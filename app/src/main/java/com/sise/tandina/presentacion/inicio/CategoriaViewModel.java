package com.sise.tandina.presentacion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sise.tandina.data.model.Categoria;
import com.sise.tandina.data.repository.CategoriaRepository;

import java.util.List;

public class CategoriaViewModel extends ViewModel {

    private final CategoriaRepository categoriaRepository;

    public CategoriaViewModel() {
        categoriaRepository = new CategoriaRepository();
    }

    public LiveData<List<Categoria>> getCategorias() {
        return categoriaRepository.listarCategorias();
    }
}
