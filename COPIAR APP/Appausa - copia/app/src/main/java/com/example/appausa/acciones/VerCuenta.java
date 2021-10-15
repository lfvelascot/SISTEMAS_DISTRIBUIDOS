package com.example.appausa.acciones;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appausa.R;
import com.example.appausa.interfaces.ICuenta;
import com.example.appausa.model.AesUtil;
import com.example.appausa.model.CuentaBancaria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class VerCuenta extends Fragment {

    public static String user;
    private View l;
    AesUtil crip = new AesUtil();
    public CuentaBancaria cuenta;

    public static VerCuenta newInstance(String username) {
        VerCuenta frag = new VerCuenta();
        Bundle args = new Bundle();
        user = username;
        return frag;
    }

    private void cargarDatos(String usuario) {
        Retrofit con = new Retrofit.Builder().baseUrl("http://192.168.0.14:80")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ICuenta is = con.create(ICuenta.class);
        Call<CuentaBancaria> call = is.obtenerEstado(crip.encrypt(usuario));
        call.enqueue(new Callback<CuentaBancaria>() {
            @Override
            public void onResponse(Call<CuentaBancaria> call, Response<CuentaBancaria> response) {
                try{
                    if(response.isSuccessful()){
                        CuentaBancaria resp = response.body();
                        if (resp != null){
                            cuenta = resp;
                        } else {
                            Toast.makeText(l.getContext(), "No se pudo hallar una cuenta asociada a su documento", Toast.LENGTH_LONG).show();
                        }
                    }

                }catch (Exception e){
                    Toast.makeText(l.getContext(), "Busqueda fallida", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<CuentaBancaria> call, Throwable t) {
                Toast.makeText(l.getContext(), "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
    }



}
