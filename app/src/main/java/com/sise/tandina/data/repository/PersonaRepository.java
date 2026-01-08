package com.sise.tandina.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sise.tandina.data.api.PersonaApi;
import com.sise.tandina.data.api.RetrofitClient;
import com.sise.tandina.data.common.BaseResponse;
import com.sise.tandina.data.model.Persona;
import com.sise.tandina.data.request.VerificarPersonaRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class    PersonaRepository {
    private final PersonaApi personaApi;

    private final String TAG = PersonaRepository.class.getSimpleName();

    public  PersonaRepository(){
        personaApi = RetrofitClient.getRetrofit().create(PersonaApi.class);
    }

    public LiveData<BaseResponse<Persona>> verificarPersona(VerificarPersonaRequest request){
        Log.i(TAG, "Iniciando verificarPesona");
        MutableLiveData<BaseResponse<Persona>> data = new MutableLiveData<>();
        personaApi.verificarPersona(request).enqueue(new Callback<BaseResponse<Persona>>() {
            @Override
            public void onResponse(Call<BaseResponse<Persona>> call, Response<BaseResponse<Persona>> response) {
                //Cuando la respuesta es satisfactoria +, codigo http 200 o similar
                if(response.isSuccessful() && response.body() != null){
                    data.setValue(response.body());
                }
                //Cunado la respuesta es error, codigo http 400 o 500
                if(response.errorBody() != null){
                    try {
                        String  errorJson = response.errorBody().string();
                        Log.i(TAG, "errorJson: "+errorJson);
                    } catch (Exception e) {
                        Log.e(TAG, "Error al convertir respuesta error api: "+e.getMessage());

                    }
                    data.setValue(BaseResponse.error("Ingrese un nombre valido"));
                }
            }

            //Cuando no hay conexion
            @Override
            public void onFailure(Call<BaseResponse<Persona>> call, Throwable throwable) {
                data.setValue(BaseResponse.error("Fallo de conexion: "+throwable.getMessage()));
            }
        });
        return data;
    }
}
