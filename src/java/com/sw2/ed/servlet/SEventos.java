
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.servlet;

import com.sw2.ed.bean.BCalendario;
import com.sw2.ed.bean.BEvento;
import com.sw2.ed.bean.BLugar;
import com.sw2.ed.bean.BUsuario;
import com.sw2.ed.dao.DAdmin;
import com.sw2.ed.dao.DEventos;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Christian
 */
public class SEventos extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accionStr = request.getParameter("accion") == null ? "10" : request.getParameter("accion");
            int accion = Integer.parseInt(accionStr);
            String pagina = "";
            ArrayList<BEvento> L = new ArrayList<BEvento>();
            ArrayList<BEvento> L2 = new ArrayList<BEvento>();
            ArrayList<BEvento> LF = new ArrayList<BEvento>();
            BCalendario LC = new BCalendario();
            ArrayList<String> partesfecha = null;
            BUsuario bUsuario = null;
            DEventos dEvento = new DEventos();
            BEvento bEvento = null;
            BLugar bLugar = null;
            DAdmin dadmin = new DAdmin();

            HttpSession session1 = request.getSession(true);
            BUsuario usuario = (BUsuario) session1.getAttribute("usuario");

            if (usuario.getNombre() == null) {

                response.sendRedirect("index.jsp");
            }


            switch (accion) {
                case 1:
                    pagina = "/Eventos/evento_info2.jsp";
                    break;

                case 2://Mapa de todos los eventos

                    L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                    request.setAttribute("listaeventos", L);
                    pagina = "/Mapas/mapa_eventos.jsp";
                    break;

                case 3://Ruta del evento selecionado
                    int idevent3 = Integer.parseInt((request.getParameter("data") == null ? "0" : request.getParameter("data")));
                    bEvento = dadmin.DataEvento(idevent3);
                    request.setAttribute("evento", bEvento);
                    pagina = "/Mapas/ver_ruta.jsp";
                    break;

                case 5: //Datos de un evento que tiene el usuario
                    partesfecha = new ArrayList<String>();
                    int idevent5 = Integer.parseInt(request.getParameter("event"));
                    bEvento = dadmin.DataEvento(idevent5);

                    String dFormat1 = "dd";
                    SimpleDateFormat sdf = new SimpleDateFormat(dFormat1);
                    String s1 = sdf.format(bEvento.getFecha());//obtengo el dia.


                    String[] meses = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", " Sep", "Oct", "Nov", "Dec"};
                    String dFormat2 = "M";
                    SimpleDateFormat sdf2 = new SimpleDateFormat(dFormat2);
                    String s2 = sdf2.format(bEvento.getFecha());//obtengo el mes

                    String dFormat3 = "yyyy";
                    SimpleDateFormat sdf3 = new SimpleDateFormat(dFormat3);
                    String s3 = sdf3.format(bEvento.getFecha());//obtengo el año.

                    String dFormat4 = "hh";
                    SimpleDateFormat sdf4 = new SimpleDateFormat(dFormat4);
//                    String s4=String.valueOf(bEvento.getFecha().getHours());//obtengo la hora.
                    String s4 = sdf4.format(bEvento.getFecha());//obtengo el año.
//                    String.valueOf(bEvento.getFecha().getHours()


                    String dFormat5 = "mm";
                    SimpleDateFormat sdf5 = new SimpleDateFormat(dFormat5);
                    String s5 = sdf5.format(bEvento.getFecha());//obtengo los minutos.

                    partesfecha.add(0, s1);
                    partesfecha.add(1, meses[Integer.parseInt(s2) - 1]);
                    partesfecha.add(2, s3);
                    partesfecha.add(3, s4);
                    partesfecha.add(4, s5);

                    request.setAttribute("listapartes", partesfecha);
                    request.setAttribute("dataevento", bEvento);
                    pagina = "/Eventos/evento_info1.jsp";
                    break;

                case 6://Mapa del evento seleccionado
                    int idevent6 = Integer.parseInt(request.getParameter("event"));
                    bEvento = dEvento.DataEvento(idevent6);
                    request.setAttribute("evento", bEvento);
                    pagina = "/Mapas/my_event.jsp";
                    break;


                case 7://Listado de eventos que no tiene el usuario
                    L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                    L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());
                    ArrayList<BEvento> LF2 = new ArrayList<BEvento>();

                    for (int i = 0; i < L2.size(); i++) {
                        int P = 0;
                        for (int j = 0; j < L.size(); j++) {

                            if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                P = P + 1;
                            }
                        }

                        // fin for
                        if (P == L.size()) {
                            LF2.add(L2.get(i));
                        }
                    }



                    request.setAttribute("listanuevoseventos", LF2);
                    pagina = "/Eventos/nuevos_eventos.jsp";
                    break;


                case 8://Borrar evento del usuario
                    int idevent8 = Integer.parseInt(request.getParameter("event"));
                    dEvento.BorrarEventoUsuario(usuario.getIdusuario(), idevent8);
                    L = dEvento.ListarEventoxUsuarioHoy(usuario.getIdusuario());
                    L2 = dEvento.ListarEventoxUsuarioMañana(usuario.getIdusuario());
                    request.setAttribute("eventoshoy", L);
                    request.setAttribute("eventosmañana", L2);

                    pagina = "/Eventos/eventos.jsp";
                    break;


                case 9: //Datos de un nuevo evento
                    partesfecha = new ArrayList<String>();
                    int idevent9 = Integer.parseInt(request.getParameter("event"));
                    bEvento = dadmin.DataEvento(idevent9);

                    String dFormat12 = "dd";
                    SimpleDateFormat sdf42 = new SimpleDateFormat(dFormat12);
                    String s12 = sdf42.format(bEvento.getFecha());//obtengo el dia.


                    String[] meses2 = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", " Sep", "Oct", "Nov", "Dec"};
                    String dFormat22 = "M";
                    SimpleDateFormat sdf22 = new SimpleDateFormat(dFormat22);
                    String s22 = sdf22.format(bEvento.getFecha());//obtengo el mes

                    String dFormat32 = "yyyy";
                    SimpleDateFormat sdf32 = new SimpleDateFormat(dFormat32);
                    String s32 = sdf32.format(bEvento.getFecha());//obtengo el año.

                    String dFormat42 = "hh";
                    SimpleDateFormat sdf422 = new SimpleDateFormat(dFormat42);
