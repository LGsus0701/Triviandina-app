package com.sise.tandina.data.api;

import com.sise.tandina.data.common.BaseResponse;
import com.sise.tandina.data.model.Pregunta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PreguntaApi {
    @GET("preguntas/categoria/{idCategoria}")
    Call<BaseResponse<Pregunta>> obtenerPreguntaPorCategoria(
            @Path("idCategoria") int idCategoria

    );


}
