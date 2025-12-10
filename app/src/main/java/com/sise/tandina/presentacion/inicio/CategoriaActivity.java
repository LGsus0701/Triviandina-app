package com.sise.tandina.presentacion.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.sise.tandina.R;

public class CategoriaActivity extends AppCompatActivity {

    private Button btnCategoriaCultura, btnCategoriaDeporte, btnCategoriaFarandula, btnCategoriaArte;
    private TextView txtBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        inicializarVistas();
        configurarEventos();
        mostrarBienvenida();
    }

    private void inicializarVistas() {
        txtBienvenida = findViewById(R.id.categorias_txt_bienvenida);

        btnCategoriaCultura = findViewById(R.id.btnCategoriaCultura);
        btnCategoriaDeporte = findViewById(R.id.btnCategoriaDeporte);
        btnCategoriaFarandula = findViewById(R.id.btnCategoriaFarandula);
        btnCategoriaArte = findViewById(R.id.btnCategoriaArte);
    }

    private void mostrarBienvenida() {
        // Si ya tienes un nombre enviado desde ValidacionActivity:
        String nombre = getIntent().getStringExtra("nombreJugador");

        if (nombre != null) {
            txtBienvenida.setText("Hola " + nombre + ", elige una categoría:");
        }
    }

    private void configurarEventos() {

        btnCategoriaCultura.setOnClickListener(v -> abrirPreguntas(1));
        btnCategoriaDeporte.setOnClickListener(v -> abrirPreguntas(2));
        btnCategoriaFarandula.setOnClickListener(v -> abrirPreguntas(3));
        btnCategoriaArte.setOnClickListener(v -> abrirPreguntas(4));
    }

    private void abrirPreguntas(int idCategoria) {
        Intent intent = new Intent(CategoriaActivity.this, PreguntaActivity.class);
        intent.putExtra("idCategoria", idCategoria);
        startActivity(intent);
    }


}