//                    String s4=String.valueOf(bEvento.getFecha().getHours());//obtengo la hora.
                    String s422 = sdf422.format(bEvento.getFecha());//obtengo el año.
//                    String.valueOf(bEvento.getFecha().getHours()


                    String dFormat52 = "mm";
                    SimpleDateFormat sdf52 = new SimpleDateFormat(dFormat52);
                    String s52 = sdf52.format(bEvento.getFecha());//obtengo los minutos.

                    partesfecha.add(0, s12);
                    partesfecha.add(1, meses2[Integer.parseInt(s22) - 1]);
                    partesfecha.add(2, s32);
                    partesfecha.add(3, s422);
                    partesfecha.add(4, s52);

                    request.setAttribute("listapartes", partesfecha);

                    request.setAttribute("dataevento", bEvento);
                    pagina = "/Eventos/data-eventonuevo.jsp";
                    break;
                case 10://eventos de hoy y maniana

                    L = dEvento.ListarEventoxUsuarioHoy(usuario.getIdusuario());
                    L2 = dEvento.ListarEventoxUsuarioMañana(usuario.getIdusuario());
                    request.setAttribute("eventoshoy", L);
                    request.setAttribute("eventosmañana", L2);
                    pagina = "/Eventos/eventos.jsp";
                    break;

                case 11://Agrgar un evento a usuario
                    int idevent11 = Integer.parseInt(request.getParameter("event"));
                    L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                    LC = dEvento.calenda(usuario.getIdusuario(), idevent11);
                    dEvento.DesactivarEvento(LC.getIdcalendario());
                    request.setAttribute("listaeventos", L);
                    pagina = "/Eventos/todos_eventos.jsp";
                    break;


                case 12: // Guardar un evento con recordatorio




                    bEvento = new BEvento();
                    bLugar = new BLugar();

                    bEvento.setIdevento(Integer.parseInt(request.getParameter("data")));
                    bEvento.setNombre(request.getParameter("nombre"));
                    bEvento.setDescripcion(request.getParameter("descripcion"));
////                    
                    String fechax = request.getParameter("mydate1");
                    String hora1g = request.getParameter("mydate2");
                    String hora1 = "";
                    hora1 = hora1g.substring(6, 8);
                    int hora2 = 0;
                    if (hora1.compareTo("PM") == 0) {
                        hora2 = Integer.parseInt(hora1g.substring(0, 2)) + 12;
                        hora1g = String.valueOf(hora2) + "" + hora1g.substring(2, 5);
                    }
                    if (hora1.compareTo("AM") == 0) {
                        hora2 = Integer.parseInt(hora1g.substring(0, 2));
                        hora1g = String.valueOf(hora2) + "" + hora1g.substring(2, 5);
                    }
                    String fecharesult = fechax + " " + hora1g;
//                   
                    bEvento.setDuracion(Integer.parseInt(request.getParameter("duracion")));
                    bEvento.setActivo(Integer.parseInt(request.getParameter("radio-choice-1")));
