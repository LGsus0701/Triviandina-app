package com.sise.tandina.data.model;

import java.util.Date;

import lombok.Data;

@Data
public class Persona {
    private int idPersona;
    private String nombre;
    private Date fechaRegistro;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
}
