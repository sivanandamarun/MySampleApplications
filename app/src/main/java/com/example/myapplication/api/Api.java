package com.example.myapplication.api;

import com.example.myapplication.model.AndVersion;
import com.example.myapplication.model.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://simplifiedcoding.net/demos/";
    String BASE_URL1 = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Hero>> getHeros();

    @GET("android/jsonandroid")
    Call<AndVersion> getVersions();


}
