package com.example.qeto.discovertbilisi.api.Service;

import com.example.qeto.discovertbilisi.api.ui.models.LanguageModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ZVIO on 11/30/2016.
 */

public interface IRetrofitService {
    String BASE = "r/";

    @GET(BASE + "languages")
    Call<LanguageModel> getLanguages();

}

