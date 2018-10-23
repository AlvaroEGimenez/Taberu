package com.example.alva.taberu;



import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {
    //--------- Claves para recibir por Bundle los datos--------------------------//
    public static final String KEY_FOTO = "foto";
    public static final String KEY_INGREDIENTES = "ingredientes";
    public static final String KEY_PREPARACION = "preparacion";
    public static final String KEY_NOMBRE = "nombre";

    private Integer foto;
    private Integer ingredientes;
    private Integer preparacion;


    //-----------------Metodo para fabricar el fragment segun la cantidad de objetos en la lista-------------//
    public static  DetalleFragment factory (Receta receta){
        DetalleFragment detalleFragment = new DetalleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_FOTO,receta.getFoto());
        bundle.putInt(KEY_INGREDIENTES,receta.getIngredientes());
        bundle.putInt(KEY_PREPARACION,receta.getPreparacion());
        bundle.putString(KEY_NOMBRE,receta.getTitulo());
        detalleFragment.setArguments(bundle);
        return detalleFragment;
    }

    //---------Metodo para verificar que el bundle no sea NULL -----------------------------------//
    private void leerBundle(Bundle bundle) {
        if (bundle != null) {
            foto = bundle.getInt(KEY_FOTO);
            ingredientes = bundle.getInt(KEY_INGREDIENTES);
            preparacion = bundle.getInt(KEY_PREPARACION);
        }
    }

    public DetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        if (savedInstanceState == null){
            Toolbar toolbar = view.findViewById(R.id.detalleToolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });

            CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.detalleCollapsing);
            collapsingToolbar.setTitle(getArguments().getString(KEY_NOMBRE));
            collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        }


        //---------Recuperar los datos del Bundle para luego poder setear los datos---------------//
        leerBundle(getArguments());


        //-------------------Buscar los componentes de la vista----------------------------------//
        ImageView imageViewDetalle = view.findViewById(R.id.ivFotoDetalle);
        TextView textViewIngredientes = view.findViewById(R.id.tvIngredientesDetalle);
        TextView textViewPreparacion = view.findViewById(R.id.tvPreparacionDetalle);

        //------------------Setear las vistas con los datos rescatados del Bundle-----------------//
        imageViewDetalle.setImageResource(foto);
        textViewIngredientes.setText(ingredientes);
        textViewPreparacion.setText(preparacion);


        return view;
    }

}
