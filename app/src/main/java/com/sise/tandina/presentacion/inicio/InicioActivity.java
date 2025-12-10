package com.sise.tandina.presentacion.inicio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.sise.tandina.MainActivity;
import com.sise.tandina.R;
import com.sise.tandina.data.model.Persona;
import com.sise.tandina.data.request.VerificarPersonaRequest;

public class InicioActivity extends AppCompatActivity {

    TextView txtTitulo, txtBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        txtTitulo = findViewById(R.id.categorias_txt_titulo);
        txtBienvenida = findViewById(R.id.categorias_txt_bienvenida);

        // obtengo el nombre enviado desde MainActivity
        String nombre = getIntent().getStringExtra("nombreJugador");

        if (nombre != null && !nombre.isEmpty()) {
            txtBienvenida.setText("Bienvenido " + nombre + ", elige una categoría");
        }


    }
}