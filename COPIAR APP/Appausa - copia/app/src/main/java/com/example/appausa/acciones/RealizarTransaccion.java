package com.example.appausa.acciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appausa.R;
import com.example.appausa.interfaces.ICuenta;
import com.example.appausa.interfaces.ISession;
import com.example.appausa.interfaces.ITransferencia;
import com.example.appausa.main.Login;
import com.example.appausa.main.MainActivity;
import com.example.appausa.model.AesUtil;
import com.example.appausa.model.CredencialesBasic;
import com.example.appausa.model.CuentaBancaria;
import com.example.appausa.model.TransferenciaBasic;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RealizarTransaccion extends Fragment {

    public static String user;

    AesUtil crip = new AesUtil();
    TextView t1;
    EditText e1,e2;
    Button b1;
    private View l;
    String cuentaor;


    public static RealizarTransaccion newInstance(String username) {
        RealizarTransaccion frag = new RealizarTransaccion();
        Bundle args = new Bundle();
        user = username;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        l = inflater.inflate(R.layout.realizar_transferencia, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Datos de Usuario");
        b1 = l.findViewById(R.id.enviart);
        t1 = l.findViewById(R.id.cuentaor);
        e1 = l.findViewById(R.id.cd);
        e2 = l.findViewById(R.id.vaaalor);
        cargarDatos(user);
        t1.setText(cuentaor);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String co = t1.getText().toString(), cd = e1.getText().toString(), valor = e2.getText().toString();
                if (!co.isEmpty() && !cd.isEmpty() && !valor.isEmpty()) {
                    enviar(user,co,cd,valor);
                } else {
                    Toast.makeText(l.getContext(), "Todos los campos deben estar llenos", Toast.LENGTH_LONG).show();
                }
            }
        });
        return l;
    }

    private void enviar(String user, String co, String cd, String valor) {
        try{
            TransferenciaBasic tb = new TransferenciaBasic(crip.encrypt(user),crip.encrypt(co),crip.encrypt(cd), Double.valueOf(valor));
            Retrofit con = new Retrofit.Builder().baseUrl("http://192.168.0.14:80")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            ITransferencia is = con.create(ITransferencia.class);
            Call<String> call = is.guardarTransaccion(tb);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try{
                        if(response.isSuccessful()){
                            String resp = response.body();
                            String[] r = resp.split(",");
                            if (r[0] == "Login Exitoso"){
                                Toast.makeText(l.getContext(), r[0]+" ID Transferencia: "+crip.decrypt(r[1]), Toast.LENGTH_LONG).show();
                                clean();
                            } else {
                                Toast.makeText(l.getContext(), r[0], Toast.LENGTH_LONG).show();
                            }
                        }

                    }catch (Exception e){
                        Toast.makeText(l.getContext(), "Transferencia fallida", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(l.getContext(), "Error de conexion", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e){
            Toast.makeText(l.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void clean() {
        e1.setText("");
        e2.setText("");
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
                            cuentaor = crip.decrypt(resp.getNum());
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
