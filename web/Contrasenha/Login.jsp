<%-- 
    Document   : Login
    Created on : 13-may-2012, 21:05:17
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <LINK REL="SHORTCUT ICON" HREF="/~your_directory/logo.ico">
        <title>Event Detected</title> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <script src="js/jquery.js"></script>
        <script src="js/jquery.mobile-1.1.0.min.js"></script>
    </head>
    <body>
        <div data-role="page">

            <div data-role="header">

            </div><!-- /header -->

            <div data-role="content">
                <div style="display: inline">
                    <img style="width: 288px; height: 80px" src="http://webpc.com.uy/wp-content/uploads/2010/10/windows-7-wallpaper.jpg" />
                </div>
                <p><font color="teal"><b>Nombre de usuario</b></font></p>
                <input type="text" name="name" id="basic" />
                <p><font color="teal"><b>Contraseña</b></font></p>
                <input type="password" name="password" id="password" value="" />
                <p></p>
                <a href="<%=request.getContextPath()%>/SCalendario?accion=1" data-role="button" data-theme="b" data-mini="true" data-inline="true">Iniciar sesión</a>
                <a href="<%=request.getContextPath()%>/SCalendario?accion=2" data-role="button" data-theme="b" data-mini="true" data-inline="true">Boton de prueba (borrar luego)</a>
                <p></p>
                <a style="text-decoration:none" href="<%=request.getContextPath()%>/SCalendario?accion=3">¿No puedes acceder a tu cuenta?</a>
            </div><!-- /content -->

        </div><!-- /page -->

    </body>
</html>
