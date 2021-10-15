package com.example.appausa.interfaces;

import com.example.appausa.model.CuentaBancaria;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICuenta {

    @GET("/cuentab/find/{id}")
    public Call<CuentaBancaria> obtenerEstado(@Path("id")String id);

    }
