package com.example.april.approval.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hp on 9/7/2017.
 */

public class ResponseKurir {

    @SerializedName("semuakurir")
    private List<SemuakurirItem> semuakurir;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<SemuakurirItem> getSemuakurir() {
        return semuakurir;
    }

    public void setSemuakurir(List<SemuakurirItem> semuakurir) {
        this.semuakurir = semuakurir;
    }

    public String getMessage() {
        return message;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return
                "ResponseKurir{" +
                        "semuakurir = '" + semuakurir + '\'' +
                        ",error = '" + error + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
