package com.example.qeto.discovertbilisi.api.Service;

import com.example.qeto.discovertbilisi.api.ui.models.LanguageModel;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ZVIO on 11/30/2016.
 */

public class LanguagesApi {

    public static IRetrofitService retrofitService;

    public LanguagesApi() {
        if (retrofitService == null) retrofitService = new RetrofitClient().getService();
    }

    public void getLanguages(Callback<LanguageModel> callback) {
        Call<LanguageModel> call = retrofitService.getLanguages();
        call.enqueue(callback);
    }

}

