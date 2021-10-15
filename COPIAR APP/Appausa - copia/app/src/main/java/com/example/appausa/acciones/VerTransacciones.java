package com.example.appausa.acciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appausa.R;
import com.example.appausa.adapter.TransferenciaAdapter;
import com.example.appausa.interfaces.ICuenta;
import com.example.appausa.interfaces.ITransferencia;
import com.example.appausa.model.AesUtil;
import com.example.appausa.model.CuentaBancaria;
import com.example.appausa.model.Transferencia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerTransacciones extends Fragment {

    public static String user;
    private ListView listview;
    AesUtil crip = new AesUtil();
    private View l;
    TransferenciaAdapter ra;
    List<Transferencia> lista = new ArrayList<>(), aux = new ArrayList<>();

    public static VerTransacciones newInstance(String u) {
        VerTransacciones frag = new VerTransacciones();
        Bundle args = new Bundle();
        user = u;
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        l = inflater.inflate(R.layout.vercomentarios, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Datos de Usuario");
        listview = l.findViewById(R.id.lcomentarios);
        obtenerComentarios(user);
        ra = new TransferenciaAdapter(getActivity(), lista);
        listview.setAdapter(ra);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(l.getContext(), "Enviado : " + lista.get(position).getFecha(), Toast.LENGTH_LONG).show();
            }
        });
        return l;
    }

    private void obtenerComentarios(String s) {
        Retrofit con = new Retrofit.Builder().baseUrl("http://192.168.20.27:3010")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ITransferencia is = con.create(ITransferencia.class);
        Call<List<Transferencia>> call = is.encontrarTransacciones(crip.encrypt(user));
        call.enqueue(new Callback<List<Transferencia>>() {
            @Override
            public void onResponse(Call<List<Transferencia>> call, Response<List<Transferencia>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Transferencia> resp = response.body();
                        if (resp != null) {
                            lista = resp;
                        } else {
                            Toast.makeText(l.getContext(), "No se hallaron transacciones", Toast.LENGTH_LONG).show();
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(l.getContext(), "Busqueda fallida", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Transferencia>> call, Throwable t) {
                Toast.makeText(l.getContext(), "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
}
