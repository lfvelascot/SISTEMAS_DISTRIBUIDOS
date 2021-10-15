package com.example.appausa.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.appausa.R;
import com.example.appausa.acciones.RealizarTransaccion;
import com.example.appausa.acciones.VerCuenta;
import com.example.appausa.interfaces.ISession;
import com.example.appausa.acciones.VerTransacciones;
import com.example.appausa.model.AesUtil;
import com.example.appausa.model.Log;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener {

    private DrawerLayout drawerLayout;
    private String username;
    public Intent intent;
    private Log log = new Log();
    AesUtil crip = new AesUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        intent = getIntent();
        username = intent.getStringExtra("user");
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v = navigationView.getHeaderView(0);
        MenuItem menuItem = navigationView.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);
        drawerLayout.addDrawerListener(this);
        onCreateOptionsMenu(navigationView.getMenu(),"");
        View header = navigationView.getHeaderView(0);
        TextView tn = header.findViewById(R.id.userNameRol);
        header.findViewById(R.id.header_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    @Override
    public void onBackPressed() {
        Fragment fragment = HomeContentFragment.newInstance(username);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if (count == 0) {
                salir();
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }

    public void salir(){
        AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
        a.setMessage("¿Desea finalizar sesion?");
        a.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout(username);
            }
        });
        AlertDialog r = a.create();
        r.setIcon(R.mipmap.iconoapp);
        r.setTitle("Alerta");
        r.show();
    }

    private void logout(String username) {
        String u = crip.encrypt(username);
        Retrofit con = new Retrofit.Builder().baseUrl("http://192.168.20.27:3010")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ISession is = con.create(ISession.class);
        Call<Boolean> call = is.logout(u);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                try{
                    if(response.isSuccessful()){
                        Boolean resp = response.body();
                        if (resp){
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            intent.putExtra("mensaje","Sesión finalizada");
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error en logout", Toast.LENGTH_LONG).show();
                        }
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Login fallido", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.inicio:
                fragment = HomeContentFragment.newInstance(username);
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.nav_enter, R.anim.nav_exit)
                        .replace(R.id.home_content, fragment)
                        .commit();
                setTitle("Inicio");
                break;
            case R.id.verTransacciones:
                fragment = VerTransacciones.newInstance(username);
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.nav_enter, R.anim.nav_exit)
                        .replace(R.id.home_content, fragment)
                        .addToBackStack(null)
                        .commit();
                setTitle("Ver Transacciones");
                break;
            /// Empleados
            case R.id.realizarTransaccion:
                fragment = RealizarTransaccion.newInstance(username);
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.nav_enter, R.anim.nav_exit)
                        .replace(R.id.home_content, fragment)
                        .addToBackStack(null)
                        .commit();
                setTitle("Usuarios");
                break;
            case R.id.verCuenta:
                fragment = VerCuenta.newInstance(username);
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.nav_enter, R.anim.nav_exit)
                        .replace(R.id.home_content, fragment)
                        .addToBackStack(null)
                        .commit();
                setTitle("Empleados");
                break;
            case R.id.logOut:
                salir();
                break;
            default:
                throw new IllegalArgumentException("menu option not implemented!!");
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public boolean onCreateOptionsMenu(Menu menu, String rol) {
        if (rol.equals("")) {
            getMenuInflater().inflate(R.menu.th_menu, menu);
            return true;
        }
        return true;
    }


    @Override
    public void onDrawerSlide(@NonNull View view, float v) {
        //cambio en la posición del drawer
    }

    @Override
    public void onDrawerOpened(@NonNull View view) {
        //el drawer se ha abierto completamente
    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        //el drawer se ha cerrado completamente
    }

    @Override
    public void onDrawerStateChanged(int i) {
        //cambio de estado, puede ser STATE_IDLE, STATE_DRAGGING or STATE_SETTLING
    }

}
