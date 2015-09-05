/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.bean;



/**
 *
 * @author kristiam
 */
public class BRecordatorio {

    private int idEvento;
    private int idcalendario;
    private String nombreEvento;
    private String nombreUsuario;
    private String correo;
    private String recordatorio;
    int activo;

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getActivo() {
        return activo;
    }

    public int getIdcalendario() {
        return idcalendario;
    }

    public void setIdcalendario(int idcalendario) {
        this.idcalendario = idcalendario;
    }

    
    public BRecordatorio() {
    }

    public BRecordatorio(int idEvento, String nombreEvento, String nombreUsuario, String correo, String recordatorio, int activo) {
        this.idEvento = idEvento;
        this.nombreEvento = nombreEvento;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.recordatorio = recordatorio;
        this.activo = activo;
    }
    
    

    /**
     * @return the nombreEvento
     */
    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * @param nombreEvento the nombreEvento to set
     */
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the recordatorio
     */
    public String getRecordatorio() {
        return recordatorio;
    }

    /**
     * @param recordatorio the recordatorio to set
     */
    public void setRecordatorio(String recordatorio) {
        this.recordatorio = recordatorio;
    }

    /**
     * @return the idEvento
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     * @param idEvento the idEvento to set
     */
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
    
    
    
    
}

    

