package com.sise.tandina.data.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data

public class Pregunta {

    private int idPregunta;
    private Categoria categoria ;
    private String pregunta;
    private String urlImagen;
    private String dificultad;
    private String estadoAuditoria;

    //
    private List<Opcion> opciones;


    public List<Opcion> getOpciones() {
        return opciones;
    }
}
