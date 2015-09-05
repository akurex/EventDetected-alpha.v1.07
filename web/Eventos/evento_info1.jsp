<%-- 
    Document   : evento_info1
    Created on : 15-may-2012, 11:30:36
    Author     : jeison
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sw2.ed.bean.BEvento, com.sw2.ed.bean.BUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dataevento" type="BEvento" scope="request" />
<jsp:useBean id="listapartes" type=" ArrayList<String>" scope="request" />

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

        <div data-role="header" data-theme="f">
            <a href="<%= request.getContextPath()%>/SEventos?accion=10" data-role="button" data-theme="f">Regresar</a> 
            <h1>Detalles del evento</h1>
            <a href="<%= request.getContextPath()%>/SLogin?accion=4" data-role="button" data-icon="arrow-l" data-inline="true" data-mini="true">Salir</a>
        </div><!-- /header -->

        <br>
        <br>
        <ul data-role="listview" data-inset="true">

            <li data-role="fieldcontain">
                <label for="nombre">Nombre:</label>
                <input type="text" name="nombre" id="nombre" disabled value="<%= dataevento.getNombre()%>" />
            </li>


            <li data-role="fieldcontain">
                <label for="nombre">Descripción:</label>
                <input type="text" name="descripcion" id="descripcion" disabled value="<%= dataevento.getDescripcion()%>" />
            </li>

            <li data-role="fieldcontain">
                <fieldset data-role="controlgroup" data-type="horizontal">
                    <legend>Fecha de Inicio:</legend>

                    <label for="select-choice-month">Mes</label>
                    <select disabled name="select-choice-month" id="select-choice-month" >
                        <option value="0"><%= listapartes.get(1)%></option>

                    </select>

                    <label for="select-choice-day">Dia</label>
                    <select disabled name="select-choice-day" id="select-choice-day">
                        <option value="0"><%= listapartes.get(0)%></option>

                    </select>

                    <label for="select-choice-year">Año</label>
                    <select disabled name="select-choice-year" id="select-choice-year">
                        <option value="2000" ><%= listapartes.get(2)%></option>


                    </select>
                </fieldset>
            </li>

            <li data-role="fieldcontain">
                <fieldset data-role="controlgroup" data-type="horizontal">
                    <legend>Hora Inicio:</legend>

                    <label for="select-choice-hour">Hora</label>
                    <select name="select-choice-hour" id="select-choice-hour">
                        <option value="00"><%= listapartes.get(3)%></option>


                    </select>

                    <label for="select-choice-day">Minuto</label>
                    <select name="select-choice-day" id="select-choice-day">
                        <option value="00"><%= listapartes.get(4)%></option>                                                 


                    </select>

                </fieldset>



            </li>


            <li data-role="fieldcontain">
                <fieldset class="ui-grid-a">
                    <div class="ui-block-a">
                        <label for="lugar">Duración: </label>
                        <input type="text" name="duracion" id="duracion" disabled value="<%=dataevento.getDuracion()%>" />

                        <label for="lugar">Lugar: </label>
                        <input type="text" name="lugar" id="lugar" disabled value="<%=dataevento.getIdlugar().getNombre()%>" />

                        <label for="lugar">Direccion: </label>
                        <input type="text" name="direccion" id="direccion" disabled value="<%=dataevento.getIdlugar().getDireccion()%>" />                            

                    </div>
                </fieldset>


            </li>



            <li data-role="fieldcontain">
                <div class="ui-block-b"><a data-ajax="false" href="<%= request.getContextPath()%>/SEventos?event=<%=dataevento.getIdevento()%>&accion=6" data-role="button" data-theme="f">Ver Mapa</a> </div>
                <div class="ui-block-b"><a  data-ajax="false" href="<%= request.getContextPath()%>/SEventos?event=<%=dataevento.getIdevento()%>&accion=8" data-role="button" data-theme="f">Remover de mis eventos</a>    </div>
                <div class="ui-block-b"><a data-ajax="false" href="<%= request.getContextPath()%>/SEventos?event=<%=dataevento.getIdevento()%>&accion=24" data-role="button" data-theme="f">Agregar Recordatorio</a> </div>
            </li>  

            <!-- AddThis Button BEGIN -->
            
           
            
        

        <br>
        <br>
        
         <li data-role="fieldcontain">
            <div class="addthis_toolbox addthis_default_style addthis_32x32_style">
                <a class="addthis_button_preferred_1"></a>
                <a class="addthis_button_preferred_2"></a>
                <a class="addthis_button_preferred_3"></a>
                <a class="addthis_button_preferred_4"></a>
                <a class="addthis_button_compact"></a>
                <a class="addthis_counter addthis_bubble_style"></a>
            </div>
            <script type="text/javascript">var addthis_config = {"data_track_addressbar":true};</script>
            <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-4ffc672779be6f36"></script>
            <!-- AddThis Button END -->
            </li>
        
        </ul>
        <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
            <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
        </div><!-- /content -->            




    </body>
</html>
