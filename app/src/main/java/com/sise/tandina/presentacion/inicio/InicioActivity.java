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
//    private InicioViewModel inicioViewModel;
//    private final String TAG = InicioActivity.class.getSimpleName();
//    TextView txtTitulo, txtBienvenida;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_inicio);
//
//        txtTitulo = findViewById(R.id.categorias_txt_titulo);
//        txtBienvenida = findViewById(R.id.categorias_txt_bienvenida);
//
//        // obtengo el nombre enviado desde MainActivity
//        String nombre = getIntent().getStringExtra("nombreJugador");
//
//        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);
//
//        if (nombre != null && !nombre.isEmpty()) {
//            txtBienvenida.setText("Bienvenido " + nombre + ", validando datos…");
//        }
//
//        // Crear el request
//        VerificarPersonaRequest verificarPersonaRequest = new VerificarPersonaRequest();
//        verificarPersonaRequest.setNombre(nombre);
//
//        // llamada al api
//        inicioViewModel.verificarPersona(verificarPersonaRequest).observe(this, response -> {
//
//            if (!response.isSuccess()) {
//
//                // ❌ NO PERMITIR ENTRAR
//                Toast.makeText(this, response.getMessage(), Toast.LENGTH_LONG).show();
//
//                // Opcional: regresar a pantalla principal
//                finish();
//
//            } else {
//
//                // ✔ Nombre válido → entrar a categorías
//                Persona p = response.getData();
//
//                Toast.makeText(this,
//                        "Bienvenido " + p.getNombre() + " al sistema de juegos",
//                        Toast.LENGTH_LONG
//                ).show();
//
//                // Abrir categorías
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

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

        // AQUÍ YA NO VALIDAS NADA
        // SOLO MUESTRAS LAS CATEGORÍAS
    }
}