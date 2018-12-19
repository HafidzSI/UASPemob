package com.example.isumatsumi.barangapp.util.api;

public class UtilsApi {

    public static final String BASE_URL_API = "http://10.44.7.137/barang/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