//                    
                    bLugar.setNombre(request.getParameter("lugar"));
                    bLugar.setDireccion(request.getParameter("direccion"));

                    bEvento.setIdlugar(bLugar);

                    if (bEvento.getIdevento() == 0) {
                        dadmin.CrearEvento(bEvento);
                    } else {
                        dadmin.GuardarEvento(bEvento, fecharesult);
//                          dadmin.GuardarEvento(bEvento);
                    }


                    request.setAttribute("listaeventos", dadmin.ListarEvento());
                    pagina = "/Admin/adminEvents.jsp";
                    break;
                case 13://eventos de todos los dias

                    L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                    request.setAttribute("listaeventos", L);
                    pagina = "/Eventos/todos_eventos.jsp";
                    break;

                case 14:
                    pagina = "/Eventos/busqueda_avanzada.jsp";
                    break;

                case 15:

                    String s13 = "";
                    String s23 = "";
                    String s33 = "";
                    String s43 = "";
                    s13 = request.getParameter("nombre");
                    s23 = request.getParameter("lugar");
                    s33 = request.getParameter("mydate1");
                    s43 = request.getParameter("mydate2");
                    ArrayList<BEvento> LF13 = new ArrayList<BEvento>();


                    if (s13.length() == 0 && s23.length() == 0 && s33.length() == 0 && s43.length() == 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());


                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }

                            // fin for
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }

                        request.setAttribute("listanuevoseventos", LF13);
                        pagina = "/Eventos/resultado_busqueda.jsp";
                        break;




                    }

                    if (s13.length() != 0 && s23.length() == 0 && s33.length() == 0 && s43.length() == 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());


                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }

                            // fin for
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF14 = new ArrayList<BEvento>();
                        for (BEvento be : LF13) {
                            if (be.getNombre().toLowerCase().contains(s13.toLowerCase())) {
                                LF14.add(be);

                            }
                        }

                        request.setAttribute("listanuevoseventos", LF14);
                        pagina = "/Eventos/resultado_busqueda.jsp";
                        break;

                    }
//              

                    if (s13.length() == 0 && s23.length() != 0 && s33.length() == 0 && s43.length() == 0) {


                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());


                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }

                            // fin for
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF15 = new ArrayList<BEvento>();
                        for (BEvento be1 : LF13) {
                            if (be1.getIdlugar().getNombre().toLowerCase().contains(s23.toLowerCase())) {
                                LF15.add(be1);
                            }

                        }

                        request.setAttribute("listanuevoseventos", LF15);

                        pagina = "/Eventos/resultado_busqueda.jsp";
                        break;

                    }

                    if (s13.length() == 0 && s23.length() == 0 && s33.length() != 0 && s43.length() != 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());


                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }

                            // fin for
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF16 = new ArrayList<BEvento>();
                        for (BEvento be1 : LF13) {
                            String dFormat16 = "MM/dd/yyyy";
                            SimpleDateFormat sdf16 = new SimpleDateFormat(dFormat16);
                            String s16 = sdf16.format(be1.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s33);//fecha inicio 
                                date3 = (Date) formatter.parse(s43);//fecha fin
                                date4 = (Date) formatter.parse(s16);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (date4.after(date2) && date4.before(date3)) {
                                LF16.add(be1);
                            }
                        }
