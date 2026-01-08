package com.sise.tandina.presentacion.inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sise.tandina.data.common.BaseResponse;
import com.sise.tandina.data.model.Persona;
import com.sise.tandina.data.repository.PersonaRepository;
import com.sise.tandina.data.request.VerificarPersonaRequest;


public class InicioViewModel extends ViewModel {
    private final PersonaRepository personaRepository;

    public InicioViewModel() {
        personaRepository = new PersonaRepository();
    }
    public LiveData<BaseResponse<Persona>> verificarPersona(VerificarPersonaRequest request){
        return personaRepository.verificarPersona(request);
    }
}
