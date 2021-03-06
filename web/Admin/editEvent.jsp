<%-- 
    Document   : editEvent
    Created on : 16-may-2012, 0:08:05
    Author     : Denise
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.sw2.ed.bean.BEvento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dataevento" type="BEvento" scope="request" />
<jsp:useBean id="fecha" type="String" scope="request" />
<jsp:useBean id="hora" type="String" scope="request" />
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

        <div data-role="header" data-theme="f">
            <a href="<%=request.getContextPath()%>/SAdmin?accion=2" data-role="button" data-icon="arrow-l" data-inline="true" data-mini="true">Regresar</a>
            <h1>Editar Eventos</h1>
            <a href="<%= request.getContextPath()%>/SLogin?accion=4" data-role="button" data-icon="arrow-l" data-inline="true" data-mini="true">Salir</a>
        </div><!-- /header -->

        <br>
        <br>
        <form id="frmPage1" action="<%=request.getContextPath()%>/SAdmin" method="Post" name="PForm1">

            <ul data-role="listview" data-inset="true">

                <li data-role="fieldcontain">
                    <label for="nombre">Nombre:</label>
                    <input type="text" name="nombre" id="nombre" value="<%= dataevento.getNombre()%>" class="validate[custom[onlyLetterNumber],length[0,6]]" />
                </li>


                <li data-role="fieldcontain">
                    <label for="descripcion">Descripcion:</label>
                    <input type="text" name="descripcion" id="descripcion" value="<%= dataevento.getDescripcion()%>" class="validate[custom[onlyLetterNumber],length[0,6]]"/>
                </li>


                <li>
                    <label for="mydate1">Fecha de Inicio</label>

                    <input name="mydate1" id="mydate1" type="date" data-role="datebox"
                           data-options='{"mode": "slidebox", "dateFormat":"%Y/%m/%d" }' value="<%=fecha%>">
                </li>

                <li>
                    <label for="mydate2">Hora de Inicio</label>

                    <input name="mydate2" id="mydate2" type="date" data-role="datebox"
                           data-options='{"mode": "timeflipbox", "timeFormatOverride": 24}' value="<%=hora%>">
                </li>
                <li>
                    <label for="duracion">Duracion:</label>
                    <input name="duracion" id="duracion" type="date" data-role="datebox"
                           data-options='{"mode": "durationbox", "durationOrder": ["h"]}' value="<%=dataevento.getDuracion()%>" >
                </li>
                <li data-role="fieldcontain">
                    <fieldset class="ui-grid-a">
                        <div class="ui-block-a">
                            <label for="lugar">Lugar: </label>
                            <input type="text" name="lugar" id="lugar" value="<%=dataevento.getIdlugar().getNombre()%>" class="validate[custom[onlyLetterNumber],length[0,6]]" />

                            <label for="lugar">Direccion: </label>
                            <input type="text" name="direccion" id="direccion" value="<%=dataevento.getIdlugar().getDireccion()%>" class="validate[custom[onlyLetterNumber],length[0,6]]" />                            

                        </div>
                    </fieldset>
                </li>

                <li data-role="fieldcontain">
                    <div data-role="fieldcontain">
                        <fieldset data-role="controlgroup">

                            <legend>Estado:</legend>

                            <%if (dataevento.getCancelado() == 1) {%> 
                            <input type="radio" name="radio-choice-1" id="radio-choice-1" 
                                   value="1" checked="checked"</>
                            <label for="radio-choice-1">Activo</label>
                            <input type="radio"  name="radio-choice-1" id="radio-choice-2" value="0" />
                            <label for="radio-choice-2">Cancelado</label>


                            <%} else {
                            %>
                            <input type="radio" name="radio-choice-1" id="radio-choice-1"  value="1"</>
                            <label for="radio-choice-1">Activo</label>
                            <input type="radio" name="radio-choice-1" id="radio-choice-2" value="0" checked="checked"  />
                            <label for="radio-choice-2">Cancelado</label>
                            <% }%>

                        </fieldset>
                    </div>
                    <br>
                </li>

                <li data-role="fieldcontain">
                    <div data-role="fieldcontain">

                        <a href="javascript:go()" data-role="button" data-theme="f"  data-inline="true" >Guardar</a>

                        <button data-ajax="false" type="submit" data-inline="true" data-theme="f" name="submit" value="submit-value">Ver Mapa</button>    
                        <input type="hidden" name="data" id="data" value="<%=dataevento.getIdevento()%>" />
                        <input type="hidden" name="accion" id="accion" value="12" />
                    </div>
                </li>  


            </ul>
        </form>

        <br>
        <br>
        <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
            <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
        </div><!-- /content -->            




    </body>

    <script>
        function go() {
                
            window.document.location.href="<%= request.getContextPath()%>/SAdmin?accion=10&data=<%=dataevento.getIdevento()%>&nombre="+window.document.getElementById('nombre').value
                +"&descripcion="+window.document.getElementById('descripcion').value+"&mydate1="+window.document.getElementById('mydate1').value
                +"&mydate2="+window.document.getElementById('mydate2').value+"&duracion="+window.document.getElementById('duracion').value
                +"&lugar="+window.document.getElementById('lugar').value+"&direccion="+window.document.getElementById('direccion').value
                +"&radio-choice-1="+window.document.getElementById('radio-choice-1').checked;
        } 
    </script>

</html>

