package com.example.qeto.discovertbilisi.api.ui.activities;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.qeto.discovertbilisi.R;
import com.example.qeto.discovertbilisi.api.Settings.App;
import com.example.qeto.discovertbilisi.api.Settings.Settings;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.button_english)
    Button buttonEng;

    @BindView(R.id.button_georgian)
    Button buttonGeo;

    private String oldLang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        oldLang = Settings.getString("lang");
    }

    @OnClick(R.id.button_georgian)
    void onGeorgianClick(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            App.getInstance().changeLanguage("ka");
        }
    }

    @OnClick(R.id.button_english)
    void onEnglishClick(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            App.getInstance().changeLanguage("en");
        }
    }

    @Override
    public void onBackPressed() {

        if(!oldLang.equals(Settings.getString("lang"))){
            setResult(RESULT_OK);
        }

        super.onBackPressed();
    }
}
