/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.dao;

import com.sw2.ed.bean.BUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class DLogin extends DBase {

    public BUsuario ValidarUsuario(String user, String pass) {
        BUsuario buser = null;

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String llave = "llave";
            String query = "SELECT u.usuario, u.contrasena,u.administrador, u.idusuario, u.estado_logueo, u.correo  FROM usuario u "
                    + "WHERE u.usuario='" + user + "'"
                    + " AND AES_DECRYPT(u.contrasena,'"+llave+"')='"+pass+"'";
            pstmt = conn.prepareStatement(query);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                buser = new BUsuario();
                buser.setUsuario(rs.getString(1));
                buser.setContrasena(rs.getString(2));
                buser.setAdministrador(rs.getInt(3));
                buser.setIdusuario(rs.getInt(4));
                buser.setEstado_logueo(rs.getInt(5));
                buser.setCorreo(rs.getString(6));
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
             System.err.println("Error al ejecutar el query");
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ;
                }
                rs = null;
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ;
                }
                pstmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ;
                }
                conn = null;
            }
        }
        return buser;

    }
    
    
    public BUsuario ValidarCorreo(String correo) {

        BUsuario usuario = null;
        this.init_mysql();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
             String query = "select u.contrasena, u.correo from usuario u "
                    + "where u.correo = ?";
            
            pst = conn.prepareStatement(query);
            pst.setString(1, correo);
            rs = pst.executeQuery();
            while (rs.next()) {                
                
                usuario = new BUsuario();
                usuario.setContrasena(rs.getString(1));
                usuario.setCorreo(rs.getString(2));
            }
            rs.close();
            pst.close();
            conn.close();
            
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar el query");
            ex.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ;
                }
                rs = null;
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    ;
                }
                pst = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ;
                }
                conn = null;
            }
        }
        return usuario;
    }
    
    public void cambiarContraseña(String correo, String contraseña) {

        BUsuario usuario = null;
        this.init_mysql();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String query = "update usuario set contrasena = AES_ENCRYPT(?,'llave') where correo = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, contraseña);
            pst.setString(2, correo);
            pst.executeUpdate();
            //while (rs.next()) {                
            //  usuario = new BUsuario();
            //  usuario.setContrasena(rs.getString(1));
            //  usuario.setCorreo(rs.getString(2));

            //}
            pst.close();

            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar el query");
            ex.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    ;
                }
                pst = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ;
                }
                conn = null;
            }
        }
        //return usuario;
    }

        public void cambiarContraseña(int idusuario, String contraseña) {

        
        this.init_mysql();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String query = "update usuario set contrasena = AES_ENCRYPT(?,'llave') where idusuario = ?;";
            
            pst = conn.prepareStatement(query);
            pst.setString(1, contraseña);
            pst.setInt(2, idusuario);
            pst.executeUpdate();
            //while (rs.next()) {                
            //  usuario = new BUsuario();
            //  usuario.setContrasena(rs.getString(1));
            //  usuario.setCorreo(rs.getString(2));

            //}
            pst.close();

            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar el query");
            ex.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    ;
                }
                pst = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ;
                }
                conn = null;
            }
        }
        //return usuario;
    }

    public void setToken(String token, String correo) {

        this.init_mysql();
        PreparedStatement pstmt = null;
        try {


            String query = "Update usuario Set token=? where correo=?;";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, token);
            pstmt.setString(2, correo);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ;
                }
                pstmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ;
                }
                conn = null;
            }
        }

    }

    public BUsuario usuarioXToken(String token) {

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs;
        BUsuario bUsuario=null;
        try {


            String query = "Select nombre, idusuario, correo from usuario u where u.token=?";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, token);


            pstmt.executeQuery();
            rs = pstmt.getResultSet();

            while (rs.next()) {
                bUsuario= new BUsuario();
                bUsuario.setNombre(rs.getString(1));
                bUsuario.setIdusuario(rs.getInt(2));
                bUsuario.setCorreo(rs.getString(3));

            }
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ;
                }
                pstmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ;
                }
                conn = null;
            }
        }
        return bUsuario;
    }
    
}
