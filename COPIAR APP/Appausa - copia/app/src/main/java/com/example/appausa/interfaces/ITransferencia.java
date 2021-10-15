package com.example.appausa.interfaces;

import com.example.appausa.model.Transferencia;
import com.example.appausa.model.TransferenciaBasic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ITransferencia {

    @POST("/transaccion/create")
    public Call<String> guardarTransaccion(@Body TransferenciaBasic t);

    @GET("/transaccion/findtransacciones/{id}")
    public Call<List<Transferencia>> encontrarTransacciones(@Path("id") String id);

    }
