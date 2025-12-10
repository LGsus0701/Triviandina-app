package com.sise.tandina.presentacion.inicio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sise.tandina.R;


import androidx.appcompat.app.AppCompatActivity;

public class PreguntaActivity extends AppCompatActivity {
    private TextView txtCategoria, txtPregunta, txtTiempo;
    private ImageView imgPregunta;
    private Button btnOp1, btnOp2, btnOp3, btnOp4;

    private int idCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas); // Asegúrate que el layout se llame así

        inicializarVistas();
        recibirDatos();
        mostrarTituloCategoria();
    }

    private void inicializarVistas() {
        txtCategoria = findViewById(R.id.txtCategoria);
        txtPregunta = findViewById(R.id.txtPregunta);
        txtTiempo = findViewById(R.id.txtTiempo);

        imgPregunta = findViewById(R.id.imgPregunta);

        btnOp1 = findViewById(R.id.btnOp1);
        btnOp2 = findViewById(R.id.btnOp2);
        btnOp3 = findViewById(R.id.btnOp3);
        btnOp4 = findViewById(R.id.btnOp4);
    }

    private void recibirDatos() {
        idCategoria = getIntent().getIntExtra("idCategoria", -1);
    }

    private void mostrarTituloCategoria() {

        String nombreCategoria;

        switch (idCategoria) {
            case 1: nombreCategoria = "Cultura General"; break;
            case 2: nombreCategoria = "Deporte"; break;
            case 3: nombreCategoria = "Farandula"; break;
            case 4: nombreCategoria = "Arte"; break;
            default: nombreCategoria = "Categoría Desconocida"; break;
        }

        txtCategoria.setText("Categoría: " + nombreCategoria);
    }
}
