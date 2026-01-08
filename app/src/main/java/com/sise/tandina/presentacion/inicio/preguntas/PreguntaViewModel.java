package com.sise.tandina.presentacion.inicio.preguntas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sise.tandina.data.common.BaseResponse;
import com.sise.tandina.data.model.Pregunta;
import com.sise.tandina.data.repository.PreguntaRepository;

import java.util.List;

public class PreguntaViewModel extends ViewModel {

    private PreguntaRepository preguntaRepository;

    public PreguntaViewModel() {
        this.preguntaRepository = new PreguntaRepository();
    }

    public LiveData<BaseResponse<List<Pregunta>>> listarPreguntasPorCategoria(int idCategoria) {
        return this.preguntaRepository.listarPreguntasPorCategoria(idCategoria);
    }
}
