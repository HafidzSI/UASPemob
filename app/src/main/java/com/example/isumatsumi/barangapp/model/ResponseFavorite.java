package com.example.isumatsumi.barangapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Isumatsumi on 19/12/2018.
 */

public class ResponseFavorite {
    @SerializedName("semuafavorit")
    private List<SemuafavoritItem> semuafavorit;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public void setSemuafavorit(List<SemuafavoritItem> semuafavorit){
        this.semuafavorit = semuafavorit;
    }

    public List<SemuafavoritItem> getSemuafavorit(){
        return semuafavorit;
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
                "ResponseFavorite{" +
                        "semuafavorit = '" + semuafavorit + '\'' +
                        ",error = '" + error + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
