package com.example.appausa.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.appausa.R;
import com.example.appausa.interfaces.ISession;
import com.example.appausa.model.AesUtil;
import com.example.appausa.model.CredencialesBasic;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Login extends AppCompatActivity {

    Button b1;
    EditText c, cc;
    TextView cambiarC;
    Intent x, i;
    AesUtil crip = new AesUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        b1 = findViewById(R.id.button);
        c = findViewById(R.id.correo);
        cc = findViewById(R.id.contrasenal);
        x = getIntent();
        String m = x.getStringExtra("mensaje");
        if (m != null) {
            Toast.makeText(getApplicationContext(), m, Toast.LENGTH_LONG).show();
        }
        cambiarC = findViewById(R.id.recuperarC);
        SpannableString content = new SpannableString(cambiarC.getText());
        content.setSpan(new UnderlineSpan(), 0, cambiarC.length(), 0);
        cambiarC.setText(content);
        cambiarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String user = c.getText().toString();
                String contr = cc.getText().toString();
                buscarCuenta(user, contr);
            }
        });
    }

    public void onBackPressed() { super.onBackPressed(); finishAffinity(); System.exit(0); }

    public void buscarCuenta(String correo, String contrasena) {
        correo = crip.encrypt(correo);
        contrasena = crip.encrypt(contrasena);
        Retrofit con = new Retrofit.Builder().baseUrl("http://192.168.0.14:80")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ISession is = con.create(ISession.class);
        Call<String> call = is.login(new CredencialesBasic(correo,contrasena));
        String finalCorreo = correo;
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try{
                    if(response.isSuccessful()){
                        String resp = response.body();
                        if (resp == "Login Exitoso"){
                            Intent x = new Intent(Login.this, MainActivity.class);
                            Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_LONG).show();
                            x.putExtra("user", crip.decrypt(finalCorreo));
                            startActivity(x);
                        } else {
                            Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_LONG).show();
                        }
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Login fallido", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
    }

}