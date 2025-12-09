package com.sise.tandina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.sise.tandina.data.request.VerificarPersonaRequest;
import com.sise.tandina.presentacion.inicio.InicioActivity;
import com.sise.tandina.presentacion.inicio.InicioViewModel;

public class MainActivity extends AppCompatActivity {

//    EditText edtNombre;
//    Button btnJugar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        edtNombre = findViewById(R.id.main_edt_nombre);
//        btnJugar = findViewById(R.id.main_btn_jugar);
//
//        btnJugar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String nombre = edtNombre.getText().toString().trim();
//
//                if (nombre.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // PASAR AL ACTIVITY DE CATEGORÍAS
//                Intent intent = new Intent(MainActivity.this, InicioActivity.class);
//                intent.putExtra("nombreJugador", nombre);
//                startActivity(intent);
//            }
//        });
//    }



        EditText edtNombre;
        Button btnJugar;
        private InicioViewModel inicioViewModel;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            edtNombre = findViewById(R.id.main_edt_nombre);
            btnJugar = findViewById(R.id.main_btn_jugar);

            inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);

            btnJugar.setOnClickListener(v -> {

                String nombre = edtNombre.getText().toString().trim();

                if (nombre.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show();
                    return;
                }

                // request
                VerificarPersonaRequest request = new VerificarPersonaRequest();
                request.setNombre(nombre);

                // VALIDACIÓN AQUÍ
                inicioViewModel.verificarPersona(request).observe(this, response -> {

                    if (!response.isSuccess()) {
                        Toast.makeText(this, response.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        // PASAR A CATEGORÍAS (InicioActivity)
                        Intent intent = new Intent(MainActivity.this, InicioActivity.class);
                        intent.putExtra("nombreJugador", nombre);
                        startActivity(intent);
                    }
                });
            });
        }



}