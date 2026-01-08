package com.sise.tandina.presentacion.inicio.preguntas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sise.tandina.R;
import com.sise.tandina.data.model.Opcion;
import com.sise.tandina.data.model.Pregunta;
import com.sise.tandina.data.repository.PreguntaRepository;

import java.util.List;

// 🔽 AGREGADO
interface OnPuntajeChangeListener {
    void onPuntajeChange(int puntaje);
}

//agregate ahora

interface OnQuizFinishListener {
    void onFinish(int puntajeFinal);
}


public class CardPreguntaAdapter extends RecyclerView.Adapter<CardPreguntaAdapter.ViewHolder> {

    private final Context context;
    private final List<Pregunta> preguntas;
    private int puntajeTotal = 0;
    private OnPuntajeChangeListener puntajeListener;

    /// aqui agregaste//
    private int preguntasRespondidas = 0;
    private OnQuizFinishListener finishListener;


    private final PreguntaRepository repository = new PreguntaRepository();
    public CardPreguntaAdapter(Context context, List<Pregunta> preguntas) {
        this.context = context;
        this.preguntas = preguntas;
    }

    // ✅ AQUÍ VA (NIVEL DE CLASE)
    public void setOnPuntajeChangeListener(OnPuntajeChangeListener listener) {
        this.puntajeListener = listener;
    }

    //aqui agrea

    public void setOnQuizFinishListener(OnQuizFinishListener listener) {
        this.finishListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pregunta, parent, false);
        return new CardPreguntaAdapter.ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void actualizarLista(List<Pregunta> preguntas) {
        this.preguntas.clear();
        this.preguntas.addAll(preguntas);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pregunta pregunta = preguntas.get(position);
        holder.tvTitulo.setText(pregunta.getPregunta());
        Glide.with(holder.itemView.getContext())
                .load(pregunta.getUrlImagen())
                .centerCrop()
                .into(holder.imvLogo);
        //holder.itemView.setOnClickListener(v -> {
        //Intent intent = new Intent(this.context, PartidosPoliticosActivity.class);
        //this.context.startActivity(intent);


        // 🔽 NUEVO: OPCIONES


        holder.rgOpciones.removeAllViews();

        final boolean[] yaRespondio = {false};

        if (pregunta.getOpciones() != null) {
            for (Opcion opcion : pregunta.getOpciones()) {
                RadioButton rb = new RadioButton(context);
                rb.setText(opcion.getDescripcion());

                rb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked && !yaRespondio[0]) {

                        if (opcion.isValido()) {
                            // ✅ CORRECTA
                            rb.setTextColor(Color.GREEN);
                            puntajeTotal += 5;

                            if (puntajeListener != null) {
                                puntajeListener.onPuntajeChange(puntajeTotal);
                            }


                        } else {
                            // ❌ INCORRECTA
                            rb.setTextColor(Color.RED);

                            // 🔍 mostrar cuál era la correcta
                            for (int i = 0; i < holder.rgOpciones.getChildCount(); i++) {
                                RadioButton other =
                                        (RadioButton) holder.rgOpciones.getChildAt(i);
                                Opcion op = pregunta.getOpciones().get(i);
                                if (op.isValido()) {
                                    other.setTextColor(Color.GREEN);
                                }
                            }
                        }

                        // 🚫 deshabilitar todas las opciones
                        for (int i = 0; i < holder.rgOpciones.getChildCount(); i++) {
                            holder.rgOpciones.getChildAt(i).setEnabled(false);
                        }

                        yaRespondio[0] = true;

                        preguntasRespondidas++;

                        if (preguntasRespondidas == preguntas.size() && finishListener != null) {
                            finishListener.onFinish(puntajeTotal);
                        }

                    }
                });

                holder.rgOpciones.addView(rb);
            }
        }


       // });
    }

    @Override
    public int getItemCount() {

        return this.preguntas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvLogo;
        private TextView tvTitulo;
        private RadioGroup rgOpciones;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvLogo = itemView.findViewById(R.id.cardpartpol_imv_logo);
            tvTitulo = itemView.findViewById(R.id.cardpartpol_tv_titulo);
            rgOpciones = itemView.findViewById(R.id.rgOpciones);
        }

    }
}