//                  System.out.println(s33);    
                        request.setAttribute("listanuevoseventos", LF16);

                        pagina = "/Eventos/resultado_busqueda.jsp";
                        break;

                    }

                    if (s13.length() != 0 && s23.length() != 0 && s33.length() == 0 && s43.length() == 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF17 = new ArrayList<BEvento>();
                        for (BEvento be : LF13) {
                            if (be.getNombre().toLowerCase().contains(s13.toLowerCase()) && be.getIdlugar().getNombre().toLowerCase().contains(s23.toLowerCase())) {
                                LF17.add(be);
                            }

                        }
                        request.setAttribute("listanuevoseventos", LF17);
                        pagina = "/Eventos/resultado_busqueda.jsp";
                        break;

                    }
                    if (s13.length() == 0 && s23.length() != 0 && s33.length() != 0 && s43.length() != 0) {


                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF18 = new ArrayList<BEvento>();
                        for (BEvento be : LF13) {
                            String dFormat18 = "MM/dd/yyyy";
                            SimpleDateFormat sdf18 = new SimpleDateFormat(dFormat18);
                            String s18 = sdf18.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s33);//fecha inicio 
                                date3 = (Date) formatter.parse(s43);//fecha fin
                                date4 = (Date) formatter.parse(s18);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }






                            if (be.getIdlugar().getNombre().toLowerCase().contains(s23.toLowerCase())
                                    && date4.after(date2) && date4.before(date3)) {

                                LF18.add(be);
                            }

                        }
                        request.setAttribute("listanuevoseventos", LF18);
                        pagina = "/Eventos/resultado_busqueda.jsp";
                        break;

                    }

                    if (s13.length() != 0 && s23.length() == 0 && s33.length() != 0 && s43.length() != 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF19 = new ArrayList<BEvento>();
                        for (BEvento be : LF13) {
                            String dFormat19 = "MM/dd/yyyy";
                            SimpleDateFormat sdf19 = new SimpleDateFormat(dFormat19);
                            String s19 = sdf19.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s33);//fecha inicio 
                                date3 = (Date) formatter.parse(s43);//fecha fin
                                date4 = (Date) formatter.parse(s19);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }




                            if (be.getNombre().toLowerCase().contains(s13.toLowerCase()) && date4.after(date2) && date4.before(date3)) {
                                LF19.add(be);
                            }

                        }
                        request.setAttribute("listanuevoseventos", LF19);
                        pagina = "/Eventos/resultado_busqueda.jsp";
                        break;

                    }

                    if (s13.length() != 0 && s23.length() != 0 && s33.length() != 0 && s43.length() != 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF20 = new ArrayList<BEvento>();
                        for (BEvento be : LF13) {
                            String dFormat20 = "MM/dd/yyyy";
                            SimpleDateFormat sdf20 = new SimpleDateFormat(dFormat20);
                            String s20 = sdf20.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s33);//fecha inicio 
                                date3 = (Date) formatter.parse(s43);//fecha fin
                                date4 = (Date) formatter.parse(s20);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }





                            if (be.getNombre().toLowerCase().contains(s13.toLowerCase()) && be.getIdlugar().getNombre().toLowerCase().contains(s23.toLowerCase()) && date4.after(date2) && date4.before(date3)) {

                                LF20.add(be);
                            }

                        }
                        request.setAttribute("listanuevoseventos", LF20);
                        pagina = "/Eventos/resultado_busqueda.jsp";
                        break;

                    }



                case 16:
                    String s161 = "";
                    String s26 = "";
                    String s36 = "";
                    String s46 = "";
                    s161 = request.getParameter("nombre");
                    s26 = request.getParameter("lugar");
                    s36 = request.getParameter("mydate1");
                    s46 = request.getParameter("mydate2");

                    L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());

                    if (s161.length() == 0 && s26.length() == 0 && s36.length() == 0 && s46.length() == 0) {
                        request.setAttribute("listaeventos", L);
                        pagina = "/Eventos/resultado_busqueda1.jsp";
                        break;
                    }

                    if (s161.length() != 0 && s26.length() == 0 && s36.length() == 0 && s46.length() == 0) {


                        ArrayList<BEvento> LF14 = new ArrayList<BEvento>();
                        for (BEvento be : L) {
                            if (be.getNombre().toLowerCase().contains(s161.toLowerCase())) {
                                LF14.add(be);

                            }
                        }

                        request.setAttribute("listaeventos", LF14);
                        pagina = "/Eventos/resultado_busqueda1.jsp";
                        break;

                    }
//              

                    if (s161.length() == 0 && s26.length() != 0 && s36.length() == 0 && s46.length() == 0) {
                        ArrayList<BEvento> LF15 = new ArrayList<BEvento>();
                        for (BEvento be1 : L) {
                            if (be1.getIdlugar().getNombre().contains(s26)) {
                                LF15.add(be1);
                            }

                        }
                        request.setAttribute("listaeventos", LF15);
                        pagina = "/Eventos/resultado_busqueda1.jsp";
                        break;
                    }
//
                    if (s161.length() == 0 && s26.length() == 0 && s36.length() != 0 && s46.length() != 0) {

                        ArrayList<BEvento> LF16 = new ArrayList<BEvento>();
                        for (BEvento be1 : L) {
                            String dFormat16 = "MM/dd/yyyy";
                            SimpleDateFormat sdf16 = new SimpleDateFormat(dFormat16);
                            String s16 = sdf16.format(be1.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s36);//fecha inicio 
                                date3 = (Date) formatter.parse(s46);//fecha fin
                                date4 = (Date) formatter.parse(s16);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (date4.after(date2) && date4.before(date3)) {
                                LF16.add(be1);
                            }
                        }
//                  System.out.println(s33);    
                        request.setAttribute("listaeventos", LF16);
                        pagina = "/Eventos/resultado_busqueda1.jsp";
                        break;

                    }
