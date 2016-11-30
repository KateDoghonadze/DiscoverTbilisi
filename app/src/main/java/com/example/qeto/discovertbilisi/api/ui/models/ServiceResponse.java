package com.example.qeto.discovertbilisi.api.ui.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QETO on 11/30/2016.
 */

public class ServiceResponse {
    public List<LanguageModel> Languages;

    public ServiceResponse(List<LanguageModel> Languages) {
        this.Languages = Languages;
    }

    public List<LanguageModel> getLanguages() {
        return Languages;
    }

    public void setLanguages(List<LanguageModel> Languages) {
        this.Languages = Languages;
    }
}
