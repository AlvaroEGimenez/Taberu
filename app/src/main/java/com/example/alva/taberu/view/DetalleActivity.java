package com.example.alva.taberu.view;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alva.taberu.view.adaptadores.AdaptadorFragments;
import com.example.alva.taberu.R;
import com.example.alva.taberu.model.Receta;

import java.util.List;

public class DetalleActivity extends AppCompatActivity {
    public static final String KEY_OBJETO = "listaObjetos";
    public static final String KEY_POSICION = "poscion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        //----------Recibir la lsita y posicon del click en el recycler--------------------------//
        Intent intent = getIntent();
        Bundle bundleActivity = intent.getExtras();


        //---------------------llenar la listra y la posicion enviadas--------------------------//
        List<Receta> listaRecetas = (List<Receta>) bundleActivity.getSerializable(KEY_OBJETO);
        Integer posicion = bundleActivity.getInt(KEY_POSICION);

        //--------------------Buscar el ViewPager y crear el adaptador--------------------------//
        ViewPager viewPager = findViewById(R.id.viewpagerDetalle);
        AdaptadorFragments adaptadorFragments = new AdaptadorFragments(getSupportFragmentManager(),listaRecetas);

        //-------------setear el adaptador y la posicion del item clicleado-----------------------//
        viewPager.setAdapter(adaptadorFragments);
        viewPager.setCurrentItem(posicion);
        }
}
