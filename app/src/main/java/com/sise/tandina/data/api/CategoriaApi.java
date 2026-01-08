package com.sise.tandina.data.api;

import com.sise.tandina.data.common.BaseResponse;
import com.sise.tandina.data.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriaApi {
    @GET("categorias")
    Call<BaseResponse<List<Categoria>>> listarCategorias();
}
