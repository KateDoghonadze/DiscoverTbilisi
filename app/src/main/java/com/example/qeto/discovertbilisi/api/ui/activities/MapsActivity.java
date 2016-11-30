package com.example.qeto.discovertbilisi.api.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qeto.discovertbilisi.R;
import com.example.qeto.discovertbilisi.api.Service.IRetrofitService;
import com.example.qeto.discovertbilisi.api.Service.RetrofitClient;
import com.example.qeto.discovertbilisi.api.ui.models.LanguageModel;
import com.example.qeto.discovertbilisi.api.ui.models.PictureModel;
import com.example.qeto.discovertbilisi.api.ui.models.PlaceModel;
import com.example.qeto.discovertbilisi.api.ui.models.ServiceResponse;
import com.example.qeto.discovertbilisi.api.ui.models.TypeModel;
import com.example.qeto.discovertbilisi.api.ui.models.TypesEnum;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.base.Predicate;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.id.list;

public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.InfoWindowAdapter,
        GoogleMap.OnInfoWindowClickListener {
    private Toolbar toolbar;
    private GoogleMap mMap;
    public PlaceModel placeModel;
    public PictureModel pictureModel;
    public TypeModel typeModel;
    public View mapView;
    public ArrayList<PlaceModel> places = new ArrayList<PlaceModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setSupportActionBar();
        mapView = findViewById(R.id.map);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        getData();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        TextView title;
        TextView description;
        ImageView imageView;
        View v = getLayoutInflater().inflate(R.layout.fragment_info_window, null);

        final LatLng latLng = marker.getPosition();
        Point mappoint = mMap.getProjection().toScreenLocation(latLng);
        mappoint.set(mappoint.x, mappoint.y - (int) getHeight() / 2);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(mMap.getProjection().fromScreenLocation(mappoint)));

        PlaceModel resultPlace = null;

        for (PlaceModel place : places) {
            if (place.getmLatitude() == latLng.latitude && place.getmLongitude() == latLng.longitude) {
                resultPlace = place;
            }
        }

        title = (TextView) v.findViewById(R.id.textViewTitle);
        description = (TextView) v.findViewById(R.id.textViewDescription);
        imageView = (ImageView) v.findViewById(R.id.locationImageView);
        if (resultPlace != null) {
            title.setText(resultPlace.getTitle());
            description.setText(resultPlace.getDescription());
            Picasso.with(this).load(resultPlace.getBasicPicture().getUrl()).into(imageView);
        }
        return v;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_manu, menu);
        return true;
    }

    protected void setSupportActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        final LatLng latLng = marker.getPosition();
        PlaceModel resultPlace = null;
        for (PlaceModel place : places) {
            if (place.getmLatitude() == latLng.latitude && place.getmLongitude() == latLng.longitude) {
                resultPlace = place;
            }
        }
        if (resultPlace != null) {
            Intent i = new Intent(this, LocationInfoActivity.class);
            i.putExtra("PlaceInfo", resultPlace);
            startActivity(i);
        } else {
            Toast.makeText(MapsActivity.this, "Ups", Toast.LENGTH_SHORT).show();
        }

    }

    public float getHeight() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        return height;
    }

    public void getData() {
        IRetrofitService apiService = RetrofitClient.getClient().create(IRetrofitService.class);
        Call<ServiceResponse> call = apiService.getPlaces();
        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, final Response<ServiceResponse> response) {
                List<LanguageModel> languages = response.body().getLanguages();
                ArrayList<TypeModel> types = new ArrayList<TypeModel>();
                for (LanguageModel lang : languages) {
                    types.addAll(lang.getTypes());
                }
                for (TypeModel type : types) {
                    places.addAll(type.getPlaces());
                }

                for (PlaceModel place : places) {
                    setMarker(place);
                }

            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Log.e("GITA-ANDROID", t.getMessage());
            }
        });
    }

    public void setMarker(PlaceModel place) {
        int iconUrl = 0;
        switch (place.getTypeID()) {
            case TypesEnum.Statues:
                iconUrl = R.drawable.statue_icon;
                break;
            case TypesEnum.Churches:
                iconUrl = R.drawable.church_icon;
                break;
            case TypesEnum.Buildings:
                iconUrl = R.drawable.building_icon;
                break;
        }
        if (iconUrl != 0) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(place.mLatitude, place.mLongitude))
                    .title(place.Title)
                    .icon(BitmapDescriptorFactory.fromResource(iconUrl)));

            mMap.setOnMarkerClickListener(this);
            mMap.setInfoWindowAdapter(this);
            mMap.setOnInfoWindowClickListener(this);
        }
    }
}
