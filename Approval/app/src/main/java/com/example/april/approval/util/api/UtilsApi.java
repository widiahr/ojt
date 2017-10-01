package com.example.april.approval.util.api;

/**
 * Created by hp on 9/6/2017.
 */

public class UtilsApi {
    // localhost.
    public static final String BASE_URL_API = "http://quaquaversal-qualif.000webhostapp.com/appkurir/index.php/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

}

