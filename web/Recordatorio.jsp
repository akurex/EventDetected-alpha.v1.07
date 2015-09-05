<%-- 
    Document   : Recordatorio
    Created on : 19-jun-2012, 12:31:40
    Author     : kristiam
--%>

<%@page import="com.sw2.ed.bean.BEvento"%>
<%@page import="com.sw2.ed.bean.BUsuario"%>
<jsp:useBean id="evento" type="BEvento" scope="request" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    HttpSession session1 = request.getSession(true);
    BUsuario usuario = (BUsuario) session1.getAttribute("usuario");

    if (usuario.getNombre() == null) {

        response.sendRedirect("index.jsp");
    }
%>
<html>
    <head>
        <LINK REL="SHORTCUT ICON" HREF="<%= request.getContextPath()%>/imgs/map2.ico">
        <title>Event Detected</title> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <script src="js/jquery.js"></script>
        <script src="js/jquery.mobile-1.1.0.min.js"></script>
    </head>
    <body>
        <div data-role="dialog" id="popupBasic" data-theme="f"> <!-- /popup de recordatorio -->

            <div data-role="header" data-theme="f" >

                <h1>Event Detected</h1>
            </div><!-- /header -->

            <div data-role="content" data-theme="c">	                
                <p>Desea agregar un recordatorio</p>		
                <a data-ajax="false" href="<%= request.getContextPath()%>/SEventos?event=<%=evento.getIdevento()%>&accion=18" data-role="button" data-theme="f" data-inline="true" data-icon="back">Si</a>
                <a href="<%= request.getContextPath()%>/SEventos?event=<%=evento.getIdevento()%>&accion=11" data-role="button" data-theme="f" data-inline="true" data-icon="back">No</a>
            </div>
        </div>
    </body>
</html>
