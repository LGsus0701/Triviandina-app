package com.sise.tandina.presentacion.inicio.categorias;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sise.tandina.R;

import java.util.ArrayList;

public class CategoriasActivity extends AppCompatActivity {

    private CategoriasViewModel categoriasViewModel;
    private CardCategoriaAdapter cardCategoriaAdapter;
    private RecyclerView recyclerView;

    private final String TAG = CategoriasActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categorias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.actelectvig_rcv_contenedor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardCategoriaAdapter = new CardCategoriaAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(cardCategoriaAdapter);
        categoriasViewModel = new ViewModelProvider(this).get(CategoriasViewModel.class);
        configViewModel();

    }

    private void configViewModel() {
        categoriasViewModel.listarCategorias().observe(this, response -> {
            Log.i(TAG,"configViewModel");
            if(response.isSuccess()) {
                cardCategoriaAdapter.actualizarLista(response.getData());
            }
        });
    }
}