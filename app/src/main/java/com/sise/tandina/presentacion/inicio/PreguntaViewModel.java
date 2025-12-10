package com.sise.tandina.presentacion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sise.tandina.data.model.Pregunta;
import com.sise.tandina.data.repository.PreguntaRepository;

public class PreguntaViewModel extends ViewModel {

    private final PreguntaRepository preguntaRepository;

    public PreguntaViewModel() {
        preguntaRepository = new PreguntaRepository();
    }

    public LiveData<Pregunta> getPregunta(int idCategoria) {
        return preguntaRepository.obtenerPreguntaPorCategoria(idCategoria);
    }
}
