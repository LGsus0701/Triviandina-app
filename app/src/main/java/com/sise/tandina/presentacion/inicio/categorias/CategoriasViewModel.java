package com.sise.tandina.presentacion.inicio.categorias;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sise.tandina.data.common.BaseResponse;
import com.sise.tandina.data.model.Categoria;
import com.sise.tandina.data.repository.CategoriaRepository;

import java.util.List;

public class CategoriasViewModel extends ViewModel {

    private CategoriaRepository categoriaRepository;

    public CategoriasViewModel() {
        this.categoriaRepository = new CategoriaRepository();
    }

    public LiveData<BaseResponse<List<Categoria>>> listarCategorias() {
        return this.categoriaRepository.listarCategorias();
    }

}
