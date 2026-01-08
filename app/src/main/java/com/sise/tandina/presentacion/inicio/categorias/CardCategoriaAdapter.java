package com.sise.tandina.presentacion.inicio.categorias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sise.tandina.R;
import com.sise.tandina.data.model.Categoria;
import com.sise.tandina.presentacion.inicio.preguntas.PreguntasActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class CardCategoriaAdapter extends RecyclerView.Adapter<CardCategoriaAdapter.ViewHolder>{

    private final Context context;
    private final List<Categoria> categorias;
    public CardCategoriaAdapter(Context context, List<Categoria> categorias) {
        this.context = context;
        this.categorias = categorias;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void actualizarLista(List<Categoria> categorias) {
        this.categorias.clear();
        this.categorias.addAll(categorias);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_categoria, parent, false);
        return new CardCategoriaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        holder.tvTitulo.setText(categoria.getDescripcion());
        holder.tvSubtitulo.setText("Jugar Ahora");
        Glide.with(holder.itemView.getContext())
                .load(categoria.getUrlImagen())
                .centerCrop()
                .into(holder.imvLogo);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(this.context, PreguntasActivity.class);
            intent.putExtra("ID_CATEGORIA", categoria.getIdCategoria());
            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.categorias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvLogo;
        private TextView tvTitulo;
        private TextView tvSubtitulo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvLogo = itemView.findViewById(R.id.cardelecvig_imv_logo);
            tvTitulo = itemView.findViewById(R.id.cardelecvig_tv_titulo);
            tvSubtitulo = itemView.findViewById(R.id.cardelecvig_tv_subtitulo);
        }
    }
}
