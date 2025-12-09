//package com.sise.tandina.data.repository;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import com.sise.tandina.data.api.PreguntaApi;
//import com.sise.tandina.data.model.Pregunta;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class PreguntaRepository {
//    private final PreguntaApi api;
//
//    public PreguntaRepository() {
//        api = ApiClient.getClient().create(PreguntaApi.class);
//    }
//
//    public LiveData<List<Pregunta>> listarPregunta(String categoria) {
//        MutableLiveData<List<Pregunta>> data = new MutableLiveData<>();
//
//        api.listarPregunta(categoria).enqueue(new Callback<List<Pregunta>>() {
//            @Override
//            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
//                if (response.isSuccessful()) {
//                    data.setValue(response.body());
//                } else {
//                    data.setValue(null);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
//                data.setValue(null);
//            }
//        });
//
//        return data;
//    }
//}
