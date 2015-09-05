<%-- 
    Document   : nuevos_eventos
    Created on : 15-may-2012, 16:23:32
    Author     : alulab
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.sw2.ed.bean.BEvento, com.sw2.ed.bean.BLugar, com.sw2.ed.bean.BUsuario, java.util.ArrayList, java.util.Date, java.util.Locale"%>
<jsp:useBean id="listaeventos" type=" ArrayList<BEvento>" scope="request" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%

HttpSession session1 = request.getSession(true);
BUsuario usuario = (BUsuario) session1.getAttribute("usuario");

if(usuario.getNombre() == null){

    response.sendRedirect("index.jsp");
}
%>
<html> 
    <head> 
        <title>Event Detected</title> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <script src="js/jquery.js"></script>
        <script src="js/jquery.mobile-1.1.0.min.js"></script>
    </head> 
    <body> 


        <div data-role="page" >

            <div data-role="header" data-theme="f">
                <h1>Lista de Eventos:</h1> 
                <a href="<%= request.getContextPath()%>/SEventos?accion=10" data-role="button" data-theme="f">Regresar</a>                 
                <a href="<%= request.getContextPath()%>/index.jsp" data-icon="home" data-role="button" data-inline="true">Salir</a>               
                <h1>Hola <%=  usuario.getUsuario() %></h1>
            </div>

            
            
            <div data-role="content">
    <a data-ajax="false" data-inline="true" href="<%= request.getContextPath()%>/SEventos?accion=21" data-role="button" data-theme="f">Busqueda avanzada</a>  
<%--
                <ul data-role="listview" data-filter="true" data-inset="true" data-divider-theme="f" >                    
                    <li data-role="list-divider">Sábado, 26 de Mayo de 2012</li>
                    <li><a href="<%= request.getContextPath()%>/Eventos/evento_info1.jsp">Pichanga Teleca - 8:00</a></li>
                    <li><a href="index.html">Entrevista de trabajo - 13:00</a></li>                                        
                    <li><a href="index.html">Cena Familiar - 20:00</a></li>                    
                    <li><a href="index.html">Tono fin de parciales - 23:00</a></li>
                    <li data-role="fieldcontain" >
                        <h5><a href="<%= request.getContextPath()%>/Eventos/todos_eventos.jsp" data-role="button" data-theme="f">Ver Próximos Eventos</a></h5>
                        <h5><a href="<%= request.getContextPath()%>/Eventos/nuevos_eventos.jsp" data-role="button" data-theme="f">Buscar nuevos eventos</a></h5>
                    </li>
                </ul>
--%>
                <ul data-role="listview" data-inset="true" data-filter="true" data-divider-theme="f">
                    <li data-role="list-divider"><%
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
                        Date date = new Date();
                        String fecha=formateador.format(date);
                        out.println(fecha);
                    %></li>                                       
                    <%for (BEvento event : listaeventos) {%>
                    <li class="ui-li ui-li-static ui-body-c ui-corner-top" data-inset="true">
                        <a href="<%=request.getContextPath()%>/SEventos?event=<%=event.getIdevento()%>&accion=5" class="ui-link-inherit">
                            <h3 class="ui-li-heading"><%=event.getNombre()%> - <%=event.getFecha() %></h3>
                        </a>
                    </li>
                    <%}%>
                    <li data-role="fieldcontain" >
                        <h5><a data-ajax="false" href="<%= request.getContextPath()%>/SEventos?accion=2" data-role="button" data-theme="f">Ver Eventos en Mapa</a></h5>
                       
                </ul>

            </div><!-- /content -->

            <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
                <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
            </div><!-- /content -->            

        </div><!-- /page -->

    </body>
</html>
