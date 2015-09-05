/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.bean;

/**
 *
 * @author Miguel
 */
public class BLugar {
    private int idlugar;
    private String nombre;
    private String direccion;
    private float latitud;
    private float longitud;

    /**
     * @return the idlugar
     */
    public int getIdlugar() {
        return idlugar;
    }

    /**
     * @param idlugar the idlugar to set
     */
    public void setIdlugar(int idlugar) {
        this.idlugar = idlugar;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the latitud
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public float getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
    
}
