package com.example.qeto.discovertbilisi.api.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qeto.discovertbilisi.R;
import com.example.qeto.discovertbilisi.api.models.PlaceModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationInfoActivity extends AppCompatActivity {
    @BindView(R.id.mainTitle)
    TextView title;
    @BindView(R.id.mainDescription)
    TextView description;
    @BindView(R.id.mainImage)
    ImageView image;
    PlaceModel placeModel;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        i = getIntent();
        placeModel = i.getParcelableExtra("PlaceInfo");
        setPlaceModel(placeModel);

    }
    private void setPlaceModel(PlaceModel placeModel){
        title.setText(placeModel.getTitle());
        image.setImageResource(R.drawable.kartlis_deda);
        description.setText(placeModel.getDescription());
        description.setMovementMethod(new ScrollingMovementMethod());
    }

}
