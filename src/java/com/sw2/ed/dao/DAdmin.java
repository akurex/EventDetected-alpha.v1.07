/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.dao;

import com.sw2.ed.bean.BEvento;
import com.sw2.ed.bean.BLugar;
import com.sw2.ed.bean.BUsuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Christian
 */
public class DAdmin extends DBase {

    public void CambiarActivo(BEvento bEvento) {
        ResultSet res = null;
        this.init_mysql();
        PreparedStatement pstmt = null;
        try {

            String query = "UPDATE evento SET evento.activo=0 "
                    + "WHERE evento.fecha<now() and evento.idevento=?;";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bEvento.getIdevento());

            pstmt.executeUpdate();
            conn.commit();

            res.close();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException ex) {
                    ;
                }
                res = null;
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

    }

    public ArrayList<BUsuario> ListarUsuario() {

        BUsuario buser = null;
        ArrayList<BUsuario> L = new ArrayList<BUsuario>();

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT u.idusuario,u.nombre,u.apellido,u.usuario, u.contrasena,u.correo,"
                    + "u.administrador,u.estado_logueo FROM usuario u where u.administrador='0'";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                buser = new BUsuario();
                buser.setIdusuario(rs.getInt(1));
                buser.setNombre(rs.getString(2));
                buser.setApellido(rs.getString(3));
                buser.setUsuario(rs.getString(4));
                buser.setContrasena(rs.getString(5));
                buser.setCorreo(rs.getString(6));
                buser.setAdministrador(rs.getInt(7));
                buser.setEstado_logueo(rs.getInt(8));

                L.add(buser);
            }



            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
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
        return L;

    }

    public ArrayList<BEvento> ListarEvento() {
        ArrayList<BEvento> li = new ArrayList<BEvento>();

        BEvento bevento = null;
        BLugar blugar = null;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT e.idevento, e.nombre, e.descripcion, e.fecha, e.duracion, e.activo , e.cancelado , l.idlugar,"
                    + "l.nombre, l.latitud,l.longitud from evento e inner join lugar l on e.idlugar=l.idlugar";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bevento = new BEvento();
                bevento.setIdevento(rs.getInt(1));
                bevento.setNombre(rs.getString(2));
                bevento.setDescripcion(rs.getString(3));
                bevento.setFecha(rs.getTimestamp(4));
                bevento.setDuracion(rs.getInt(5));
                bevento.setActivo(rs.getInt(6));
                bevento.setCancelado(rs.getInt(7));

                blugar = new BLugar();
                blugar.setIdlugar(rs.getInt(8));
                blugar.setNombre(rs.getString(9));
                blugar.setLatitud(rs.getFloat(10));
                blugar.setLongitud(rs.getFloat(11));


                bevento.setIdlugar(blugar);

                li.add(bevento);
            }



            rs.close();
            pstmt.close();
            conn.close();



        } catch (SQLException ex) {
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

        return li;

    }

    public BUsuario DataUsuario(int iduser) {
        BUsuario buser = null;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT u.idusuario,u.nombre,u.apellido,u.usuario,u.correo,"
                    + "u.administrador,u.estado_logueo FROM usuario u where u.idusuario=?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, iduser);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                buser = new BUsuario();
                buser.setIdusuario(rs.getInt(1));
                buser.setNombre(rs.getString(2));
                buser.setApellido(rs.getString(3));
                buser.setUsuario(rs.getString(4));
                //buser.setContrasena(rs.getString(5));
                buser.setCorreo(rs.getString(5));
                buser.setAdministrador(rs.getInt(6));
                buser.setEstado_logueo(rs.getInt(7));


            }



            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAdmin.class.getName()).log(Level.SEVERE, null, ex);
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

    public int EstadoLogueoxUsuario(int iduser) {
        int estado = 0;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT u.estado_logueo FROM usuario u where u.idusuario=?;";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, iduser);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                estado = rs.getInt(1);

            }



            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAdmin.class.getName()).log(Level.SEVERE, null, ex);
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

        return estado;


    }

    public void GuardaUsuario(BUsuario buser) {

        this.init_mysql();
        PreparedStatement pstmt = null;
        try {

            String query = "UPDATE usuario u SET u.nombre = ?, u.apellido = ?, u.usuario = ? , u.correo = ?, u.contrasena = ? , u.estado_logueo = ? "
                    + " WHERE u.idusuario = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, buser.getNombre());
            pstmt.setString(2, buser.getApellido());
            pstmt.setString(3, buser.getUsuario());
            pstmt.setString(4, buser.getCorreo());
            pstmt.setString(5, buser.getContrasena());
            pstmt.setInt(6, buser.getEstado_logueo());
            pstmt.setInt(7, buser.getIdusuario());

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

    public BEvento DataEvento(int idevent) {
        BEvento bevent = null;
        BLugar blugar = null;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT e.idevento, e.nombre, e.descripcion, e.fecha, e.duracion, e.activo, e.cancelado, l.idlugar, "
                    + "l.nombre,l.direccion,l.latitud,l.longitud from evento e inner join lugar l on e.idlugar=l.idlugar where e.idevento=?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idevent);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bevent = new BEvento();
                bevent.setIdevento(rs.getInt(1));
                bevent.setNombre(rs.getString(2));
                bevent.setDescripcion(rs.getString(3));
                bevent.setFecha(rs.getTimestamp(4));
                bevent.setDuracion(rs.getInt(5));
                bevent.setActivo(rs.getInt(6));
                bevent.setCancelado(rs.getInt(7));

                blugar = new BLugar();
                blugar.setIdlugar(rs.getInt(8));
                blugar.setNombre(rs.getString(9));
                blugar.setDireccion(rs.getString(10));
                blugar.setLatitud(rs.getFloat(11));
                blugar.setLongitud(rs.getFloat(12));

                bevent.setIdlugar(blugar);

            }



            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAdmin.class.getName()).log(Level.SEVERE, null, ex);
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


        return bevent;
    }

    public void GuardarEvento(BEvento bEvento) {

        this.init_mysql();
        PreparedStatement pstmt = null;
        try {
//        
//            String query = "UPDATE evento e, lugar l SET e.nombre = ?, e.descripcion = ?, e.duracion = ?, e.activo = ? , l.nombre = ?, l.direccion = ? "
//                    + " WHERE e.idevento = ? AND l.idlugar = ? AND e.idlugar = l.idlugar";
//            String query = "UPDATE evento e inner join lugar lu on e.idlugar=lu.idlugar "
//                    + " SET e.nombre = ? ,e.descripcion=?,e.duracion=?, lu.nombre=?, "
//                    + " lu.direccion=?, e.activo=? WHERE e.idevento = ?";
            String query = "UPDATE evento e inner join lugar lu on e.idlugar=lu.idlugar "
                    + " SET e.nombre = ?, e.descripcion=? WHERE e.idevento = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bEvento.getNombre());
            pstmt.setString(2, bEvento.getDescripcion());
//            pstmt.setInt(3, bEvento.getDuracion());
//            pstmt.setString(4, bEvento.getIdlugar().getNombre());
//            pstmt.setString(5, bEvento.getIdlugar().getDireccion());
//            pstmt.setInt(6, bEvento.getActivo());
            pstmt.setInt(3, bEvento.getIdevento());



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

    public void GuardarEvento(BEvento bEvento, String f1) {

        this.init_mysql();
        PreparedStatement pstmt = null;
        try {
//        
//            String query = "UPDATE evento e, lugar l SET e.nombre = ?, e.descripcion = ?, e.duracion = ?, e.activo = ? , l.nombre = ?, l.direccion = ? "
//                    + " WHERE e.idevento = ? AND l.idlugar = ? AND e.idlugar = l.idlugar";
//            String query = "UPDATE evento e inner join lugar lu on e.idlugar=lu.idlugar "
//                    + " SET e.nombre = ? ,e.descripcion=?,e.duracion=?, lu.nombre=?, "
//                    + " lu.direccion=?, e.activo=?, e.fecha=STR_TO_DATE(?,'%m/%d/%Y %H:%i') WHERE e.idevento = ?";
            String query = "UPDATE evento e inner join lugar lu on e.idlugar=lu.idlugar "
                    + " SET e.nombre = ? , e.descripcion=?, e.duracion=?, lu.nombre=?, "
                    + " lu.direccion=?, e.activo=?, e.fecha=STR_TO_DATE(?,'%Y/%m/%d %H:%i:%s'), e.cancelado=? WHERE e.idevento = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bEvento.getNombre());
            pstmt.setString(2, bEvento.getDescripcion());
            pstmt.setInt(3, bEvento.getDuracion());
            pstmt.setString(4, bEvento.getIdlugar().getNombre());
            pstmt.setString(5, bEvento.getIdlugar().getDireccion());
            pstmt.setInt(6, bEvento.getActivo());
            pstmt.setString(7, f1);
            pstmt.setInt(8, bEvento.getCancelado());
            pstmt.setInt(9, bEvento.getIdevento());



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

    public void EliminarUsuario(int iduser) {

        this.init_mysql();
        PreparedStatement pstmt = null;
        try {

            String query = "DELETE FROM usuario u WHERE u.idusuario= ?";


            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, iduser);

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

    public boolean CrearUsuario(BUsuario buser) {

        this.init_mysql();
        boolean x = true;
        PreparedStatement pstmt = null;
        try {

            String query = "INSERT INTO usuario (nombre , apellido, usuario , contrasena, correo, estado_logueo,administrador) VALUES (?,?, ?, ?, ?, ?,0)";


            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, buser.getNombre());
            pstmt.setString(2, buser.getApellido());
            pstmt.setString(3, buser.getUsuario());
            pstmt.setString(4, buser.getContrasena());
            pstmt.setString(5, buser.getCorreo());
            pstmt.setInt(6, buser.getEstado_logueo());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(DAdmin.class.getName()).log(Level.SEVERE, null, ex);
            x = false;
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

        return x;
    }

    public int validarCaracter(String input, String patron) {
        int a = 0;
        Pattern p;
        Matcher m;
        //String input = "aer@opucppe";
        //p = Pattern.compile("[^a-zA-Z0-9]");
        //p = Pattern.compile("[^A-Za-z0-9.@_-~#]+"); //validación para correo
        //p = Pattern.compile("[^a-zA-Z0]"); //validación para nombre
        p = Pattern.compile(patron);
        m = p.matcher(input);

        StringBuffer sb = new StringBuffer();
        boolean resultado = m.find();

        boolean caracteresIlegales = false;

        while (resultado) {
            caracteresIlegales = true;
            m.appendReplacement(sb, "");
            resultado = m.find();
        }

        // Añade el ultimo segmento de la entrada a la cadena
        m.appendTail(sb);
        input = sb.toString();

        //System.out.println(resultado);
        //System.out.println(caracteresIlegales);
        if (caracteresIlegales) {
            //System.out.println("La cadena contiene caracteres ilegales");
            a = 1; //cadena contiene caracteres ilegales

        }

        return a;
    }

    public ArrayList<BEvento> ListarEvento(String desde, String hasta) {
        ArrayList<BEvento> li = new ArrayList<BEvento>();

        BEvento bevento = null;
        BLugar blugar = null;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT e.idevento, e.nombre, e.descripcion, e.fecha, "
                    + " e.duracion, e.activo, l.idlugar,"
                    + " l.nombre, l.latitud,l.longitud from evento e inner join "
                    + " lugar l on e.idlugar=l.idlugar"
                    + " where e.fecha between"
                    + " STR_TO_DATE(?,'%d/%m/%Y') "
                    + " and STR_TO_DATE(?,'%d/%m/%Y')";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, desde);
            pstmt.setString(2, hasta);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bevento = new BEvento();
                bevento.setIdevento(rs.getInt(1));
                bevento.setNombre(rs.getString(2));
                bevento.setDescripcion(rs.getString(3));
                bevento.setFecha1(rs.getDate(4));
                bevento.setDuracion(rs.getInt(5));
                bevento.setActivo(rs.getInt(6));

                blugar = new BLugar();
                blugar.setIdlugar(rs.getInt(7));
                blugar.setNombre(rs.getString(8));
                blugar.setLatitud(rs.getFloat(9));
                blugar.setLongitud(rs.getFloat(10));


                bevento.setIdlugar(blugar);

                li.add(bevento);
            }



            rs.close();
            pstmt.close();
            conn.close();



        } catch (SQLException ex) {
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

        return li;

    }

    public void NuevoLugar(BLugar blu) {

        this.init_mysql();
        PreparedStatement pstmt = null;
        try {

            String query = "INSERT INTO lugar (nombre , direccion, latitud , longitud)"
                    + " VALUES (?,?, ?, ?)";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, blu.getNombre());
            pstmt.setString(2, blu.getDireccion());
            pstmt.setFloat(3, blu.getLatitud());
            pstmt.setFloat(4, blu.getLongitud());


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

    //NUEVO AGREGADO
    public ArrayList<BLugar> ListarLugares() {
        ArrayList<BLugar> li = new ArrayList<BLugar>();


        BLugar blugar = null;

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT l.idlugar,"
                    + "l.nombre,l.direccion, l.latitud,l.longitud from lugar l";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                blugar = new BLugar();
                blugar.setIdlugar(rs.getInt(1));
                blugar.setNombre(rs.getString(2));
                blugar.setDireccion(rs.getString(3));
                blugar.setLatitud(rs.getFloat(4));
                blugar.setLongitud(rs.getFloat(5));

                li.add(blugar);
            }



            rs.close();
            pstmt.close();
            conn.close();



        } catch (SQLException ex) {
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

        return li;

    }
    // NUEVO

    public BLugar DataLugar(int idlu) {

        BLugar lu = null;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT u.idlugar,u.nombre,u.direccion,u.latitud, u.longitud"
                    + " FROM lugar u where u.idlugar=?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idlu);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                lu = new BLugar();
                lu.setIdlugar(rs.getInt(1));
                lu.setNombre(rs.getString(2));
                lu.setDireccion(rs.getString(3));
                lu.setLatitud(Float.parseFloat(rs.getString(4)));
                lu.setLongitud(Float.parseFloat(rs.getString(5)));
            }



            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAdmin.class.getName()).log(Level.SEVERE, null, ex);
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

        return lu;

    }

    public void CrearEvento(BEvento bEvento) {

        this.init_mysql();
        PreparedStatement pstmt = null;
        try {
//        
//            String query = "UPDATE evento e, lugar l SET e.nombre = ?, e.descripcion = ?, e.duracion = ?, e.activo = ? , l.nombre = ?, l.direccion = ? "
//                    + " WHERE e.idevento = ? AND l.idlugar = ? AND e.idlugar = l.idlugar";
            String query = "INSERT INTO evento (nombre,descripcion,fecha,duracion,activo,idlugar) VALUES (?,?,?,?,?,?)";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bEvento.getNombre());
            pstmt.setString(2, bEvento.getDescripcion());
            pstmt.setInt(3, bEvento.getDuracion());
            pstmt.setInt(4, bEvento.getIdlugar().getIdlugar());
            pstmt.setInt(6, bEvento.getActivo());
            pstmt.setInt(7, bEvento.getIdevento());



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

    public void CrearEvento(BEvento bEvento, String f1, int idlugar) {
        this.init_mysql();
        PreparedStatement pstmt = null;
        try {
//        
//            String query = "UPDATE evento e, lugar l SET e.nombre = ?, e.descripcion = ?, e.duracion = ?, e.activo = ? , l.nombre = ?, l.direccion = ? "
//                    + " WHERE e.idevento = ? AND l.idlugar = ? AND e.idlugar = l.idlugar";
//            String query = "UPDATE evento e inner join lugar lu on e.idlugar=lu.idlugar "
//                    + " SET e.nombre = ? ,e.descripcion=?,e.duracion=?, lu.nombre=?, "
//                    + " lu.direccion=?, e.activo=?, e.fecha=STR_TO_DATE(?,'%m/%d/%Y %H:%i') WHERE e.idevento = ?";
            String query1 = "INSERT INTO evento(nombre, descripcion, fecha, duracion, activo, idlugar, "
                    + " cancelado) VALUES (?, ?, STR_TO_DATE(?,'%d/%m/%Y %H:%i'), ?, ?,?, 1)";

            pstmt = conn.prepareStatement(query1);
            pstmt.setString(1, bEvento.getNombre());
            pstmt.setString(2, bEvento.getDescripcion());
            pstmt.setString(3, f1);
            pstmt.setInt(4, bEvento.getDuracion());
            pstmt.setInt(5, bEvento.getActivo());
            pstmt.setInt(6, idlugar);

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

    public int sacarid() {

        int idl = 0;
        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT max(idlugar) FROM lugar";

            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                idl = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
        return idl;
    }

    public boolean validar_username(String username) {
        // Valida ahora por usuario y por correo.

        boolean ayuda = false;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Connection conn = null;
        try {

            pstmt = conn.prepareStatement("select * from usuario where username=?;");
            pstmt.setString(1, username);
            res = pstmt.executeQuery();
            if (res.next()) {
                ayuda = true;  // si hay un usuario con ese nombre -1 es el valor si ya existe usuario.

            }
            res.close();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException ex) {
                    ;
                }
                res = null;
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
        return ayuda;

    }

    public boolean validar_correo(String correo) {
        // Valida ahora por usuario y por correo.

        boolean ayuda = false;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Connection conn = null;
        try {

            pstmt = conn.prepareStatement("select * from usuario where correo=?;");
            pstmt.setString(1, correo);
            res = pstmt.executeQuery();
            if (res.next()) {
                ayuda = true;  // si hay un usuario con ese nombre -1 es el valor si ya existe usuario.

            }
            res.close();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException ex) {
                    ;
                }
                res = null;
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
        return ayuda;

    }

    public ArrayList<BUsuario> ListarUsuarioXEvento(int idevento) {
        BUsuario buser = null;
        ArrayList<BUsuario> L = new ArrayList<BUsuario>();

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT u.idusuario,u.nombre,u.apellido,u.usuario, "
                    + "u.correo FROM usuario u inner join calendario c on u.idusuario=c.idusuario "
                    + "where u.administrador='0' AND u.estado_logueo='1' AND c.idevento=?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idevento);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                buser = new BUsuario();
                buser.setIdusuario(rs.getInt(1));
                buser.setNombre(rs.getString(2));
                buser.setApellido(rs.getString(3));
                buser.setUsuario(rs.getString(4));
                buser.setCorreo(rs.getString(5));

                L.add(buser);
            }



            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
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
        return L;

    }
}
