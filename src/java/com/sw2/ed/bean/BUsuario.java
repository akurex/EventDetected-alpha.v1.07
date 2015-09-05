/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.bean;

/**
 *
 * @author Miguel
 */
public class BUsuario {
    private int idusuario;
    private String nombre = "";
    private String apellido = "";
    private String usuario = "";
    private String contrasena= "";
    private String correo= "";
    private int administrador;
    private int estado_logueo;
    private boolean flag;

    public BUsuario() {
    }
    /**
     * @return the idusuario
     */
    
    
    public int getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
     * @return the administrador
     */
    public int getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }

    /**
     * @return the estado_logueo
     */
    public int getEstado_logueo() {
        return estado_logueo;
    }

    /**
     * @param estado_logueo the estado_logueo to set
     */
    public void setEstado_logueo(int estado_logueo) {
        this.estado_logueo = estado_logueo;
    }

    /**
     * @return the flag
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
}
