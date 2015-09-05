<%-- 
    Document   : resultado_busqueda1
    Created on : 09/07/2012, 02:23:03 PM
    Author     : Miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.sw2.ed.bean.BEvento, com.sw2.ed.bean.BUsuario, java.util.ArrayList, java.util.Date, java.util.Locale"%>
<jsp:useBean id="listaeventos" type=" ArrayList<BEvento>" scope="request" />

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
        <link rel="stylesheet" href="css/custom.css" />
        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <script src="js/jquery.js"></script>
        <script src="js/jquery.mobile-1.1.0.min.js"></script>
    </head> 
    <body> 

        <div data-role="page" >

            <div data-role="header" data-theme="f">
                <a href="" data-icon="back" data-rel="back" data-role="button" data-inline="true">Atrás</a>               
                <h1>Eventos...</h1> 
                <a href="<%= request.getContextPath()%>/SLogin?accion=4" data-icon="home" data-role="button" data-inline="true">Salir</a>               

            </div>

          

              <div data-role="content">
    
            
      <ul data-role="listview" data-inset="true" data-filter="true" data-divider-theme="f">
                    <li data-role="list-divider"><%
                        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
                        Date date = new Date();
                        String fecha=formateador.format(date);
                        out.println(fecha);
                    %></li>                                       
                    <%for (BEvento event : listaeventos) {%>
                    <li class="ui-li ui-li-static ui-body-c ui-corner-top" data-inset="true">
                        <a href="<%=request.getContextPath()%>/SEventos?event=<%=event.getIdevento()%>&accion=9" class="ui-link-inherit">
                            <h3 class="ui-li-heading"><%=event.getNombre()%> - <%=event.getFecha() %></h3>
                        </a>
                    </li>
                    <%}%>
                   
                </ul>


            </div><!-- /content -->


          

            <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
                <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
            </div><!-- /content -->            

        </div><!-- /page -->

    </body>
</html>
