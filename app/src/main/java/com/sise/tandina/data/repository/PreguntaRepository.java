package com.sise.tandina.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sise.tandina.data.api.PreguntaApi;
import com.sise.tandina.data.api.RetrofitClient;
import com.sise.tandina.data.common.BaseResponse;
import com.sise.tandina.data.model.Pregunta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreguntaRepository {

    private final PreguntaApi preguntaApi;

    public PreguntaRepository() {
        preguntaApi = RetrofitClient.getRetrofit().create(PreguntaApi.class);
    }

    public LiveData<Pregunta> obtenerPreguntaPorCategoria(int idCategoria) {
        MutableLiveData<Pregunta> data = new MutableLiveData<>();

        preguntaApi.obtenerPreguntaPorCategoria(idCategoria)
                .enqueue(new Callback<BaseResponse<Pregunta>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<Pregunta>> call,
                                           Response<BaseResponse<Pregunta>> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            data.setValue(response.body().getData());
                        } else {
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<Pregunta>> call, Throwable t) {
                        data.setValue(null);
                    }
                });

        return data;
    }
}
