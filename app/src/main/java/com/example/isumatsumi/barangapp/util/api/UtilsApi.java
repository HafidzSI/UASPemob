package com.example.isumatsumi.barangapp.util.api;

public class UtilsApi {

    public static final String BASE_URL_API = "http://192.168.43.233/barang/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
