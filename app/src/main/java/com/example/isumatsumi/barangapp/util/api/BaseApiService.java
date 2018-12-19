package com.example.isumatsumi.barangapp.util.api;

import com.example.isumatsumi.barangapp.model.ResponseBarang;
import com.example.isumatsumi.barangapp.model.ResponseFavorite;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password);

    @GET("barang")
    Call<ResponseBarang> getSemuaBarang();

    @FormUrlEncoded
    @POST("barang")
    Call<ResponseBody> simpanBarangRequest(@Field("nama_barang") String namabarang,
                                           @Field("jenis_barang") String jenis,
                                           @Field("jumlah") String jumlah,
                                           @Field("kondisi") String kondisi);

    @DELETE("barang/{idbarang}")
    Call<ResponseBody> deteleBarang(@Path("idbarang") String idbarang);

    @GET("favorit")
    Call<ResponseFavorite> getFavorite();

    @POST("favorit")
    Call<ResponseBody> simpanFavorite(@Field("nama_barang") String favnamabarang,
                                      @Field("jenis_barang") String favjenisbarang,
                                      @Field("jumlah") String favjumlah,
                                      @Field("kondisi") String favkondisi);
}
