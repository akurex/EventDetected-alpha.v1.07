/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.servlet;

import com.sw2.ed.bean.BEvento;
import com.sw2.ed.bean.BUsuario;
import com.sw2.ed.dao.DEventos;

import com.sw2.ed.dao.DLogin;
import com.sw2.ed.mail.Mail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;


public class SLogin extends HttpServlet {

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
            String accionStr = request.getParameter("accion") == null ? "7" : request.getParameter("accion");
            int accion = Integer.parseInt(accionStr);
            BUsuario bUsuario = null;

            DLogin dUsuario = new DLogin();
            DEventos dEvento = new DEventos();
            Mail m = new Mail();
            ArrayList<BEvento> L = new ArrayList<BEvento>();
            String pagina = "";
//          boolean flag=false; 
//           ArrayList<BUsuario> L = null;
            switch (accion) {
                case 1:
//                bUsuario=new BUsuario();

                    String user = String.valueOf(request.getParameter("username"));
                    String pass = String.valueOf(request.getParameter("password"));
//                bUsuario.setFlag(flag);

                    bUsuario = dUsuario.ValidarUsuario(user, pass);
//               bUsuario.setFlag(flag);
                    if (bUsuario != null) {
                        if (bUsuario.getEstado_logueo() != 0) {
                            if (bUsuario.getAdministrador() != 1) {
                                int id = bUsuario.getIdusuario();
                                L = dEvento.ListarEventoxUsuarioHoy(id);
                                request.setAttribute("eventoshoy", L);
                                L = dEvento.ListarEventoxUsuarioMañana(id);
                                request.setAttribute("eventosmañana", L);


                                HttpSession session = request.getSession(true);
                                session.setAttribute("usuario", bUsuario);
                                pagina = "/Eventos/eventos.jsp";

                            } else {
                                HttpSession session = request.getSession(true);
                                session.setAttribute("administrador", bUsuario);
                                pagina = "/Admin/index.jsp";
                            }
                        } else {
                            pagina = "/Eventos/index_nohabilitado.jsp";
                        }

                    } else {
                        pagina = "/Eventos/index_error.jsp";
                    }

                    break;

                case 2: // valida usuario

                    String mail = String.valueOf(request.getParameter("textinput1"));
                    bUsuario = dUsuario.ValidarCorreo(mail);
                    
                    //String remoteAddr = request.getRemoteAddr();
                    //ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
                    //reCaptcha.setPrivateKey("6LfPadISAAAAAP3IG_V8Y0CU2FXwtKWN6P1am6vl");

                    //String challenge = request.getParameter("recaptcha_challenge_field");
                    //String uresponse = request.getParameter("recaptcha_response_field");
                    //ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);



                    if (bUsuario != null) {

                        if (bUsuario.getCorreo().compareTo(mail) == 0) {

//                            pru.enviacorreo(mail, "/Contrasenha/Reestablecer.jsp");

                            //if (bUsuario.getCorreo().compareTo(mail) == 0 && reCaptchaResponse.isValid()) {
                            if (bUsuario.getCorreo().compareTo(mail) == 0) {
//                        out.print("Answer was entered correctly!");
                                String token = Long.toHexString(Double.doubleToLongBits(Math.random()));
                                //m.enviacorreo(mail, "http://localhost:8084/" + request.getContextPath() + "/SLogin?accion=3");
                                dUsuario.setToken(token, bUsuario.getCorreo());

                                String link = "http://localhost:8084" + request.getContextPath() + "/SLogin?accion=5&link=";

                                link += token;


                                m.enviacorreo(mail, link);
                                //http://localhost:8084//EventDetected/SLogin?accion=3


                                pagina = "/Enviado.jsp";
                            } else {
//                        out.print("Answer is wrong");
                                pagina = "/Contrasenha/Recuperar.jsp";
                            }

                        } else {

                            pagina = "/Contrasenha/Recuperar.jsp";
                        }
                    } else {

                        pagina = "/Contrasenha/Recuperar.jsp";
                    }
                    break;
                case 3:

                    //String correo = String.valueOf(request.getParameter("name"));


                    String password = String.valueOf(request.getParameter("password1"));
                    String passwordR = String.valueOf(request.getParameter("password2"));
                    String idusuario = request.getParameter("vidusuario");

                    if (passwordR.compareTo(password) == 0) {


                        dUsuario.cambiarContraseña(Integer.parseInt(idusuario), passwordR);
                        pagina = "/index.jsp";

                    } else {

                        pagina = "/Contrasenha/Reestablecer.jsp";
                    }


                    break;

                case 4:

                    HttpSession session = request.getSession(true);
                    session.invalidate();

                    pagina = "/index.jsp";
                    break;

                case 5:
                    String token = request.getParameter("link");
                    bUsuario = new BUsuario();
                    bUsuario = dUsuario.usuarioXToken(token);
                    if (bUsuario != null) {
                        token += "dsbld";
                        dUsuario.setToken(token, bUsuario.getCorreo());
                        request.setAttribute("busuario", bUsuario);
                        pagina = "/Contrasenha/Reestablecer.jsp";
                    } else {
                        pagina = "/Usado.jsp"; //jsp para enlace que  ya fue usado
                    }
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
