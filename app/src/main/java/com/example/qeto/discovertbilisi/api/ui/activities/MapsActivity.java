package com.example.qeto.discovertbilisi.api.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qeto.discovertbilisi.R;
import com.example.qeto.discovertbilisi.api.Service.LanguagesApi;
import com.example.qeto.discovertbilisi.api.ui.models.LanguageModel;
import com.example.qeto.discovertbilisi.api.ui.models.PictureModel;
import com.example.qeto.discovertbilisi.api.ui.models.PlaceModel;
import com.example.qeto.discovertbilisi.api.ui.models.TypeModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public static final String Description = "„ქართვლის დედა“[1] — მონუმენტური ქანდაკება თბილისში, რომელიც ქალაქის ერთ-ერთი სიმბოლო გახდა. მონუმენტის ავტორია ქართველი მოქანდაკე ელგუჯა ამაშუკელი, რომელსაც 1966 წელს ამ ქანდაკებისთვის მიენიჭა შოთა რუსთაველის სახელობის სახელმწიფო პრემია.[1] ქანდაკება სოლოლაკის გორაზე 1958 წელს აღიმართა ქალაქის 1500 წლისთავთან დაკავშირებით. თავდაპირველი გადაწყვეტილების თანახმად, ხის ალეგორიული ქანდაკება დედაქალაქს დროებით დაამშვენებდა, თუმცა მოგვიანებით ეს გადაწყვეტილება შეიცვალა. ამის გამო ხის ფაქტურა 1963 წელს ალუმინით დაიფარა, რათა ქანდაკება არ დაზიანებულიყო. 1997 წელს კი ძველი ქანდაკება ახლით შეიცვალა. 20 მეტრის სიმაღლის ალუმინის ქართულ ეროვნულ სამოსში ჩაცმული ქალის ქანდაკების ავტორია ელგუჯა ამაშუკელი. „ქართვლის დედა“ არის ქართული ეროვნული ხასიათის სიმბოლო: ქანდაკებას ცალ ხელში ღვინის თასი უჭირავს მოყვართათვის, მეორეში კი ხმალი მტერთათვის. ქანდაკების ძირში იშლება ხედი თბილისის ბოტანიკურ ბაღზე, აგრეთვე ბოტანიკური ბაღის ფარგლებში შესაძლებელია მდინარე წავკისისწყლის აღბეჭდვა, სადაც სამი ხიდია გადაგდებული.[2]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setSupportActionBar();
        mapView = findViewById(R.id.map);
        pictureModel = new PictureModel(1, "/drawable/kartlis_deda.jpg", "ქართლის დედა");
        typeModel = new TypeModel(1, "ძეგლი", null,1);
        placeModel = new PlaceModel(1, "ქართლის დედა", 41.688079, 44.807602, pictureModel, "", Description, null, null, 1, false);
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
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(placeModel.mLatitude, placeModel.mLongitude))
                .title(placeModel.Title)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.statue_icon)));
        mMap.setMyLocationEnabled(true);
        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(this);
        mMap.setOnInfoWindowClickListener(this);



        LanguagesApi redditApi = new LanguagesApi();
        redditApi.getLanguages(callback);

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

        LatLng latLng = marker.getPosition();
        double screen_height = (double) mapView.getHeight();
        Point mappoint = mMap.getProjection().toScreenLocation(latLng);
        mappoint.set(mappoint.x, mappoint.y - (int) getHeight() / 2);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(mMap.getProjection().fromScreenLocation(mappoint)));
        title = (TextView) v.findViewById(R.id.textViewTitle);
        description = (TextView) v.findViewById(R.id.textViewDescription);
        imageView = (ImageView) v.findViewById(R.id.locationImageView);
        imageView.setImageResource(R.drawable.kartlis_deda);
        title.setText("ქართლის დედა");

        description.setText("Kartlis Deda[1](Georgian: ქართლის დედა; Mother of a Kartli or Mother of a Georgian), is a monument in Georgia’s capital Tbilisi.\n" +
                "\n" +
                "The statue was erected on the top of Sololaki hill in 1958, the year Tbilisi celebrated its 1500th anniversary. Prominent Georgian sculptor Elguja Amashukeli designed the twenty-metre aluminium figure of a woman in Georgian national dress. She symbolizes the Georgian national character: in her left hand she holds a bowl of wine to greet those who come as friends, and in her right hand is a sword for those who come as enemies.[2]");
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
        Intent i = new Intent(this, LocationInfoActivity.class);
        i.putExtra("PlaceInfo", placeModel);
        startActivity(i);
    }

    public float getHeight() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        return height;
    }





    Callback<LanguageModel> callback = new Callback<LanguageModel>() {
        @Override
        public void onResponse(Call<LanguageModel> call, Response<LanguageModel> response) {
int k = 0;
        }

        @Override
        public void onFailure(Call<LanguageModel> call, Throwable t) {
            Toast.makeText(MapsActivity.this, "Something went wrong :/", Toast.LENGTH_SHORT).show();
        }
    };
}
