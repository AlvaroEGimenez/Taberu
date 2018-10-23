package com.example.alva.taberu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
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
