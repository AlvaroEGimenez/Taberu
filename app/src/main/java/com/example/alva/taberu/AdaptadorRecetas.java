package com.example.alva.taberu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorRecetas extends RecyclerView.Adapter implements Filterable {

    private List<Receta> listaRecetas;
    private List<Receta> listaCompleta;
    private OnRecetaClickListerner listerner;


    public AdaptadorRecetas(List<Receta> listaRecetas, OnRecetaClickListerner listerner) {
        this.listaRecetas = listaRecetas;
        this.listerner = listerner;
        this.listaCompleta = new ArrayList<>(listaRecetas);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.modelo_receta,viewGroup,false);
        RecetaViewHolder recetaViewHolder = new RecetaViewHolder(view);
        return recetaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int posicion) {
                RecetaViewHolder recetaViewHolder = (RecetaViewHolder) viewHolder;
                Receta receta = listaRecetas.get(posicion);
                recetaViewHolder.bind(receta);
    }

    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    @Override
    public Filter getFilter() {
        return listaConFiltro;
    }


    //----------Metodo para filtar el RecyclerView-----------------------------------//
    private Filter listaConFiltro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //-----------Crear una lista para poder filtrarla y no tocar la original-----------//
            List<Receta> listaFiltrada = new ArrayList<>();
            //---------------Verificar que si no se ingreso texto devolver la lista completa-----//
            if (constraint == null || constraint.length() == 0){
                listaFiltrada.addAll(listaCompleta);
            }
            //-----------Caso contrario ir filtrando a medida que se agregar caracteres-----------//
            else {
                String filtro = constraint.toString().toLowerCase().trim();

                //--------------ir completando la lista con los caracteres que coincidan---------//
                for (Receta receta : listaCompleta){
                    if (receta.getTitulo().toLowerCase().contains(filtro)){
                        listaFiltrada.add(receta);
                    }
                }
            }
            //----------------Devolver la lista filtrada-----------------------------------------//
            FilterResults results = new FilterResults();
            results.values = listaFiltrada;
            return results;
        }
        //------Con esos resultados se completa la lista y se notifica al adaptador--------------//
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaRecetas.clear();
            listaRecetas.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    public class RecetaViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitulo;
        private ImageView imageViewFooto;
        private TextView textViewDificultad;
        private TextView textViewTiempo;
        private TextView textViewComensales;
        public RelativeLayout relativeLayout;
        public FrameLayout frameLayout;

        public RecetaViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.tvTitulo);
            imageViewFooto = itemView.findViewById(R.id.ivFoto);
            textViewDificultad = itemView.findViewById(R.id.tvDificualtad);
            textViewTiempo = itemView.findViewById(R.id.tvTiempoPrepracion);
            textViewComensales = itemView.findViewById(R.id.tvComensales);
            relativeLayout = itemView.findViewById(R.id.view_background);
            frameLayout = itemView.findViewById(R.id.foregraund);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Receta receta = listaRecetas.get(getAdapterPosition());
                    listerner.OnItemClick(receta,getAdapterPosition());
                }
            });
        }
        public void bind (Receta receta){
            textViewTitulo.setText(receta.getTitulo());
            imageViewFooto.setImageResource(receta.getFoto());
            textViewComensales.setText(String.valueOf(receta.getComensales()));
            textViewTiempo.setText(String.valueOf(receta.getTiempo())+" min");
            textViewDificultad.setText(receta.getDificultad());
        }
    }
    public interface OnRecetaClickListerner{
        void OnItemClick (Receta receta, int posicion);
    }
    //--------Metodo para eliminar recetas con el Swipe----------------------------------------//
    public void eliminarReceta (int posicion){
            listaRecetas.remove(posicion);
            notifyItemRemoved(posicion);
    }

    //--------Metodo para volver a agregar la receta que se elimino------------------------------//
    public void noRemoverReceta (Receta receta, int posicion){
        listaRecetas.add(posicion,receta);
        notifyItemInserted(posicion);
    }
}
