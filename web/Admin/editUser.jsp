<%-- 
    Document   : editUser
    Created on : 15-may-2012, 23:32:35
    Author     : Denise
--%>

<%@page import="com.sw2.ed.bean.BUsuario, com.sw2.ed.dao.DAdmin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="datauser" type="BUsuario" scope="request" />

<!DOCTYPE html>
<%

    HttpSession session1 = request.getSession(true);
    BUsuario usuario = (BUsuario) session1.getAttribute("administrador");

    if (usuario.getNombre() == null) {

        response.sendRedirect("index.jsp");
    }

%>
<html>
    <head>
        <LINK REL="SHORTCUT ICON" HREF="/~your_directory/logo.ico">
        <title>Event Detected</title> 
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />        
        <script src="js/jquery.js"></script>
        <script type="text/javascript">
            $(document).bind("mobileinit", function() {
                $.mobile.ajaxEnabled = false;
            })
        </script>  
        <script src="js/jquery.mobile-1.1.0.js"></script>

        <script type="text/javascript" src="js/jquery.validationEngine-en.js" charset="utf-8"></script>
        <script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
        <script type="text/javascript" src="js/pre_utopia_scripts.js"></script>  
        <link type="text/css" rel="stylesheet" href="css/validationEngine.jquery.css"/>	

    </head>


    <body>

        <div data-role="page" id="page1form1">


            <div data-role="header" data-theme="f">
                <a href="<%=request.getContextPath()%>/SAdmin?accion=1" data-role="button" data-icon="arrow-l" data-inline="true" data-mini="true">Regresar</a>
                <% if (datauser.getIdusuario() > 0) {%>
                <h1>Editar Usuarios</h1>
                <%} else {%>
                <h1>Agregar Usuarios</h1>
                <% }%>

                <a href="<%=request.getContextPath()%>/SLogin?accion=4" data-role="button" date-icon="delete" data-inline="true" data-mini="true">Salir</a> 
            </div><!-- /header -->

            <br>
            <br>

            <form id="frmPage1" action="<%=request.getContextPath()%>/SAdmin" method="post" name="PForm1">

                <ul data-role="listview" data-inset="true">

                    <li data-role="fieldcontain">
                        <label for="nombre">Nombres:</label>
                        <input type="text" name="nombre" required id="nombre" value="<%= datauser.getNombre()%>" class="validate[custom[onlyLetterSp],length[0,8]]"/>
                    </li>

                    <%--
                                <% String mensaje= "";
                                        if (flag == 1) {
                                        mensaje = "Username inválido";}
                                %>
                                <li ><%=flag%>
                                    <div id='username_availability_result'></div>  
                                    </li>

                    --%>


                    <li data-role="fieldcontain">
                        <label for="apellido">Apellidos:</label>
                        <input type="text" name="apellido" required id="apellido" value="<%= datauser.getApellido()%>" class="validate[custom[onlyLetterSp],length[0,8]]" />
                    </li>



                    <li data-role="fieldcontain">
                        <label for="usuario">Usuario:</label>
                        <input type="text" name="usuario" required  id="usuario" value="<%= datauser.getUsuario()%>" class="validate[custom[onlyLetterNumber],length[0,6]]" />
                    </li>


                    <%--                <li data-role="fieldcontain">
                                        <label for="password">Password:</label>
                                        <input type="text" name="password" id="password" value="<%= datauser.getContrasena()%>" />
                                    </li>
                    --%>


                    <li data-role="fieldcontain">
                        <label for="email">Email:</label>
                        <input type="email" name="email" required id="email" value="<%= datauser.getCorreo()%>" />
                    </li>




                    <li data-role="fieldcontain">
                        <div data-role="fieldcontain">
                            <fieldset data-role="controlgroup">
                                <legend>Estado:</legend>

                                <%if (datauser.getEstado_logueo() == 1) {%> 
                                <input type="radio" name="radio-choice-1" id="radio-choice-1" 
                                       value="1" checked="checked"</>
                                <label for="radio-choice-1">Activo</label>
                                <input type="radio" name="radio-choice-1" id="radio-choice-2" value="0" />
                                <label for="radio-choice-2">Bloqueado</label>


                                <%} else {
                                %>
                                <input type="radio" name="radio-choice-1" id="radio-choice-1" 
                                       value="1"</>
                                <label for="radio-choice-1">Activo</label>
                                <input type="radio" name="radio-choice-1" id="radio-choice-2" value="0" checked="checked"  />
                                <label for="radio-choice-2">Bloqueado</label>
                                <% }%>

                            </fieldset>
                        </div>
                        <br>
                    </li>


                    <div class="ui-grid-a">
                        <div class="ui-block-a" align="left">
                            <button type="submit" data-theme="f" name="submit" value="submit-value" >Guardar</button>
                            <input type="hidden" name="data" id="data" value="<%=datauser.getIdusuario()%>" />
                            <input type="hidden" name="estadoLogueo" id="estadoLogueo" value="<%=datauser.getEstado_logueo()%>" />
                            <input type="hidden" name="accion" id="accion" value="4" />
                        </div>


                    </div>

                    <%-- <div class="ui-block-b" align="right">

                        <a href="javascript:go()" data-role="button" data-theme="f" data-inline="true">Activar</a>
                    </div>--%>
                </ul>
            </form>

            <br>
            <br>


            <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
                <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
            </div><!-- /content -->  

        </div>


    </body>

    <%--<script>
        function go() {
            window.document.location.href="<%= request.getContextPath()%>/SAdmin?accion=17&email="+window.document.getElementById('email').value
                + "&nombre="+window.document.getElementById('nombre').value
                + "&apellido="+window.document.getElementById('apellido').value
                + "&usuario="+window.document.getElementById('usuario').value
                + "&radio-choice-1="+window.document.getElementById('radio-choice-1').value
                + "&data="+window.document.getElementById('data').value;
                
                
        } 
    </script> --%>


</html>