//
                    if (s161.length() != 0 && s26.length() != 0 && s36.length() == 0 && s46.length() == 0) {

                        ArrayList<BEvento> LF17 = new ArrayList<BEvento>();
                        for (BEvento be : L) {
                            if (be.getNombre().contains(s161) && be.getIdlugar().getNombre().contains(s26)) {
                                LF17.add(be);
                            }

                        }
                        request.setAttribute("listaeventos", LF17);
                        pagina = "/Eventos/resultado_busqueda1.jsp";
                        break;

                    }
                    if (s161.length() == 0 && s26.length() != 0 && s36.length() != 0 && s46.length() != 0) {



                        ArrayList<BEvento> LF18 = new ArrayList<BEvento>();
                        for (BEvento be : L) {
                            String dFormat18 = "MM/dd/yyyy";
                            SimpleDateFormat sdf18 = new SimpleDateFormat(dFormat18);
                            String s18 = sdf18.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s36);//fecha inicio 
                                date3 = (Date) formatter.parse(s46);//fecha fin
                                date4 = (Date) formatter.parse(s18);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }






                            if (be.getIdlugar().getNombre().contains(s26)
                                    && date4.after(date2) && date4.before(date3)) {

                                LF18.add(be);
                            }

                        }
                        request.setAttribute("listaeventos", LF18);

                        pagina = "/Eventos/resultado_busqueda1.jsp";
                        break;

                    }
//
                    if (s161.length() != 0 && s26.length() == 0 && s36.length() != 0 && s46.length() != 0) {


                        ArrayList<BEvento> LF19 = new ArrayList<BEvento>();
                        for (BEvento be : L) {
                            String dFormat19 = "MM/dd/yyyy";
                            SimpleDateFormat sdf19 = new SimpleDateFormat(dFormat19);
                            String s19 = sdf19.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s36);//fecha inicio 
                                date3 = (Date) formatter.parse(s46);//fecha fin
                                date4 = (Date) formatter.parse(s19);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }




                            if (be.getNombre().contains(s161) && date4.after(date2) && date4.before(date3)) {
                                LF19.add(be);
                            }

                        }
                        request.setAttribute("listaeventos", LF19);

                        pagina = "/Eventos/resultado_busqueda1.jsp";
                        break;

                    }
//
                    if (s161.length() != 0 && s26.length() != 0 && s36.length() != 0 && s46.length() != 0) {

                        ArrayList<BEvento> LF20 = new ArrayList<BEvento>();
                        for (BEvento be : L) {
                            String dFormat20 = "MM/dd/yyyy";
                            SimpleDateFormat sdf20 = new SimpleDateFormat(dFormat20);
                            String s20 = sdf20.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s36);//fecha inicio 
                                date3 = (Date) formatter.parse(s46);//fecha fin
                                date4 = (Date) formatter.parse(s20);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }





                            if (be.getNombre().contains(s161) && be.getIdlugar().getNombre().contains(s26) && date4.after(date2) && date4.before(date3)) {

                                LF20.add(be);
                            }

                        }
                        request.setAttribute("listaeventos", LF20);

                        pagina = "/Eventos/resultado_busqueda1.jsp";
                        break;

                    }

                case 17://Agrega evento

                    int idevent17 = Integer.parseInt(request.getParameter("event"));
                    bEvento = dadmin.DataEvento(idevent17);
                    dEvento.AñadirEvento(usuario.getIdusuario(), idevent17);
                    dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                    request.setAttribute("evento", bEvento);
                    pagina = "/Recordatorio.jsp";
                    break;

                case 18:///Para que nos de el Idcalendario

                    int idevent18 = Integer.parseInt(request.getParameter("event"));
                    LC = dEvento.calenda(usuario.getIdusuario(), idevent18);
                    dEvento.ActivarRecordatorio(LC.getIdcalendario());
                    request.setAttribute("idcalendar", LC);
                    pagina = "/Eventos/recordatorio.jsp";
                    break;

                case 19:// recordatorio Avanzado

                    int idcal = Integer.parseInt(request.getParameter("data"));

                    ///                    
                    String fechax2 = request.getParameter("mydate1");
                    String hora1g2 = request.getParameter("mydate2");
                    String hora12 = "";


                    hora12 = hora1g2 + ":00";


                    String fecharesult2 = fechax2 + " " + hora12;
                    dEvento.recordatorioAVANZADO(idcal, fecharesult2);
                    L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());

                    request.setAttribute("listaeventos", L);

                    pagina = "/Eventos/todos_eventos.jsp";

                    break;

                case 20:///Recordatorio basico

                    //int idevent20 = Integer.parseInt(request.getParameter("event"));

                    int recor = Integer.parseInt(request.getParameter("radio-choice-1"));
                    int idcal2 = Integer.parseInt(request.getParameter("calendar"));
                    //bEvento = dadmin.DataEvento(idevent20);


                    dEvento.recordatorioBASICO(idcal2, recor);

                    L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                    request.setAttribute("listaeventos", L);

                    pagina = "/Eventos/todos_eventos.jsp";
                    break;

                case 21:

                    pagina = "/Eventos/busqueda_avanzada1.jsp";
                    break;
                case 22:
                    L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                    L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());
                    LF2 = new ArrayList<BEvento>();
                    bUsuario = dadmin.DataUsuario(usuario.getIdusuario());
                    for (int i = 0; i < L2.size(); i++) {
                        int P = 0;
                        for (int j = 0; j < L.size(); j++) {

                            if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                P = P + 1;
                            }
                        }

                        // fin for
                        if (P == L.size()) {
                            LF2.add(L2.get(i));
                        }
                    }


                    request.setAttribute("listaeventos_nuevos", LF2);
                    request.setAttribute("usuario", bUsuario);
                    pagina = "/Mapas/area_search.jsp";
                    break;
                case 23:

