<%-- 
    Document   : busqueda_avanzada1
    Created on : 09/07/2012, 02:09:13 PM
    Author     : Miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.sw2.ed.bean.BEvento, com.sw2.ed.bean.BUsuario"%>
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
        <title>Event Detected</title> 

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

        <div data-role="page" >

            <div data-role="header" data-theme="f">
                <a href="" data-icon="back" data-role="button" data-rel="back" data-inline="true">Atrás</a>               
                <h1>Eventos...</h1> 
                <a href="<%= request.getContextPath()%>/SLogin?accion=4" data-icon="home" data-role="button" data-inline="true">Salir</a>               

            </div>
            <form id="frmPage1" action="<%=request.getContextPath()%>/SEventos" method="Post" name="PForm1">
                <div data-role="content">	

                    <ul data-role="listview" data-inset="true" data-divider-theme="f" >
                        <li data-role="list-divider" ><h1>Búsqueda Avanzada</h1></li>
                        <li data-role="fieldcontain">
                            <label for="nombre">Nombre:</label>
                            <input type="search" name="nombre" id="nombre" value="" />
                        </li>                    
                        <li data-role="fieldcontain">
                            <label for="lugar">Lugar:</label>
                            <input type="search" name="lugar" id="lugar" value="" />
                        </li>
                        <li>
                            <label for="mydate1">Fecha Inicio: </label>

                            <input name="mydate1" id="mydate1" type="date" data-role="datebox"
                                   data-options='{"mode": "slidebox"}' >
                        </li>
                        <li>
                            <label for="mydate2">Fecha Fin: </label>

                            <input name="mydate2" id="mydate2" type="date" data-role="datebox"
                                   data-options='{"mode": "slidebox"}' >
                        </li>
                        <li><a href="javascript:go()" data-role="button" data-theme="f">Buscar en Mapa</a></li>
                        <li data-role="fieldcontain" >
                            <button type="submit" data-theme="f" name="submit" value="submit-value" >Buscar</button>    
                            <input type="hidden" name="data" id="data" value="<%=usuario.getIdusuario()%>" />
                            <input type="hidden" name="accion" id="accion" value="16" />


                        </li>    

                    </ul>


                </div><!-- /content -->
            </form>


            <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
                <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
            </div><!-- /content -->            

        </div><!-- /page -->

    </body>
    <script>
        function go() {
            window.document.location.href="<%= request.getContextPath()%>/SEventos?accion=25&nombre="+window.document.getElementById('nombre').value
                +"&lugar="+window.document.getElementById('lugar').value+"&mydate1="+window.document.getElementById('mydate1').value
                +"&mydate2="+window.document.getElementById('mydate2').value;
        } 
    </script>
</html>
