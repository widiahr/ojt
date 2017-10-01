package com.example.april.approval.util.api;

import com.example.april.approval.model.ResponseA;
import com.example.april.approval.model.ResponseKurir;
import com.example.april.approval.model.ResponsePaket;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by hp on 9/6/2017.
 */

public interface BaseApiService {
    // Fungsi ini untuk memanggil API http://10.0.2.2/latapi/index.php/login
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("pass") String pass);


    @GET("semuakurir")
    Call<ResponseKurir> getSemuakurir();

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> submitKurirRequest(@Field("email") String email,
                                          @Field("pass") String pass,
                                          @Field("nama") String nama,
                                          @Field("type") String type,
                                          @Field("alamat") String alamat,
                                          @Field("telp") String telp);
    //@Field("id_kantor") Integer id_kantor

    @GET("semua-paket")
    Call<ResponsePaket> getSemuapaket();

    @GET("semua-paket")
    Call<ResponsePaket> getSemuapakett();


    @FormUrlEncoded
    @POST("input-paket")
    Call<ResponseBody> submitPaketRequest(@Field("kode_pengiriman") String kodepengiriman,
                                          @Field("pengirim") String pengirim,
//                                          @Field("id_kantor") Integer id_kantor,
                                          @Field("telp_pengirim") String telppengirim,
                                          @Field("penerima") String penerima,
                                          @Field("alamat_penerima") String alamatpenerima,
                                          @Field("telp_penerima") String telppenerima,
                                          @Field("jenis_barang") String jenisbarang,
                                          @Field("qty") String qty,
                                          @Field("width") String width,
                                          @Field("length") String length,
                                          @Field("height") String height,
                                          @Field("kendaraan") String kendaraan,
                                          @Field("ket") String ket,
                                          @Field("status_pengiriman") String status,
                                          @Field("date") String date
    );
//    @Field("kurir") String kurir

    @GET("semua-pengiriman")
    Call<ResponseA> getSemuapengiriman();

    @GET("kantor")
    Call<ResponseA> getKantor();


    @FormUrlEncoded
    @POST("update-paket/{id_paket}")
    Call<ResponseBody> updatePost(@Path("id_paket") Integer id,
                                  @Field("status_pengiriman") String status_pengiriman,
                                  @Field("status_pod") String status_pod
    );

    @FormUrlEncoded
    @POST("input-pengiriman")
    Call<ResponseBody> updatePengiriman(@Field("id_paket") Integer id_paket,
                                     @Field("kode_pengiriman") String kode_pengiriman,
                                     @Field("penerima_paket") String penerima_paket,
                                     @Field("hubungan_penerima") String hubungan_penerima,
                                     @Field("status_pod") String status_pod,

                                     @Field("date_tiba") String date_tiba);
//    @Field("signature") String signature, ini yg mana
}
