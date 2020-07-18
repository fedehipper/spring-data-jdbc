package com.demo.jdbc.domain;

public enum Regional {

    BUENOS_AIRES("Buenos Aires"),
    LA_PLATA("La Plata"),
    ROSARIO("Rosario"),
    TUCUMAN("Tucuman");

    private final String descripcion;

    Regional(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
