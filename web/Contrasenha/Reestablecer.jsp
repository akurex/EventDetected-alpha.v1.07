<%-- 
    Document   : Reestablecer
    Created on : 14-may-2012, 14:21:07
    Author     : User
--%>

<%@page import="com.sw2.ed.bean.BUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="busuario" type="BUsuario" scope="request" />
<!DOCTYPE html>
<html>
    <head>
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
        <div data-role="page">

            <div data-role="header" data-theme="f">

            </div><!-- /header -->
            <form id="frmPage1" action="<%= request.getContextPath()%>/SLogin" method="Post" name="PForm1">
                <div data-role="content">	
                    <p><font color="teal"><b>Correo electrónico</b></font></p>
                    <input type="text" name="name" id="basic" value="<%=busuario.getCorreo()%>" disabled="true"/>
                    <p><font color="teal"><b>Nueva Contraseña</b></font></p> 
                    <input type="password" name="password1" id="password1" value="" class="validate[required,minSize[4]]"/>
                    <p><font color="teal"><b>Reingrese la nueva Contraseña</b></font></p>
                    <input type="password" name="password2" id="password2" value="" class="validate[required,minSize[4]]"/>
                    <p></p>
                    <button type="submit" data-theme="f" name="submit" value="submit-value">Aceptar</button>
                    <input type="hidden" name="accion" id="accion" value="3">
                    <input type="hidden" id="vidusuario" name="vidusuario" value="<%= busuario.getIdusuario()%>">
                       
                </div><!-- /content -->
            </form>
        </div><!-- /page -->
    </body>
</html>
