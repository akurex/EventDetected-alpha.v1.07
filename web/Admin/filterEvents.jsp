<%-- 
    Document   : filterEvents
    Created on : 06-jun-2012, 21:28:07
    Author     : Aaron Villa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sw2.ed.bean.BEvento, com.sw2.ed.bean.BLugar, java.util.ArrayList"%>
<jsp:useBean id="listaeventos" type=" ArrayList<BEvento>" scope="request" />
<jsp:useBean id="desde" type="String" scope="request" />
<jsp:useBean id="hasta" type="String" scope="request" />

<%@page import="com.sw2.ed.bean.BUsuario"%>
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
        <title>Event Detected</title> 
        <LINK REL="SHORTCUT ICON" HREF="<%= request.getContextPath()%>/imgs/map2.ico"> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <script src="js/jquery.js"></script>
        <link rel="stylesheet" type="text/css" href="http://dev.jtsage.com/cdn/datebox/latest/jquery.mobile.datebox.min.css" /> 

        <script src="js/jquery.js"></script>
        <script type="text/javascript">
            $(document).bind("mobileinit", function() {
                $.mobile.ajaxEnabled = false;
            })
        </script>  
        <script src="js/jquery.mobile-1.1.0.min.js"></script>

        <script type="text/javascript" src="js/jquery.mobile.datebox.js"></script>
        <script type="text/javascript" src="js/jquery.mobile.datebox.i8n.en.js"></script>

        <script type="text/javascript" src="js/jquery.validationEngine-en.js" charset="utf-8"></script>
        <script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
        <script type="text/javascript" src="js/pre_utopia_scripts.js"></script>  
        <link type="text/css" rel="stylesheet" href="css/validationEngine.jquery.css"/>	

    </head>
    <body>
        <div data-role="page" id="admin.events">

            <div data-role="header" data-theme="f">
                <a href="<%= request.getContextPath()%>/SAdmin?accion=2" data-role="button" data-icon="arrow-l" data-inline="true" data-mini="true">Regresar</a>
                <h1>Filtrado de Eventos</h1>
            </div><!-- /header -->
            <br>
            <form action="<%= request.getContextPath()%>/SAdmin">
                <input type="hidden" name="accion" value="11"/>
                <div data-role="content">
                    <ul data-role="listview" data-inset="true" data-inline="true">

                        <li>
                            <label for="mydate1">Desde:</label>

                            <input name="mydate1" id="mydate1" type="date" data-role="datebox"
                                   data-options='{"mode": "slidebox", "dateFormat":"%d/%m/%y" }' value="<%=desde%>">
                        </li>

                        <li>
                            <label for="mydate2">Hasta:</label>

                            <input name="mydate2" id="mydate2" type="date" data-role="datebox"
                                   data-options='{"mode": "slidebox", "dateFormat":"%d/%m/%y" }' value="<%=hasta%>">
                        </li>
                    </ul>
                    <!--<div data-role="fieldcontain">
                        <label for="name">Desde: </label>
                        <input type="text" data-inline="true" name="desde" id="desde" value="<%=desde%>"  /><label for="formato">(dia/mes/año) </label>
                    </div>
                    
                    <div data-role="fieldcontain">
                        <label for="name">Hasta: </label>
                        <input type="text" name="hasta" id="hasta" value="<%=hasta%>"  /><label for="formato">(dia/mes/año) </label>
                    </div>!-->
                    <div data-role="fieldcontain">
                        <input type="submit" data-inline="true" value="Filtrar"  />
                    </div>    
                </div>

            </form>

            <div data-role="content">

                <ul data-role="listview" data-inset="true" data-filter="false" data-divider-theme="f">
                    <li data-role="list-divider">Eventos en el sistema</li>                                       
                    <%for (BEvento event : listaeventos) {%>                    
                    <%if (event.getActivo() == 1) {
                    %><li class="ui-li ui-li-static ui-body-c ui-corner-top" data-inset="true" data-theme="c"><%                    } else {
                    %><li class="ui-li ui-li-static ui-body-c ui-corner-top" data-inset="true" data-theme="h"><%                                                }
                        %>                    
                        <a data-ajax="false" href="<%=request.getContextPath()%>/SAdmin?data=<%=event.getIdevento()%>&accion=5" class="ui-link-inherit">

                            <p class="ui-li-aside ui-li-desc">
                                <strong><%if (event.getActivo() == 1) {
                                        out.println("ACTIVO");
                                    } else {
                                        out.println("BLOQUEADO");
                                    }

                                    %></strong></p>
                            <h3 class="ui-li-heading"><%=event.getNombre()%></h3>
                            <p class="ui-li-desc"><%=event.getDescripcion()%></p>

                        </a>
                    </li>
                    <%}%>
                </ul>
            </div><!-- /content --> 

            <br/>
            <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
                <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
            </div><!-- /content --> 



        </div><!-- /page -->

    </body>
</html>
