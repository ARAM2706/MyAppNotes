package com.app.notapsavhll.appnotas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.notapsavhll.appnotas.Datos.Nota;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private final Nota[] dummyModels;
    private final ItemFragment.OnListFragmentInteractionListener interactionListener;

    public ItemRecyclerViewAdapter(Nota[] items, ItemFragment.OnListFragmentInteractionListener listener) {

        dummyModels = items;
        interactionListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Nota dm = dummyModels[position];
        holder.dummyModelItem = dm;

        if(dm.getTipo()==0){

            holder.imagen.setImageResource(R.drawable.nota);
            holder.titulo.setText(dm.getId_nota()+" - "+dm.getTitulo());
            holder.descrip.setText(dm.getDescripcion());
            holder.fechaIni.setText("Creada:"+" "+dm.getFecha_creacion());
            holder.fechaFin.setText("");
            holder.checa.setText("");

        }else if(dm.getTipo()==1){

            holder.imagen.setImageResource(R.drawable.tarea);
            holder.titulo.setText(dm.getId_nota()+" - "+dm.getTitulo());
            holder.descrip.setText(dm.getDescripcion());
            holder.fechaIni.setText("Creada:"+" "+dm.getFecha_creacion());
            holder.fechaFin.setText("Finaliza:"+" "+dm.getFecha_limite()+" - "+dm.getHora_limite());

            if(dm.getChecalo()==0){

                holder.checa.setText(R.string.card_checaStatusBAD);

            }else if(dm.getChecalo()==1){

                holder.checa.setText(R.string.card_checaStatusGOOD);

            }

        }





        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != interactionListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    interactionListener.onListFragmentInteraction(holder.dummyModelItem);
                }

            }

        });

    }

    @Override
    public int getItemCount() {
        return dummyModels.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public final ImageView imagen;
        public final TextView titulo;
        public final TextView descrip;
        public final TextView fechaIni;
        public final TextView fechaFin;
        public final TextView checa;

        public Nota dummyModelItem;


        public ViewHolder(View view) {
            super(view);

            mView = view;
            imagen = (ImageView) view.findViewById(R.id.foto);
            titulo = (TextView) view.findViewById(R.id.txt_titulo);
            descrip = (TextView) view.findViewById(R.id.txt_descrip);
            fechaIni = (TextView) view.findViewById(R.id.txt_fechaIni);
            fechaFin = (TextView) view.findViewById(R.id.txt_fechaFin);
            checa = (TextView) view.findViewById(R.id.txt_checa);

        }

    }

}
