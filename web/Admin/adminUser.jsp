<%-- 
    Document   : adminUser
    Created on : 15-may-2012, 17:31:11
    Author     : Denise
--%>

<%@page import="com.sw2.ed.bean.BUsuario, java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="listausuarios" type=" ArrayList<BUsuario>" scope="request" />
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

        <script type="text/javascript">
            function enviarForm(){
                var formulario = document.getElementById("busquedaForm");
                formulario.submit();
            }

  
        </script>        

    </head> 

    <body>

        <div data-role="page" >


            <div data-role="header" data-theme="f">
                <a href="<%= request.getContextPath()%>/SAdmin?accion=9" data-role="button" data-icon="arrow-l" data-inline="true" data-mini="true">Regresar</a> <!-- /Debe regresar a la pantalla de login -->
                <h1>Administración de Usuarios</h1>
                <a data-ajax="false" href="<%= request.getContextPath()%>/SAdmin?accion=7" data-icon="plus" data-inline="true" data-mini="true" data-transition="pop">Añadir</a>


            </div><!-- /header -->

            <br>
            <form id="busquedaForm" action ="<%=request.getContextPath()%>/SAdmin?accion=1" method="post">
                <fieldset data-role="controlgroup"  data-type="horizontal" >

                    <legend>Elija una opcion:</legend>
                    <input type="radio" name="radios" id="radio-choice-1" value="Todos" onchange="var formulario = document.getElementById('busquedaForm');
                           formulario.submit();" />
                           <label for="radio-choice-1">Todos</label>

                    <input type="radio" name="radios" id="radio-choice-2" value="Activos"  onchange="var formulario = document.getElementById('busquedaForm');
                           formulario.submit();"/>
                           <label for="radio-choice-2">Activos</label>

                    <input type="radio" name="radios" id="radio-choice-3" value="Bloqueados"  onchange="var formulario = document.getElementById('busquedaForm');
                           formulario.submit();"/>
                           <label for="radio-choice-3">Bloqueados</label>

                </fieldset>
            </form>

            <div data-role="content">

                <ul data-role="listview" data-inset="true" data-filter="true" data-divider-theme="f">
                    <li data-role="list-divider">Usuarios en el sistema</li>
                    <%for (BUsuario user : listausuarios) {%>

                    <%if (user.getEstado_logueo() == 1) {
                    %><li class="ui-li ui-li-static ui-body-c ui-corner-top" data-inset="true" data-theme="c"><%                    } else {
                    %><li class="ui-li ui-li-static ui-body-c ui-corner-top" data-inset="true" data-theme="h"><%                                                }
                        %>                    


                        <a data-ajax="false" href="<%=request.getContextPath()%>/SAdmin?data=<%=user.getIdusuario()%>&accion=3" class="ui-link-inherit">

                            <p class="ui-li-aside ui-li-desc">
                                <strong><%if (user.getEstado_logueo() == 1) {
                                        out.println("Activo");
                                    } else {
                                        out.println("Bloqueado");
                                    }

                                    %></strong></p>
                            <h3 class="ui-li-heading"><%=user.getUsuario()%></h3>
                            <h3 class="ui-li-heading"><%=user.getNombre()%></h3>
                            <p class="ui-li-desc"><%=user.getApellido()%></p>
                            <p class="ui-li-desc"><%=user.getCorreo()%></p>

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
