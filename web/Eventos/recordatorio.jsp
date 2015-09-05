<%-- 
    Document   : recordatorio
    Created on : 17-may-2012, 14:39:19
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sw2.ed.bean.BUsuario, com.sw2.ed.bean.BCalendario"%>
<jsp:useBean id="idcalendar" type="BCalendario" scope="request" />
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
        <LINK REL="SHORTCUT ICON" HREF="<%= request.getContextPath()%>/imgs/map2.ico"> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <link rel="stylesheet" type="text/css" href="http://dev.jtsage.com/cdn/datebox/latest/jquery.mobile.datebox.min.css" /> 

        <script src="<%= request.getContextPath()%>/js/jquery.js"></script>
        <script type="text/javascript">
            $(document).bind("mobileinit", function() {
                $.mobile.ajaxEnabled = false;
            })
        </script>  
        <script src="<%= request.getContextPath()%>/js/jquery.mobile-1.1.0.min.js"></script>

        <script type="text/javascript" src="js/jquery.mobile.datebox.js"></script>
        <script type="text/javascript" src="js/jquery.mobile.datebox.i8n.en.js"></script>

    </head> 
    <body>
        <div data-role="page" >




            <div data-role="header" data-theme="f">
                <a href="" data-rel="back" data-ajax="false" data-role="button" data-icon="arrow-l" data-inline="true" data-mini="true">Regresar</a>
                <h1>Detalles del evento</h1>
                <a href="<%= request.getContextPath()%>/SLogin?accion=4" data-role="button" data-icon="arrow-l" data-inline="true" data-mini="true">Salir</a>
            </div><!-- /header -->





            <div data-role="content">

                <div data-role="collapsible" >
                    <h3>Básico</h3>


                    <form id="frmPage1" action="<%= request.getContextPath()%>/SEventos" method="Post" name="form1">
                        <div data-role="fieldcontain">
                            <legend><b>Recordatorio Basico</b>  </legend>
                            <fieldset data-role="controlgroup">
                                <input type="radio" name="radio-choice-1" id="radio-choice-1" value="15" checked="checked" />
                                <label for="radio-choice-1">15 min</label>

                                <input type="radio" name="radio-choice-1" id="radio-choice-2" value="30"  />
                                <label for="radio-choice-2">30 min</label>

                                <input type="radio" name="radio-choice-1" id="radio-choice-3" value="60"  />
                                <label for="radio-choice-3">60 min</label>

                                
                            </fieldset>
                        </div>
                        <button type="submit" data-theme="f" name="submit" value="submit-value">Agregar Recordatorio</button>
                        <input type="hidden" name="calendar" id="calendar" value="<%=idcalendar.getIdcalendario()%>" />
                        <input type="hidden" name="accion" id="accion" value="20" />


                    </form>   


                    </p>
                </div>

                <div data-role="collapsible" >
                    <h3>Avanzado</h3>


                    <li>
                        <label for="mydate1">Fecha de Inicio</label>

                        <input name="mydate1" id="mydate1" type="date" data-role="datebox"
                               data-options='{"mode": "slidebox" , "dateFormat":"%Y/%m/%d" }' value="">
                    </li>

                    <li>
                        <label for="mydate2">Hora de Inicio</label>

                        <input name="mydate2" id="mydate2" type="date" data-role="datebox"
                               data-options='{"mode": "timeflipbox", "timeFormatOverride": 24 }' value="">
                    </li>

                    <h5><a <a href="javascript:go()" data-role="button" data-theme="f">Agregar Recordatorio</a></h5></p>
                </div>


            </div><!-- /content -->


        </div><!-- /page -->

    </body>

    <script>
        function go()  {
            window.document.location.href="<%= request.getContextPath()%>/SEventos?accion=19&data=<%=idcalendar.getIdcalendario()%>&mydate1="+window.document.getElementById('mydate1').value
                +"&mydate2="+window.document.getElementById('mydate2').value;
                
        } 
    </script>

</html>
