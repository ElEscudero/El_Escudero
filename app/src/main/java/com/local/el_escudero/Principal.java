package com.local.el_escudero;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.local.el_escudero.R;
import com.local.el_escudero.fragmentsPrincipal.CarritoFragment2;
import com.local.el_escudero.fragmentsPrincipal.Homefr;
import com.local.el_escudero.fragmentsPrincipal.InfoFragment;
import com.local.el_escudero.fragmentsPrincipal.user;
import com.local.el_escudero.sqlLite.SQLite;


public class Principal extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        //Con este metodo que se crea por defecto, vamos a implementar lo de los Fragments que se
        //abran al pulsar en el bottoom bar
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    pushfragmentss(new Homefr());
                    return true;
                case R.id.navigation_user:
                    pushfragmentss(new user());
                    return true;
                case R.id.navigation_info:
                    pushfragmentss(new InfoFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Aqui agregamos lo del carrito y lo de la toolbar
        toolbar = findViewById(R.id.toolbar);

        SQLite sql = new SQLite(this);


        setSupportActionBar(toolbar);

        setContentView(R.layout.activity_prinicpal);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        pushfragmentss(new Homefr());

    }

    //Metodo para pasar de un fragment a otro
    public void pushfragmentss(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor,fragment).addToBackStack(null).commit();
    }
    //Metodo creado por robert para mostrar el carrito
    private void mostrarSuperCarrito() {
        //pushFragment(new CarritoFragment());
        CarritoFragment2 carritoFragment2 = new CarritoFragment2();
        carritoFragment2.show(getSupportFragmentManager(),"Test");
    }
    //Los metodos que se usan aqui son para el cuadro de dialogo del carrito
    public void carritoOnclick(View v) {
        mostrarSuperCarrito();
        Log.d("TAG", "Hola carrito");
    }
    //Lo creo porque me lo pide el View.OnclickListener
    @Override
    public void onClick(View v) {

    }
}
