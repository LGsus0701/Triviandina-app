package com.sise.tandina.presentacion.inicio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
import com.sise.tandina.presentacion.inicio.categorias.CategoriasActivity;

public class InicioActivity extends AppCompatActivity {

    private final String TAG = InicioActivity.class.getSimpleName();
    EditText edtNombre;
    Button btnJugar;
    private InicioViewModel inicioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNombre = findViewById(R.id.main_edt_nombre);
        btnJugar = findViewById(R.id.main_btn_jugar);

        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        btnJugar.setOnClickListener(v -> {

            String nombre = edtNombre.getText().toString().trim();

            if (nombre.isEmpty()) {
                Toast.makeText(InicioActivity.this, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent(this, CategoriasActivity.class);
                    intent.putExtra("nombreJugador", nombre);
                    startActivity(intent);
                    // opcional
                    // finish();
                }

            });
        });
    }

}