/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.servlet;

import com.sw2.ed.bean.BEvento;
import com.sw2.ed.bean.BLugar;
import com.sw2.ed.bean.BUsuario;
import com.sw2.ed.dao.DAdmin;
import com.sw2.ed.dao.DLogin;
import com.sw2.ed.mail.Mail;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Denise
 */
public class SAdmin extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String accionStr = request.getParameter("accion") == null ? "9" : request.getParameter("accion");
            int accion = Integer.parseInt(accionStr);
            BUsuario bUsuario = null;
            BUsuario btemp = null;
            BEvento bEvento = null;
            BLugar bLugar = null;
            DAdmin dadmin = new DAdmin();
            DLogin dusuario = new DLogin();
            String pagina = "";
            Mail m = new Mail();
            ArrayList<BUsuario> L = new ArrayList<BUsuario>();
            ArrayList<BEvento> Li = new ArrayList<BEvento>();
            ArrayList<BEvento> LiP = new ArrayList<BEvento>();
            ArrayList<BEvento> LiSoloActivos = new ArrayList<BEvento>();
            ArrayList<BEvento> LiSoloBloqueados = new ArrayList<BEvento>();
            ArrayList<BUsuario> LSoloActivos = new ArrayList<BUsuario>();
            ArrayList<BUsuario> LSoloBloqueados = new ArrayList<BUsuario>();
            ArrayList<BUsuario> LxEvento = new ArrayList<BUsuario>();

            ArrayList<String> partesfecha = null;
            int flag = 0;
            switch (accion) {
                case 1: //Lista Usuarios

                    L = dadmin.ListarUsuario();
                    String[] aux = request.getParameterValues("radios");
                    if (aux != null) {
                        if (request.getParameterValues("radios")[0].equals("Activos")) {
                            for (BUsuario z : L) {
                                if (z.getEstado_logueo() == 1) {
                                    LSoloActivos.add(z);
                                }
                            }
                            request.setAttribute("listausuarios", LSoloActivos);
                        }

                        if (request.getParameterValues("radios")[0].equals("Bloqueados")) {
                            for (BUsuario z : L) {
                                if (z.getEstado_logueo() == 0) {
                                    LSoloBloqueados.add(z);
                                }
                            }
                            request.setAttribute("listausuarios", LSoloBloqueados);
                        }

                        if (request.getParameterValues("radios")[0].equals("Todos")) {
                            request.setAttribute("listausuarios", L);
                        }

                    } else {
                        request.setAttribute("listausuarios", L);
                    }

                    pagina = "/Admin/adminUser.jsp";
                    break;

                case 2: //Lista Eventos

                    Li = dadmin.ListarEvento();

                    for (int j = 0; j < Li.size(); j++) {

                        dadmin.CambiarActivo(Li.get(j));

                    }

                    LiP = dadmin.ListarEvento();

                    String[] aux1 = request.getParameterValues("radios");
                    if (aux1 != null) {
                        if (request.getParameterValues("radios")[0].equals("Activos")) {
                            for (BEvento zi : LiP) {
                                if (zi.getActivo() == 1) {
                                    LiSoloActivos.add(zi);
                                }
                            }
                            request.setAttribute("listaeventos", LiSoloActivos);
                        }

                        if (request.getParameterValues("radios")[0].equals("Cancelados")) {
                            for (BEvento zi : LiP) {
                                if (zi.getCancelado() == 0) {
                                    LiSoloBloqueados.add(zi);
                                }
                            }
                            request.setAttribute("listaeventos", LiSoloBloqueados);
                        }

                        if (request.getParameterValues("radios")[0].equals("Todos")) {
                            request.setAttribute("listaeventos", Li);
                        }

                    } else {
                        request.setAttribute("listaeventos", Li);
                    }





                    pagina = "/Admin/adminEvents.jsp";
                    break;







                case 3: //Datos de un usuario

                    int iduser = Integer.parseInt(request.getParameter("data"));
                    bUsuario = dadmin.DataUsuario(iduser);
                    request.setAttribute("datauser", bUsuario);
                    request.setAttribute("flag", flag);
                    pagina = "/Admin/editUser.jsp";
                    break;

                case 4: // Guardar un usuario
                    bUsuario = new BUsuario();


                    bUsuario.setIdusuario(Integer.parseInt(request.getParameter("data")));


                    bUsuario.setNombre(request.getParameter("nombre"));
                    bUsuario.setApellido(request.getParameter("apellido"));
                    bUsuario.setUsuario(request.getParameter("usuario"));
                    //bUsuario.setContrasena(request.getParameter("password"));
                    bUsuario.setCorreo(request.getParameter("email"));
                    bUsuario.setEstado_logueo(Integer.parseInt(request.getParameter("radio-choice-1")));

                    if (Integer.parseInt(request.getParameter("data")) > 0) {

                        //                       btemp = new BUsuario();
                        //                     btemp.setIdusuario(Integer.parseInt(request.getParameter("data")));
                        //                   btemp.setNombre(request.getParameter("nombre"));
                        //                  btemp.setApellido(request.getParameter("apellido"));
                        //                 btemp.setUsuario(request.getParameter("usuario"));
                        //                  btemp.setCorreo(request.getParameter("email"));
                        //                 btemp.setEstado_logueo(Integer.parseInt(request.getParameter("radio-choice-1")));

                        int id = Integer.parseInt(request.getParameter("data"));
                        //buscar el estado del usuario con el id "id" y guardarlo en un valor estado previo
                        int estado_previo = dadmin.EstadoLogueoxUsuario(id);
                        if (estado_previo != bUsuario.getEstado_logueo()) {
                            if (bUsuario.getEstado_logueo() == 1) // envio correo de activacion de cuenta
                            {
                             m.enviacorreoActivar(bUsuario.getCorreo());   
                               // m.enviacorreoUsuarioNuevo(pagina, pagina, pagina);
                            } else { //envio correo de desactivación de cuenta
                                m.enviacorreoDesactivar(bUsuario.getCorreo());
                               
                            }
                        }

                        dadmin.GuardaUsuario(bUsuario);

                        request.setAttribute("listausuarios", dadmin.ListarUsuario());
                        request.setAttribute("flag", flag);
                        pagina = "/Admin/adminUser.jsp";
                        break;



                    } else {

                        if (dadmin.CrearUsuario(bUsuario)) {
                            String token = Long.toHexString(Double.doubleToLongBits(Math.random()));
                            //m.enviacorreo(mail, "http://localhost:8084/" + request.getContextPath() + "/SLogin?accion=3");
                            dusuario.setToken(token, bUsuario.getCorreo());

                            String link = "http://localhost:8084" + request.getContextPath() + "/SLogin?accion=5&link=";

                            link += token;
                            m.enviacorreoUsuarioNuevo(bUsuario.getCorreo(), link, bUsuario.getUsuario());
                            request.setAttribute("listausuarios", dadmin.ListarUsuario());
                            request.setAttribute("flag", flag);
                            pagina = "/Admin/adminUser.jsp";
                            break;

                        } else {

                            bUsuario = new BUsuario();
                            request.setAttribute("datauser", bUsuario);
                            pagina = "/Admin/validar_user.jsp";
                            break;

                        }

                    }

                case 5: //Datos de un evento
                    partesfecha = new ArrayList<String>();
                    int idevent = Integer.parseInt(request.getParameter("data"));
                    bEvento = dadmin.DataEvento(idevent);

                    //String dFormat1 = "MM/dd/yyyy";
                    String dFormat1 = "yyyy/MM/dd";
                    SimpleDateFormat sdf = new SimpleDateFormat(dFormat1);
                    String s1 = sdf.format(bEvento.getFecha());//obtengo la fecha.


                    String hora = bEvento.getFecha().toString().substring(11, 16);
                    /*int num = Integer.parseInt(hora.substring(0, 2));
                    String fhora = "";
                    if (num > 12) {
                        int num1 = num - 12;
                        String fecha1 = String.valueOf(num1);
                        if (fecha1.length() == 1) {
                            fecha1 = "0" + fecha1;
                        }
                        fhora = fecha1 + "" + bEvento.getFecha().toString().substring(13, 16) + " PM";
//                        System.out.println(fecha1);
                    } else {

                        String fecha2 = String.valueOf(num);
                        if (fecha2.length() == 1) {
                            fecha2 = "0" + fecha2;
                        }

                        fhora = fecha2 + "" + bEvento.getFecha().toString().substring(13, 16) + " AM";
//                        System.out.println(fecha1);

                    }*/
                    request.setAttribute("fecha", s1);
                    request.setAttribute("hora", hora);
                    request.setAttribute("dataevento", bEvento);
                    pagina = "/Admin/editEvent.jsp";
                    break;



                case 6:
//                    bEvento = new BEvento();
//                    bLugar = new BLugar();
//
//                    bEvento.setIdevento(Integer.parseInt(request.getParameter("data")));
//                    bEvento.setNombre(request.getParameter("nombre"));
//                    bEvento.setDescripcion(request.getParameter("descripcion"));
//
//                    bLugar.setIdlugar(Integer.parseInt(request.getParameter("idlugar")));
//                    bLugar.setNombre(request.getParameter("lugar"));
//                    bLugar.setDireccion(request.getParameter("direccion"));
//                    bEvento.setIdlugar(bLugar);
//
//
//                    if (Integer.parseInt(request.getParameter("data")) > 0) {
//
//                        dadmin.GuardarEvento(bEvento);
//                    }
//                    request.setAttribute("listaeventos", dadmin.ListarEvento());
//                    pagina = "/Admin/adminEvents.jsp";
//                    break;

                case 7://NUEVO, MUESTRA FORMULARIO VACIO
                    bUsuario = new BUsuario();
                    request.setAttribute("datauser", bUsuario);
                    pagina = "/Admin/editUser.jsp";
                    break;


                case 8: //BORRAR USUARIO

                    dadmin.EliminarUsuario(Integer.parseInt(request.getParameter("data")));

                    request.setAttribute("listausuarios", dadmin.ListarUsuario());
                    pagina = "/Admin/adminUser.jsp";
                    break;
                case 9:

                    pagina = "/Admin/index.jsp";
                    break;

                case 10: // Guardar un evento

                    bEvento = new BEvento();
                    bLugar = new BLugar();

                    bEvento.setIdevento(Integer.parseInt(request.getParameter("data")));
                    System.out.println(bEvento.getIdevento());
                    bEvento.setNombre(request.getParameter("nombre"));
                    System.out.println(bEvento.getNombre());
                    bEvento.setDescripcion(request.getParameter("descripcion"));

                    String fechax = request.getParameter("mydate1");
                    String hora1g = request.getParameter("mydate2");
                    String hora1 = "";
                    //hora1 = hora1g.substring(6,8);
                    //int hora2 = 0;
                    //if (hora1.compareTo("PM") == 0) {
                      //  hora2 = Integer.parseInt(hora1g.substring(0, 2)) + 12;
                      //  hora1g = String.valueOf(hora2) + "" + hora1g.substring(2, 5);
                    //}


                    //if (hora1.compareTo("AM") == 0) {
                     //   hora2 = Integer.parseInt(hora1g.substring(0, 2));
                      //  hora1g = String.valueOf(hora2) + "" + hora1g.substring(2, 5);
                    //}
                    hora1= hora1g+":00";
                    String fecharesult = fechax + " " + hora1;

                    bEvento.setDuracion(Integer.parseInt(request.getParameter("duracion")));



                    if (Integer.parseInt(request.getParameter("radio-choice-1")) == 0) {
                        bEvento.setCancelado(0);
                        
                    } else {
                        bEvento.setCancelado(Integer.parseInt(request.getParameter("radio-choice-1")));
                        bEvento.setActivo(Integer.parseInt(request.getParameter("radio-choice-1")));
                    }




//                    

                    bLugar.setNombre(request.getParameter("lugar"));
                    bLugar.setDireccion(request.getParameter("direccion"));
                    //bLugar.setIdlugar(iduser);

                    bEvento.setIdlugar(bLugar);

//                    if (bEvento.getIdevento() == 0) {
//                        dadmin.CrearEvento(bEvento);
//                    } else {
                    dadmin.GuardarEvento(bEvento, fecharesult);
                    if (Integer.parseInt(request.getParameter("radio-choice-1")) == 0) {
                        LxEvento = dadmin.ListarUsuarioXEvento(bEvento.getIdevento());
                        for (BUsuario bu : LxEvento) {
                            m.enviarCorreoEvntCancelado(bEvento, bu.getCorreo(), bu.getUsuario());
                        }
                    }
//                          dadmin.GuardarEvento(bEvento);
//                    }

                    if (request.getParameter("submit1") != null) {
                    }
                    request.setAttribute("listaeventos", dadmin.ListarEvento());
                    pagina = "/Admin/adminEvents.jsp";
                    break;

                case 11: //Filtrado

                    //String desde = request.getParameter("desde"); //Fecha Inicio
                    //String hasta = request.getParameter("hasta"); //Fecha Fin
                    String desde = request.getParameter("mydate1");
                    String hasta = request.getParameter("mydate2");
//                            String desde = request.getParameter("mydate1"); //Fecha Inicio
//                            String hasta = request.getParameter("mydate2"); //Fecha Fin

//                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); //please notice the capital M
//Date date1 = null;//desde
//        try {
//            date1 =  (Date) formatter.parse(desde);
//        } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
//        }
////        System.out.println(date);
//        
//                   String dFormatdesde = "dd/MM/yyyy";
//                    SimpleDateFormat sdfdesde = new SimpleDateFormat(dFormatdesde);
////                    String s1desde = sdfdesde.format(date1);//fecha desde en dd/MM/yyyy
//                             
//Date date2 = null;//desde
//        try {
//            date2 =  (Date) formatter.parse(hasta);
//        } catch (ParseException ex) {
//            Logger.getLogger(SAdmin.class.getName()).log(Level.SEVERE, null, ex);
//        }
////        System.out.println(date);
//        
//                   String dFormathasta = "dd/MM/yyyy";
//                    SimpleDateFormat sdfhasta = new SimpleDateFormat(dFormathasta);
//                    String s1hasta = sdfhasta.format(date2);//fecha hasta en dd/MM/yyyy
//                    

//        System.out.println(s1);



                    //Si alguno de los parámetros no existe
                    //No se muestra nada




                    if (desde != null && hasta != null) {
                        Li = dadmin.ListarEvento(desde, hasta);
                    } else {
                        desde = "";
                        hasta = "";
                    }
                    request.setAttribute("listaeventos", Li);
                    request.setAttribute("desde", desde);
                    request.setAttribute("hasta", hasta);
                    pagina = "/Admin/filterEvents.jsp";
                    break;

                case 12: // VERSION VER RUTA ============================== LISTA LUGARES

                    request.setAttribute("listalugares", dadmin.ListarLugares());
                    request.setAttribute("nombre", request.getParameter("nombre"));
                    request.setAttribute("descripcion", request.getParameter("descripcion"));

                    pagina = "/Admin/addLugar.jsp";
                    break;

                case 13:

                    bEvento = new BEvento();
                    bEvento.setActivo(1);
                    bEvento.setDescripcion(" ");
                    bEvento.setDuracion(0);
                    bEvento.setNombre(" ");
                    bEvento.setIdevento(0);
                    String fecha = "12/12/12";
                    String horax = "00:00 PM";
                    bLugar = new BLugar();
                    bLugar.setNombre(" ");
                    bLugar.setDireccion(" ");
                    String x = "";
                    String y = "";

                    request.setAttribute("hora", horax);
                    request.setAttribute("fecha", fecha);
                    request.setAttribute("nombre", x);
                    request.setAttribute("descripcion", y);
                    request.setAttribute("lugar", bLugar);

                    pagina = "/Admin/addEvents.jsp";
                    break;

                case 14: // VERSION VER RUTA ============================== GUARDA NUEVO LUGAR

                    bLugar = new BLugar();

                    bLugar.setNombre(request.getParameter("nombrex"));
                    bLugar.setDireccion(request.getParameter("direccion"));
                    bLugar.setLatitud(Float.parseFloat(request.getParameter("latitud")));
                    bLugar.setLongitud(Float.parseFloat(request.getParameter("longitud")));

                    dadmin.NuevoLugar(bLugar);


                    String fecha0 = "12/12/12";
                    String hora0 = "00:00 PM";

                    request.setAttribute("hora", hora0);
                    request.setAttribute("fecha", fecha0);
                    request.setAttribute("nombre", request.getParameter("nombre"));
                    request.setAttribute("descripcion", request.getParameter("descripcion"));
                    request.setAttribute("lugar", bLugar);

                    pagina = "/Admin/addEvents.jsp";
                    break;

                case 15:   // SELECCIONA UN LUGAR EXISTENTE ================


                    int idlu = Integer.parseInt(request.getParameter("data"));
                    bLugar = dadmin.DataLugar(idlu);

                    String fecha15 = "12/12/12";
                    String hora15 = "00:00 AM";

                    request.setAttribute("hora", hora15);
                    request.setAttribute("fecha", fecha15);
                    request.setAttribute("nombre", request.getParameter("nombre"));
                    request.setAttribute("descripcion", request.getParameter("descripcion"));
                    request.setAttribute("lugar", bLugar);

                    pagina = "/Admin/addEvents.jsp";
                    break;



                case 16:

                    bEvento = new BEvento();
                    bLugar = new BLugar();

//                    bEvento.setIdevento(Integer.parseInt(request.getParameter("data")));
                    bEvento.setNombre(request.getParameter("nombre"));
                    bEvento.setDescripcion(request.getParameter("descripcion"));

                    String fecha16x = request.getParameter("mydate1");//1
                    String hora16g = request.getParameter("mydate2");//2
                    String hora16 = "";//3
                    hora16 = hora16g.substring(6, 8);//2 //3
                    int hora26 = 0;//4
                    if (hora16.compareTo("PM") == 0) {//3
                        hora26 = Integer.parseInt(hora16g.substring(0, 2)) + 12;//2 //4
                        hora16g = String.valueOf(hora26) + "" + hora16g.substring(2, 5);//2 //4
                    }
                    if (hora16.compareTo("AM") == 0) {//3
                        hora26 = Integer.parseInt(hora16g.substring(0, 2));//2 //4
                        hora16g = String.valueOf(hora26) + "" + hora16g.substring(2, 5);//2 //4
                    }
                    String fecharesult16 = fecha16x + " " + hora16g;//1   //2    //la ultima

                    bEvento.setDuracion(Integer.parseInt(request.getParameter("duracion")));
                    bEvento.setActivo(Integer.parseInt(request.getParameter("radio-choice-1")));

                    bLugar.setNombre(request.getParameter("lugar"));
                    bLugar.setDireccion(request.getParameter("direccion"));


                    bEvento.setIdlugar(bLugar);
                    int idlugar16 = dadmin.sacarid();
                    dadmin.CrearEvento(bEvento, fecharesult16, idlugar16);

                    request.setAttribute("listaeventos", dadmin.ListarEvento());
                    pagina = "/Admin/adminEvents.jsp";
                    break;

                case 17:
                    bUsuario = new BUsuario();



                    bUsuario.setIdusuario(Integer.parseInt(request.getParameter("data")));
                    bUsuario.setNombre(request.getParameter("nombre"));
                    bUsuario.setApellido(request.getParameter("apellido"));
                    bUsuario.setUsuario(request.getParameter("usuario"));
                    //bUsuario.setContrasena(request.getParameter("password"));
                    bUsuario.setCorreo(request.getParameter("email"));
                    bUsuario.setEstado_logueo(Integer.parseInt(request.getParameter("radio-choice-1")));

                    String mail = request.getParameter("email");

                    String link = "http://localhost:8084" + request.getContextPath() + "/SLogin?accion=5&link=";
                    m.enviacorreo(mail, link);

                    request.setAttribute("datauser", bUsuario);
                    pagina = "/Admin/editUser.jsp";
                    break;



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
