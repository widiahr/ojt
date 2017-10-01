package com.example.april.approval.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hp on 9/19/2017.
 */

public class ResponseA {
    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("kantor")
    private List<KantorRItem> kantor;

    @SerializedName("semua-pengiriman")
    private List<ItemPengiriman> semuaPengiriman;

    @SerializedName("semua-paket")
    private List<SemuapaketItem> semuaPakett;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<KantorRItem> getKantor() {
        return kantor;
    }

    public void setKantor(List<KantorRItem> kantor) {
        this.kantor = kantor;
    }

    public List<ItemPengiriman> getSemuapengiriman() {
        return semuaPengiriman;
    }

    public void setSemuapengiriman(List<ItemPengiriman> semuapengiriman) {
        this.semuaPengiriman = semuapengiriman;
    }

    public List<SemuapaketItem> getSemuaPakett() {
        return semuaPakett;
    }
}
