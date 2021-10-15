package com.example.appausa.interfaces;


import com.example.appausa.model.CredencialesBasic;
import com.example.appausa.model.CuentaApp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ISession {

    @POST("/session/login")
    public Call<String> login(@Body CredencialesBasic t) ;

    @GET("/session/find/{id}")
    public Call<CuentaApp> findCuenta(@Path("id") String id);

    @GET("/session/logout/{id}")
    public Call<Boolean> logout(@Path("id") String id);



    }
