<%-- 
    Document   : index
    Created on : 18-may-2012, 16:21:01
    Author     : Denise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sw2.ed.bean.BUsuario"%>
<!DOCTYPE html>
<%

HttpSession session1 = request.getSession(true);
BUsuario usuario = (BUsuario) session1.getAttribute("administrador");

if(usuario.getNombre() == null){

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
        <script src="js/jquery.mobile-1.1.0.min.js"></script>

    </head>
    <body>

        <div data-role="header" data-theme="f">
            
            <h1>Administración</h1>
            <a href="<%=request.getContextPath()%>/SLogin?accion=4" data-role="button" data-icon="arrow-l" data-inline="true" data-mini="true" style="text-align: left;">Salir</a>    
        </div><!-- /header -->

        <div data-role="content">	

            <ul data-role="listview" data-inset="true" data-divider-theme="f">
                <li data-role="list-divider">Mantenimiento</li>
                <li><a href="<%=request.getContextPath()%>/SAdmin?accion=1">Administracion de Usuarios</a></li>
                <li><a href="<%=request.getContextPath()%>/SAdmin?accion=2">Administracion de Eventos</a></li>

            </ul>		
        </div>

        <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
            <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
        </div><!-- /content -->            

    </body>
</html>