//                ----------------- EL MAPA YA FILTRADO      

                    s13 = "";
                    s23 = "";
                    s33 = "";
                    s43 = "";
                    s13 = request.getParameter("nombre");
                    s23 = request.getParameter("lugar");
                    s33 = request.getParameter("mydate1");
                    s43 = request.getParameter("mydate2");
                    LF13 = new ArrayList<BEvento>();


                    if (s13.length() == 0 && s23.length() == 0 && s33.length() == 0 && s43.length() == 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        bUsuario = dadmin.DataUsuario(usuario.getIdusuario());
                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }

                            // fin for
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }

                        request.setAttribute("listaeventos_nuevos", LF13);

                        pagina = "/Mapas/area_search.jsp";
                        break;




                    }

                    if (s13.length() != 0 && s23.length() == 0 && s33.length() == 0 && s43.length() == 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        bUsuario = dadmin.DataUsuario(usuario.getIdusuario());
                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }

                            // fin for
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF14 = new ArrayList<BEvento>();
                        for (BEvento be : LF13) {
                            if (be.getNombre().toLowerCase().contains(s13.toLowerCase())) {
                                LF14.add(be);

                            }
                        }

                        request.setAttribute("listaeventos_nuevos", LF14);
                        pagina = "/Mapas/area_search.jsp";
                        break;

                    }
//              

                    if (s13.length() == 0 && s23.length() != 0 && s33.length() == 0 && s43.length() == 0) {


                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());


                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }

                            // fin for
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF15 = new ArrayList<BEvento>();
                        for (BEvento be1 : LF13) {
                            if (be1.getIdlugar().getNombre().toLowerCase().contains(s23.toLowerCase())) {
                                LF15.add(be1);
                            }

                        }

                        request.setAttribute("listaeventos_nuevos", LF15);
                        pagina = "/Mapas/area_search.jsp";
                        break;

                    }

                    if (s13.length() == 0 && s23.length() == 0 && s33.length() != 0 && s43.length() != 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }

                            // fin for
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF16 = new ArrayList<BEvento>();
                        for (BEvento be1 : LF13) {
                            String dFormat16 = "MM/dd/yyyy";
                            SimpleDateFormat sdf16 = new SimpleDateFormat(dFormat16);
                            String s16 = sdf16.format(be1.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s33);//fecha inicio 
                                date3 = (Date) formatter.parse(s43);//fecha fin
                                date4 = (Date) formatter.parse(s16);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (date4.after(date2) && date4.before(date3)) {
                                LF16.add(be1);
                            }
                        }
