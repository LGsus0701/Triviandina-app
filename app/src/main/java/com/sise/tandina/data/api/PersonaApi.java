package com.sise.tandina.data.api;

import com.sise.tandina.data.common.BaseResponse;
import com.sise.tandina.data.model.Persona;
import com.sise.tandina.data.request.VerificarPersonaRequest;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PersonaApi {
    @POST("personas/verificar")
    Call<BaseResponse<Persona>> verificarPersona(@Body VerificarPersonaRequest request);

}
