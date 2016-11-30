package com.example.qeto.discovertbilisi.api.Service;

import com.example.qeto.discovertbilisi.api.ui.models.LanguageModel;
import com.example.qeto.discovertbilisi.api.ui.models.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by ZVIO on 11/30/2016.
 */

public interface IRetrofitService {

    @Headers({
            "Content-Type: application/json",
            "JsonStub-User-Key: 042b39aa-b4ce-41a3-b627-c711b33a72bb",
            "JsonStub-Project-Key: ed091935-8955-4a3b-8aab-0dd7eb2d8b12"
    })
    @GET("all/places/GEO")
    Call<ServiceResponse> getPlaces();
}

