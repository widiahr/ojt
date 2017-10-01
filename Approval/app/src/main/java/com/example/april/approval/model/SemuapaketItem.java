package com.example.april.approval.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Aprilia on 08/09/2017.
 */

public class SemuapaketItem {

    @SerializedName("id_paket")
    private Integer id_paket;

    @SerializedName("date")
    private String date;

    @SerializedName("kode_pengiriman")
    private String kodePengiriman;

    @SerializedName("penerima")
    private String penerima;

    @SerializedName("telp_penerima")
    private String telpPenerima;

    @SerializedName("alamat_penerima")
    private String alamatPenerima;

    @SerializedName("jenis_barang")
    private String jenisBarang;

    @SerializedName("qty")
    private String qty;

    @SerializedName("width")
    private String width;

    @SerializedName("length")
    private String length;

    @SerializedName("height")
    private String height;

    @SerializedName("ket")
    private String ket;

    @SerializedName("status_pengiriman")
    private String status_pengiriman;

    @SerializedName("status_pod")
    private String status_pod;

    public Integer getId_paket() {
        return id_paket;
    }

    public void setId_paket(Integer id_paket) {
        this.id_paket = id_paket;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setKodePengiriman(String kodePengiriman) {
        this.kodePengiriman = kodePengiriman;
    }

    public String getKodePengiriman() {
        return kodePengiriman;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setTelpPenerima(String telpPenerima) {
        this.telpPenerima = telpPenerima;
    }

    public String getTelpPenerima() {
        return telpPenerima;
    }

    public void setAlamatPenerima(String alamatPenerima) {
        this.alamatPenerima = alamatPenerima;
    }

    public String getAlamatPenerima() {
        return alamatPenerima;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getQty() {
        return qty;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getWidth() {
        return width;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLength() {
        return length;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getKet() {
        return ket;
    }

    public String getStatus_pengiriman() {
        return status_pengiriman;
    }

    public void setStatus_pengiriman(String status_pengiriman) {
        this.status_pengiriman = status_pengiriman;
    }

    public String getStatus_pod() {
        return status_pod;
    }

    public void setStatus_pod(String status_pod) {
        this.status_pod = status_pod;
    }

    //    @Override
//    public String toString(){
//        return
//                "SemuapaketItem{" +
//                        "date = '" + date + '\'' +
//                        ",kode_pengiriman = '" + kodePengiriman + '\'' +
//                        ",penerima = '" + penerima + '\'' +
//                        ",telp_penerima = '" + telpPenerima + '\'' +
//                        ",alamat_penerima = '" + alamatPenerima + '\'' +
//                        ",jenis_barang = '" + jenisBarang + '\'' +
//                        ",qty = '" + qty + '\'' +
//                        ",width = '" + width + '\'' +
//                        ",length = '" + length + '\'' +
//                        ",height = '" + height + '\'' +
//                        ",ket = '" + ket + '\'' +
//                        "}";
//    }
}
