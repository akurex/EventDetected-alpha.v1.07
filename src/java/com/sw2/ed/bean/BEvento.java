/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.bean;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Miguel
 */
public class BEvento {
    private int idevento;
    private String nombre;
    private String descripcion;
    private Timestamp fecha;
    private Date fecha1;
    private int duracion;
    private int activo;
    private BLugar idlugar;
    private int cancelado;

    public void setCancelado(int cancelado) {
        this.cancelado = cancelado;
    }

    public int getCancelado() {
        return cancelado;
    }
    
    
    
    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha1() {
        return fecha1;
    }

    
    /**
     * @return the idevento
     */
    public int getIdevento() {
        return idevento;
    }

    /**
     * @param idevento the idevento to set
     */
    public void setIdevento(int idevento) {
        this.idevento = idevento;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

  

    /**
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the activo
     */
    public int getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(int activo) {
        this.activo = activo;
    }

    /**
     * @return the idlugar
     */
    public BLugar getIdlugar() {
        return idlugar;
    }
    
    /**
     * @param idlugar the idlugar to set
     */
    public void setIdlugar(BLugar idlugar) {
        this.idlugar = idlugar;
    }
    
}
