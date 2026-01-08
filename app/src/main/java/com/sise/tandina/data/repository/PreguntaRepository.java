package com.sise.tandina.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sise.tandina.data.api.PreguntaApi;
import com.sise.tandina.data.api.RetrofitClient;
import com.sise.tandina.data.common.BaseResponse;
import com.sise.tandina.data.model.Pregunta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreguntaRepository {

    private final PreguntaApi preguntaApi;
    private final String TAG = PreguntaRepository.class.getSimpleName();

    public PreguntaRepository() {
        preguntaApi = RetrofitClient.getRetrofit().create(PreguntaApi.class);
    }


//AGREGADO///////
    public LiveData<BaseResponse<List<Pregunta>>> listarPreguntasPorCategoria(int idCategoria) {
        Log.i(TAG, "Iniciando peticion listarPreguntasPorCategoria");
        MutableLiveData<BaseResponse<List<Pregunta>>> data = new MutableLiveData<>();
        preguntaApi.listarPreguntasPorCategoria(idCategoria).enqueue(new Callback<BaseResponse<List<Pregunta>>>() {

            @Override
            public void onResponse(Call<BaseResponse<List<Pregunta>>> call, Response<BaseResponse<List<Pregunta>>> response) {
                //Cuando la respuesta es satisfactoria, codigo http 200 o similar
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body());
                }

                //Cuando la respuesta es error, codigo http 400 o 500
                if (response.errorBody() != null) {
                    try {
                        String errorJson = response.errorBody().string();
                        Log.i(TAG, "errorJson: " + errorJson);
                    } catch (Exception e) {
                        Log.e(TAG, "Error al convertir respuesta error api: " + e.getMessage());
                    }
                    data.setValue(BaseResponse.error("El api devolvio un error"));
                }
            }

            //Cuando no hay conexion
            @Override
            public void onFailure(Call<BaseResponse<List<Pregunta>>> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
                data.setValue(BaseResponse.error("Fallo de conexión"));
            }
        });
        return data;
    }



}
