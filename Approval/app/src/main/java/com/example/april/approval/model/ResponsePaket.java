package com.example.april.approval.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Aprilia on 08/09/2017.
 */

public class ResponsePaket {

    @SerializedName("semuapaket")
    private List<SemuapaketItem> semuapaket;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public void setSemuapaket(List<SemuapaketItem> semuapaket){
        this.semuapaket = semuapaket;
    }

    public List<SemuapaketItem> getSemuapaket(){
        return semuapaket;
    }

    public void setError(boolean error){
        this.error = error;
    }

    public boolean isError(){
        return error;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "ResponsePaket{" +
                        "semuapaket = '" + semuapaket + '\'' +
                        ",error = '" + error + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
