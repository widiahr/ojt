package com.example.april.approval.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 9/7/2017.
 */

public class SemuakurirItem {

    @SerializedName("id_kurir")
    private Integer id_kurir;

    @SerializedName("nama")
    private String nama;

    @SerializedName("email")
    private String email;

    public void setId_kurir(Integer id_kurir) { this.id_kurir = id_kurir; }

    public Integer getId_kurir() { return id_kurir; }

    public  void setNama(String nama) { this.nama = nama; }

    public String getNama() { return nama; }

    public void setEmail (String email) {this.email = email; }

    public  String getEmail() { return  email; }



}
