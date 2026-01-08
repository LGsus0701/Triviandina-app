package com.sise.tandina.presentacion.inicio.preguntas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sise.tandina.R;

import java.util.ArrayList;

public class PreguntasActivity extends AppCompatActivity {


    private PreguntaViewModel preguntaViewModel;
    private CardPreguntaAdapter cardPreguntaAdapter;
    private RecyclerView recyclerView;
    private final String TAG = PreguntasActivity.class.getSimpleName();
    private TextView tvPuntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preguntas);
        // 🔽 AGREGADO
        tvPuntaje = findViewById(R.id.tvPuntaje);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.actpartpol_rcv_contenedor);
        recyclerView.setLayoutManager(
                new androidx.recyclerview.widget.LinearLayoutManager(this)
        );
        cardPreguntaAdapter = new CardPreguntaAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(cardPreguntaAdapter);
        // 🔽 AGREGADO (ESTO ES LO CLAVE)
        cardPreguntaAdapter.setOnPuntajeChangeListener(puntaje -> {
            tvPuntaje.setText("Puntaje: " + puntaje);

            //aqui

            cardPreguntaAdapter.setOnQuizFinishListener(puntajeFinal -> {
                new AlertDialog.Builder(this)
                        .setTitle("Juego terminado")
                        .setMessage("Tu puntaje final es: " + puntajeFinal)
                        .setPositiveButton("Aceptar", null)
                        .show();
            });


        });
        preguntaViewModel = new ViewModelProvider(this).get(PreguntaViewModel.class);
        configViewModel();

    }

    private void configViewModel() {
        Intent intent = getIntent();
        Integer idCategoria = intent.getIntExtra("ID_CATEGORIA", 1);
        preguntaViewModel.listarPreguntasPorCategoria(idCategoria).observe(this, response -> {
            Log.i(TAG,"configViewModel");
            if(response.isSuccess()) {
                cardPreguntaAdapter.actualizarLista(response.getData());
            }



        });
    }
}