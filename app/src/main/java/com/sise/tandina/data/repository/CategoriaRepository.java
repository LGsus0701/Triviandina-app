package com.sise.tandina.data.repository;

import android.util.Log;

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

    private final String TAG = CategoriaRepository.class.getSimpleName();

    public CategoriaRepository() {
        categoriaApi = RetrofitClient.getRetrofit().create(CategoriaApi.class);
    }

    public LiveData<BaseResponse<List<Categoria>>> listarCategorias() {
        Log.i(TAG, "Iniciando peticion listarCategorias");
        MutableLiveData<BaseResponse<List<Categoria>>> data = new MutableLiveData<>();
        categoriaApi.listarCategorias().enqueue(new Callback<BaseResponse<List<Categoria>>>()  {

            @Override
            public void onResponse(Call<BaseResponse<List<Categoria>>> call, Response<BaseResponse<List<Categoria>>> response) {
                //Cuando la respuesta es satisfactoria, codigo http 200 o similar
                if(response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body());
                }

                //Cuando la respuesta es error, codigo http 400 o 500
                if(response.errorBody() != null) {
                    try {
                        String errorJson = response.errorBody().string();
                        Log.i(TAG, "errorJson: "+errorJson);
                    } catch (Exception e) {
                        Log.e(TAG, "Error al convertir respuesta error api: "+e.getMessage());
                    }
                    data.setValue(BaseResponse.error("El api devolvio un error"));
                }
            }

            //Cuando no hay conexion
            @Override
            public void onFailure(Call<BaseResponse<List<Categoria>>> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
                data.setValue(BaseResponse.error("Fallo de conexión"));
            }
        });
        return data;
    }

}
