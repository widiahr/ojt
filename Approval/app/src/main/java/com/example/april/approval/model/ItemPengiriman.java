package com.example.april.approval.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 9/22/2017.
 */

public class ItemPengiriman {
    @SerializedName("id_paket")
    private Integer id_paket;

    @SerializedName("kode_pengiriman")
    private String kodePengiriman;

    @SerializedName("penerima")
    private String penerima;

    @SerializedName("jenis_barang")
    private String jenisBarang;

    @SerializedName("status_pengiriman")
    private String statusPengiriman;

    public Integer getId_paket() {
        return id_paket;
    }

    public void setId_paket(Integer id_paket) {
        this.id_paket = id_paket;
    }

    public String getKodePengiriman() {
        return kodePengiriman;
    }

    public void setKodePengiriman(String kodePengiriman) {
        this.kodePengiriman = kodePengiriman;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public String getStatusPengiriman() {
        return statusPengiriman;
    }

    public void setStatusPengiriman(String statusPengiriman) {
        this.statusPengiriman = statusPengiriman;
    }
}