//                  System.out.println(s33);    
                        request.setAttribute("listaeventos_nuevos", LF16);
                        pagina = "/Mapas/area_search.jsp";
                        break;

                    }

                    if (s13.length() != 0 && s23.length() != 0 && s33.length() == 0 && s43.length() == 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF17 = new ArrayList<BEvento>();
                        for (BEvento be : LF13) {
                            if (be.getNombre().toLowerCase().contains(s13.toLowerCase()) && be.getIdlugar().getNombre().toLowerCase().contains(s23.toLowerCase())) {
                                LF17.add(be);
                            }

                        }
                        request.setAttribute("listaeventos_nuevos", LF17);
                        pagina = "/Mapas/area_search.jsp";
                        break;

                    }
                    if (s13.length() == 0 && s23.length() != 0 && s33.length() != 0 && s43.length() != 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF18 = new ArrayList<BEvento>();
                        for (BEvento be : LF13) {
                            String dFormat18 = "MM/dd/yyyy";
                            SimpleDateFormat sdf18 = new SimpleDateFormat(dFormat18);
                            String s18 = sdf18.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s33);//fecha inicio 
                                date3 = (Date) formatter.parse(s43);//fecha fin
                                date4 = (Date) formatter.parse(s18);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }






                            if (be.getIdlugar().getNombre().toLowerCase().contains(s23.toLowerCase())
                                    && date4.after(date2) && date4.before(date3)) {

                                LF18.add(be);
                            }

                        }
                        request.setAttribute("listaeventos_nuevos", LF18);
                        pagina = "/Mapas/area_search.jsp";
                        break;

                    }

                    if (s13.length() != 0 && s23.length() == 0 && s33.length() != 0 && s43.length() != 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF19 = new ArrayList<BEvento>();
                        for (BEvento be : LF13) {
                            String dFormat19 = "MM/dd/yyyy";
                            SimpleDateFormat sdf19 = new SimpleDateFormat(dFormat19);
                            String s19 = sdf19.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s33);//fecha inicio 
                                date3 = (Date) formatter.parse(s43);//fecha fin
                                date4 = (Date) formatter.parse(s19);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }




                            if (be.getNombre().toLowerCase().contains(s13.toLowerCase()) && date4.after(date2) && date4.before(date3)) {
                                LF19.add(be);
                            }

                        }
                        request.setAttribute("listaeventos_nuevos", LF19);
                        pagina = "/Mapas/area_search.jsp";
                        break;

                    }

                    if (s13.length() != 0 && s23.length() != 0 && s33.length() != 0 && s43.length() != 0) {

                        L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                        L2 = dEvento.ListarNuevosEventos(usuario.getIdusuario());

                        for (int i = 0; i < L2.size(); i++) {
                            int P = 0;
                            for (int j = 0; j < L.size(); j++) {

                                if (L2.get(i).getIdevento() != L.get(j).getIdevento()) {
                                    P = P + 1;
                                }
                            }
                            if (P == L.size()) {
                                LF13.add(L2.get(i));
                            }
                        }
                        ArrayList<BEvento> LF20 = new ArrayList<BEvento>();
                        for (BEvento be : LF13) {
                            String dFormat20 = "MM/dd/yyyy";
                            SimpleDateFormat sdf20 = new SimpleDateFormat(dFormat20);
                            String s20 = sdf20.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s33);//fecha inicio 
                                date3 = (Date) formatter.parse(s43);//fecha fin
                                date4 = (Date) formatter.parse(s20);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }





                            if (be.getNombre().toLowerCase().contains(s13.toLowerCase()) && be.getIdlugar().getNombre().toLowerCase().contains(s23.toLowerCase()) && date4.after(date2) && date4.before(date3)) {

                                LF20.add(be);
                            }

                        }
                        request.setAttribute("listaeventos_nuevos", LF20);
                        pagina = "/Mapas/area_search.jsp";
                        break;


                    }
                case 24://Agrega evento

                    int idevent24 = Integer.parseInt(request.getParameter("event"));
                    bEvento = dadmin.DataEvento(idevent24);
                    dEvento.ListarEventoxUsuario(usuario.getIdusuario());
                    request.setAttribute("evento", bEvento);
                    pagina = "/Recordatorio.jsp";
                    break;
                    
                    case 25:     // --------  FILTRO PARA MIS EVENTOS
                    s161 = "";
                    s26 = "";
                    s36 = "";
                    s46 = "";
                    s161 = request.getParameter("nombre");
                    s26 = request.getParameter("lugar");
                    s36 = request.getParameter("mydate1");
                    s46 = request.getParameter("mydate2");

                    bUsuario = dadmin.DataUsuario(usuario.getIdusuario());
                    L = dEvento.ListarEventoxUsuario(usuario.getIdusuario());

                    if (s161.length() == 0 && s26.length() == 0 && s36.length() == 0 && s46.length() == 0) {
                        request.setAttribute("listaeventos", L);
                        request.setAttribute("user", bUsuario);
                        pagina = "/Mapas/mapa_eventos.jsp";
                        break;
                    }

                    if (s161.length() != 0 && s26.length() == 0 && s36.length() == 0 && s46.length() == 0) {


                        ArrayList<BEvento> LF14 = new ArrayList<BEvento>();
                        for (BEvento be : L) {
                            if (be.getNombre().contains(s161)) {
                                LF14.add(be);

                            }
                        }

                        request.setAttribute("listaeventos", LF14);
                        request.setAttribute("user", bUsuario);
                        pagina = "/Mapas/mapa_eventos.jsp";
                        break;

                    }
//              

                    if (s161.length() == 0 && s26.length() != 0 && s36.length() == 0 && s46.length() == 0) {
                        ArrayList<BEvento> LF15 = new ArrayList<BEvento>();
                        for (BEvento be1 : L) {
                            if (be1.getIdlugar().getNombre().contains(s26)) {
                                LF15.add(be1);
                            }

                        }
                        request.setAttribute("listaeventos", LF15);
                        request.setAttribute("user", bUsuario);
                        pagina = "/Mapas/mapa_eventos.jsp";
                        break;
                    }
