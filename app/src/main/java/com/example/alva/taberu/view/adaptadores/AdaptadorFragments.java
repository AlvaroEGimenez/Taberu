package com.example.alva.taberu.view.adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.alva.taberu.model.Receta;
import com.example.alva.taberu.view.fragments.DetalleFragment;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorFragments extends FragmentPagerAdapter {

    private List<DetalleFragment> listaDetalleFragments = new ArrayList<>();
    private List<Receta> listaRecetas;

    public AdaptadorFragments(FragmentManager fm,List<Receta> listaRecetas) {
        super(fm);
        this.listaRecetas = listaRecetas;

        for (Receta receta : listaRecetas){
            listaDetalleFragments.add(DetalleFragment.factory(receta));

        }
    }


    @Override
    public Fragment getItem(int posicion) {
        return listaDetalleFragments.get(posicion);
    }

    @Override
    public int getCount() {
        return listaRecetas.size();
    }
}
