package com.sise.tandina.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sise.tandina.data.api.CategoriaApi;
import com.sise.tandina.data.api.RetrofitClient;
import com.sise.tandina.data.common.BaseResponse;
import com.sise.tandina.data.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaRepository {

    private final CategoriaApi categoriaApi;

    public CategoriaRepository() {
        categoriaApi = RetrofitClient.getRetrofit().create(CategoriaApi.class);
    }

    public LiveData<List<Categoria>> listarCategorias() {
        MutableLiveData<List<Categoria>> data = new MutableLiveData<>();

        categoriaApi.listarCategorias().enqueue(new Callback<BaseResponse<List<Categoria>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Categoria>>> call,
                                   Response<BaseResponse<List<Categoria>>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body().getData());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Categoria>>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