//
                    if (s161.length() == 0 && s26.length() == 0 && s36.length() != 0 && s46.length() != 0) {

                        ArrayList<BEvento> LF16 = new ArrayList<BEvento>();
                        for (BEvento be1 : L) {
                            String dFormat16 = "MM/dd/yyyy";
                            SimpleDateFormat sdf16 = new SimpleDateFormat(dFormat16);
                            String s16 = sdf16.format(be1.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s36);//fecha inicio 
                                date3 = (Date) formatter.parse(s46);//fecha fin
                                date4 = (Date) formatter.parse(s16);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (date4.after(date2) && date4.before(date3)) {
                                LF16.add(be1);
                            }
                        }
//                  System.out.println(s33);    
                        request.setAttribute("listaeventos", LF16);
                        request.setAttribute("user", bUsuario);
                        pagina = "/Mapas/mapa_eventos.jsp";
                        break;

                    }
//
                    if (s161.length() != 0 && s26.length() != 0 && s36.length() == 0 && s46.length() == 0) {

                        //int iduser13 = Integer.parseInt(request.getParameter("data"));

                        ArrayList<BEvento> LF17 = new ArrayList<BEvento>();
                        for (BEvento be : L) {
                            if (be.getNombre().contains(s161) && be.getIdlugar().getNombre().contains(s26)) {
                                LF17.add(be);
                            }

                        }
                        request.setAttribute("listaeventos", LF17);
                        request.setAttribute("user", bUsuario);
                        pagina = "/Mapas/mapa_eventos.jsp";
                        break;

                    }
                    if (s161.length() == 0 && s26.length() != 0 && s36.length() != 0 && s46.length() != 0) {

                        //int iduser13 = Integer.parseInt(request.getParameter("data"));

                        ArrayList<BEvento> LF18 = new ArrayList<BEvento>();
                        for (BEvento be : L) {
                            String dFormat18 = "MM/dd/yyyy";
                            SimpleDateFormat sdf18 = new SimpleDateFormat(dFormat18);
                            String s18 = sdf18.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s36);//fecha inicio 
                                date3 = (Date) formatter.parse(s46);//fecha fin
                                date4 = (Date) formatter.parse(s18);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }






                            if (be.getIdlugar().getNombre().contains(s26)
                                    && date4.after(date2) && date4.before(date3)) {

                                LF18.add(be);
                            }

                        }
                        request.setAttribute("listaeventos", LF18);
                        request.setAttribute("user", bUsuario);
                        pagina = "/Mapas/mapa_eventos.jsp";
                        break;

                    }
//
                    if (s161.length() != 0 && s26.length() == 0 && s36.length() != 0 && s46.length() != 0) {


                        ArrayList<BEvento> LF19 = new ArrayList<BEvento>();
                        for (BEvento be : L) {
                            String dFormat19 = "MM/dd/yyyy";
                            SimpleDateFormat sdf19 = new SimpleDateFormat(dFormat19);
                            String s19 = sdf19.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s36);//fecha inicio 
                                date3 = (Date) formatter.parse(s46);//fecha fin
                                date4 = (Date) formatter.parse(s19);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }




                            if (be.getNombre().contains(s161) && date4.after(date2) && date4.before(date3)) {
                                LF19.add(be);
                            }

                        }
                        request.setAttribute("listaeventos", LF19);
                        request.setAttribute("user", bUsuario);
                        pagina = "/Mapas/mapa_eventos.jsp";
                        break;

                    }
//
                    if (s161.length() != 0 && s26.length() != 0 && s36.length() != 0 && s46.length() != 0) {

                        ArrayList<BEvento> LF20 = new ArrayList<BEvento>();
                        for (BEvento be : L) {
                            String dFormat20 = "MM/dd/yyyy";
                            SimpleDateFormat sdf20 = new SimpleDateFormat(dFormat20);
                            String s20 = sdf20.format(be.getFecha());//obtengo la fecha en el formato indicado.

                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date date2 = null;//desde
                            Date date3 = null;//desde
                            Date date4 = null;//desde
                            try {
                                date2 = (Date) formatter.parse(s36);//fecha inicio 
                                date3 = (Date) formatter.parse(s46);//fecha fin
                                date4 = (Date) formatter.parse(s20);
                            } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
                            }





                            if (be.getNombre().contains(s161) && be.getIdlugar().getNombre().contains(s26) && date4.after(date2) && date4.before(date3)) {

                                LF20.add(be);
                            }

                        }
                        request.setAttribute("listaeventos", LF20);
                        request.setAttribute("user", bUsuario);
                        pagina = "/Mapas/mapa_eventos.jsp";
                        break;

                    }

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher(pagina);
            rd.forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
