package com.example.april.approval.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 9/19/2017.
 */

public class KantorRItem {
    @SerializedName("id_kantor")
    private Integer id_kantor;

    @SerializedName("nama_kantor")
    private String nama_kantor;

    public Integer getId_kantor() {
        return id_kantor;
    }

    public void setId_kantor(Integer id_kantor) {
        this.id_kantor = id_kantor;
    }

    public String getNama_kantor() {
        return nama_kantor;
    }

    public void setNama_kantor(String nama_kantor) {
        this.nama_kantor = nama_kantor;
    }
}
