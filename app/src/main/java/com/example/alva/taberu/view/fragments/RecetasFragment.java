package com.example.alva.taberu.view.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.example.alva.taberu.view.adaptadores.AdaptadorRecetas;
import com.example.alva.taberu.R;
import com.example.alva.taberu.view.adaptadores.RecyclerItemTouchHelper;
import com.example.alva.taberu.model.Receta;
import com.example.alva.taberu.view.DetalleActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecetasFragment extends Fragment implements AdaptadorRecetas.OnRecetaClickListerner, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private List<Receta> listaRecetas;
    private AdaptadorRecetas adaptadorRecetas;
    private View viewCoordinator;


    public RecetasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recetas, container, false);
        viewCoordinator = view.findViewById(R.id.coordinator);
        listaRecetas = new ArrayList<>();
        setHasOptionsMenu(true);

        cargarRecetas(listaRecetas);

        adaptadorRecetas = new AdaptadorRecetas(listaRecetas, this);

        RecyclerView recyclerViewRecetas = view.findViewById(R.id.recyclerRecetas);
        recyclerViewRecetas.setHasFixedSize(true);
        recyclerViewRecetas.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewRecetas.setItemAnimator(new DefaultItemAnimator());

        //-----------------------------Istancia del RecyclerItemTouchHelper con los paremetros pedidos por construcotor------------------//
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);


        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerViewRecetas);
        recyclerViewRecetas.setAdapter(adaptadorRecetas);

        return view;
    }

    @Override
    public void OnItemClick(Receta receta, int posicion) {
        Integer posicionClick = posicion;
        Intent intent = new Intent(getActivity(), DetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleActivity.KEY_OBJETO, (Serializable) listaRecetas);
        bundle.putInt(DetalleActivity.KEY_POSICION, posicionClick);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //------------------Metodo para inflar el menu de busqueda-------------------------//
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_busqueda, menu);
        MenuItem itemBuscar = menu.findItem(R.id.buscar);

        SearchView searchView = (SearchView) itemBuscar.getActionView();
        //--------------Setear la flecha <-- para cancelar la busqueda-------------------//
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        //-------------------Listener para el buscador----------------------------------//
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adaptadorRecetas.getFilter().filter(s);
                return false;

            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    //-------------Metodo para Mostar el backgroud del item a eliminar y elimarlo----------------//
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof AdaptadorRecetas.RecetaViewHolder) {


            final Receta receta = listaRecetas.get(viewHolder.getAdapterPosition());
            final Integer deleteindex = viewHolder.getAdapterPosition();

            adaptadorRecetas.eliminarReceta(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar.make(viewCoordinator, "Receta Eliminada", Snackbar.LENGTH_LONG);
            snackbar.setAction("Deshacer", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adaptadorRecetas.noRemoverReceta(receta, deleteindex);
                }
            });
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();
        }

    }

    private void cargarRecetas(List<Receta> recetas) {

        recetas.add(new Receta("FLAUTAS DE JAMON Y QUESO ", R.drawable.flautasjyq, R.string.Pollo_salsa_Cebolla_Ingredientes, R.string.preparacionpollo, "Facil", 10, 4));
        recetas.add(new Receta("PECHUGAS DE POLLO EN SALSA DE TOMATE", R.drawable.pechugassaltatomate, R.string.Pollo_salsa_Cebolla_Ingredientes, R.string.preparacionpollo, "Medio", 30, 4));
        recetas.add(new Receta("ESPAGUETIS CON SALSA DE QUESO POLLO Y BACON", R.drawable.espaguetisquesopollobacon, R.string.Pollo_salsa_Cebolla_Ingredientes, R.string.preparacionpollo, "Facil", 20, 4));
        recetas.add(new Receta("POLLO AL CURRY", R.drawable.pollocurry, R.string.Pollo_salsa_Cebolla_Ingredientes, R.string.preparacionpollo, "Dificil", 40, 4));
        recetas.add(new Receta("SANDWICH EMPANADO", R.drawable.sandwichempanado, R.string.Pollo_salsa_Cebolla_Ingredientes, R.string.preparacionpollo, "Facil", 10, 4));
        recetas.add(new Receta("SOLOMILLO DE CERDO EN SALSA DE QUESO", R.drawable.solomillodecerdo, R.string.Pollo_salsa_Cebolla_Ingredientes, R.string.preparacionpollo, "Facil", 35, 4));
        recetas.add(new Receta("FLAMENQUINES CORDOBESES", R.drawable.flamenquines, R.string.Pollo_salsa_Cebolla_Ingredientes, R.string.preparacionpollo, "Facil", 10, 4));
    }
}
