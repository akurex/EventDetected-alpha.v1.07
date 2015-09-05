/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.dao;

import com.sw2.ed.bean.BCalendario;
import com.sw2.ed.bean.BEvento;
import com.sw2.ed.bean.BLugar;
import com.sw2.ed.bean.BRecordatorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian
 */
public class DEventos extends DBase {

    public void DesactivarEvento(int idcalendario) {
        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {

            String query = " UPDATE calendario SET activo=0 WHERE idcalendario=?";


            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idcalendario);

            pstmt.executeUpdate();
            conn.commit();

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
                    System.out.println(ex);
                }
                rs = null;
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                pstmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                conn = null;
            }
        }

    }

    public void ActivarRecordatorio(int idcalendario) {
        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {

           String query = " UPDATE calendario SET activo=1 WHERE idcalendario=?";


            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idcalendario);

            pstmt.executeUpdate();
            conn.commit();

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
                    System.out.println(ex);
                }
                rs = null;
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                pstmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                conn = null;
            }
        }

    }

    public ArrayList<BRecordatorio> ListarEventosRecordatorio() {
        ArrayList<BRecordatorio> L = new ArrayList<BRecordatorio>();
        BRecordatorio record = null;

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "select e.idevento, e.nombre, u.nombre, u.correo, DATE_FORMAT(c.recordatorio, '%Y/%m/%d %H:%i:%s'),c.activo,c.idcalendario  "
                    + "from evento e, usuario u, calendario c "
                    + "where e.idevento = c.idevento "
                    + "and u.idusuario = c.idusuario "
                    + "and e.activo = 1 and c.activo = 1";

            pstmt = conn.prepareStatement(query);

            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                record = new BRecordatorio();
                record.setIdEvento(rs.getInt(1));
                record.setNombreEvento(rs.getString(2));
                record.setNombreUsuario(rs.getString(3));
                record.setCorreo(rs.getString(4));
                record.setRecordatorio(rs.getString(5));
                record.setActivo(rs.getInt(6));
                record.setIdcalendario(rs.getInt(7));



                L.add(record);
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

    public ArrayList<BEvento> ListarEventoxUsuario(int idusuario) {
        ArrayList<BEvento> L = new ArrayList<BEvento>();

        BEvento bevento = null;
        BLugar blugar = null;

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT c.idevento, e.nombre, e.descripcion, e.fecha, "
                    + "e.duracion, e.activo, e.cancelado, l.nombre, l.direccion, l.latitud, l.longitud "
                    + "FROM calendario c, evento e, lugar l "
                    + "WHERE l.idlugar = e.idlugar AND e.idevento = c.idevento AND c.idusuario = ?  and e.cancelado=1 ";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idusuario);
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
                blugar.setNombre(rs.getString(8));
                blugar.setDireccion(rs.getString(9));
                blugar.setLatitud(rs.getFloat(10));
                blugar.setLongitud(rs.getFloat(11));
                bevento.setIdlugar(blugar);
                L.add(bevento);
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

    public BEvento DataEvento(int idevent) {
        BEvento bevent = null;
        BLugar blugar = null;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT e.idevento, e.nombre, e.descripcion, e.fecha, e.duracion, e.activo,e.cancelado l.idlugar,"
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

    public ArrayList<BEvento> ListarNuevosEventos(int idusuario) {
        ArrayList<BEvento> L = new ArrayList<BEvento>();

        BEvento bevento = null;
        BLugar blugar = null;

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {



            String query = "SELECT e.idevento, e.nombre, e.descripcion, e.fecha, "
                    + "e.duracion, e.activo, e.cancelado , l.nombre, l.direccion, l.latitud, l.longitud "
                    + "FROM  evento e, lugar l "
                    + "WHERE l.idlugar = e.idlugar AND e.activo = 1 and e.fecha>now() and e.cancelado=1 ";




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
                blugar.setNombre(rs.getString(8));
                blugar.setDireccion(rs.getString(9));
                blugar.setLatitud(rs.getFloat(10));
                blugar.setLongitud(rs.getFloat(11));
                bevento.setIdlugar(blugar);
                L.add(bevento);
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

    private void log(Object o) {
        System.out.println(o);
    }

    public void BorrarEventoUsuario(int idusuario, int idevent) {
        BEvento bevent = null;
        BLugar blugar = null;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            String query = " DELETE FROM calendario where idusuario=? and idevento=? ";


            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idusuario);
            pstmt.setInt(2, idevent);
            pstmt.executeUpdate();
            conn.commit();

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


    }

    public BEvento AñadirEvento(int idusuario, int idevent) {
        BEvento bevent = null;
        BLugar blugar = null;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            String query = " INSERT INTO calendario (`idusuario`, `idevento` ) VALUES (?, ? )";


            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idusuario);
            pstmt.setInt(2, idevent);
            pstmt.executeUpdate();
            conn.commit();

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

    public ArrayList<BEvento> ListarEventoxUsuarioHoy(int idusuario) {
        ArrayList<BEvento> L = new ArrayList<BEvento>();

        BEvento bevento = null;
        BLugar blugar = null;

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT c.idevento, e.nombre, e.descripcion, e.fecha,"
                    + " e.duracion, e.activo, l.nombre, l.direccion, l.latitud, l.longitud, e.cancelado"
                    + " FROM calendario c, evento e, lugar l"
                    + " WHERE l.idlugar = e.idlugar AND e.idevento = c.idevento AND c.idusuario = ?  and ( DATE(now())=DATE(e.fecha ))"
                    + " and e.cancelado=1 ;";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idusuario);
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
                blugar.setNombre(rs.getString(8));
                blugar.setDireccion(rs.getString(9));
                blugar.setLatitud(rs.getFloat(10));
                blugar.setLongitud(rs.getFloat(11));
                bevento.setIdlugar(blugar);
                L.add(bevento);
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

    public ArrayList<BEvento> ListarEventoxUsuarioMañana(int idusuario) {
        ArrayList<BEvento> L = new ArrayList<BEvento>();

        BEvento bevento = null;
        BLugar blugar = null;

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT c.idevento, e.nombre, e.descripcion, e.fecha, "
                    + " e.duracion, e.activo, e.cancelado ,  l.nombre, l.direccion, l.latitud, l.longitud"
                    + " FROM calendario c, evento e, lugar l"
                    + " WHERE l.idlugar = e.idlugar AND e.idevento = c.idevento AND c.idusuario = ?  "
                    + "and DATE( adddate(now(), interval 1 day ))=DATE(e.fecha)"
                    + " and e.cancelado= 1 ;";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idusuario);
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
                blugar.setNombre(rs.getString(8));
                blugar.setDireccion(rs.getString(9));
                blugar.setLatitud(rs.getFloat(10));
                blugar.setLongitud(rs.getFloat(11));
                bevento.setIdlugar(blugar);
                L.add(bevento);
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

    public BEvento AñadirEventoRecordatorio(int idusuario, int idevent) {
        BEvento bevent = null;
        BLugar blugar = null;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            String query = " INSERT INTO calendario (`idusuario`, `idevento`,`recordatorio` ) VALUES (? , ? , STR_TO_DATE(?,'%Y/%m/%d %H:%i:%s') )";


            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idusuario);
            pstmt.setInt(2, idevent);
            pstmt.executeUpdate();
            conn.commit();

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

    public BCalendario calenda(int idusuario, int idevento) {
        BCalendario bCalendario = null;


        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT c.idcalendario"
                    + " FROM  calendario c "
                    + " WHERE c.idusuario=? AND c.idevento=? ";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idusuario);
            pstmt.setInt(2, idevento);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bCalendario = new BCalendario();
                bCalendario.setIdcalendario(rs.getInt(1));

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
        return bCalendario;
    }

    public void recordatorioAVANZADO(int idcalendario, String recor) {


        BEvento bevento = null;
        BLugar blugar = null;

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE calendario c inner join evento e on e.idevento=c.idevento"
                    + " SET c.recordatorio =STR_TO_DATE(?,'%Y/%m/%d %H:%i:%s')"
                    + " WHERE c.idcalendario=? ";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, recor);
            pstmt.setInt(2, idcalendario);
            pstmt.executeUpdate();
            conn.commit();

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

    }

    public void recordatorioBASICO(int idcalendario, int recor) {


        BEvento bevento = null;
        BLugar blugar = null;

        this.init_mysql();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE calendario c inner join evento e on e.idevento=c.idevento"
                    + " SET c.recordatorio =subdate(e.fecha , interval ? minute )"
                    + " WHERE c.idcalendario=? ";


            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, recor);
            pstmt.setInt(2, idcalendario);
            pstmt.executeUpdate();
            conn.commit();

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
    }
}
