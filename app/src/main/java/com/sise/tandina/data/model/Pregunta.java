package com.sise.tandina.data.model;

import java.io.Serializable;

public class Pregunta implements Serializable {

    private int idPregunta;
    private int idCategoria;
    private String imagenUrl;
    private String pregunta;
    private String dificultad;


    public int getIdPregunta() {return idPregunta; }
    public String getPregunta() { return pregunta; }
    public String getImagenUrl() { return imagenUrl; }
    public String getDificultad() { return dificultad; }


}
