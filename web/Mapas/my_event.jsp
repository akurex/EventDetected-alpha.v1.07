<%-- 
    Document   : my_event
    Created on : 16-may-2012, 11:21:59
    Author     : Christian
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.sw2.ed.bean.BEvento, com.sw2.ed.bean.BLugar, java.util.ArrayList, java.util.Date, java.util.Locale"%>
<jsp:useBean id="evento" type=" BEvento" scope="request" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sw2.ed.bean.BUsuario"%>

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
        <LINK REL="SHORTCUT ICON" HREF="<%= request.getContextPath()%>/imgs/map2.ico"> 
        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <script src="js/jquery.js"></script>
        <script src="js/jquery.mobile-1.1.0.min.js"></script>
        <style type="text/css"></style>
    </head>

    <body onload ="init()"  >

        <div data-role= "page">
            <div data-role="header" data-theme="f">
                <a href="<%= request.getContextPath()%>/Eventos/evento_info1.jsp" data-rel="back" data-role="button" data-icon="arrow-l" data-inline="true" data-mini="true">Regresar</a> <!-- /Debe regresar a la pantalla de login -->
                <h1>Mapa de Evento</h1>
                <a href="<%= request.getContextPath()%>/index.jsp" data-icon="home" data-inline="true" data-mini="true" data-transition="pop">Salir</a>


            </div><!-- /header -->
            <div id="map"></div>

            <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
                <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
            </div><!-- /content -->    

        </div>


        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
        <script type="text/javascript">

            
            google.maps.event.addDomListener(window, 'load', function() {
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 12,
                    center: new google.maps.LatLng(-12, -77),
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                });

            if(navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = new google.maps.LatLng(position.coords.latitude,
                                             position.coords.longitude);
             var marker0 = new google.maps.Marker({
                        map: map,
                        position: pos,
                        icon: "http://maps.google.com/mapfiles/arrow.png",
                        shadow: "http://maps.google.com/mapfiles/arrowshadow.png"
                    });                              
            

            map.setCenter(pos);
          }, function() {
            handleNoGeolocation(true);
          });
        } else {
          // Browser doesn't support Geolocation
          handleNoGeolocation(false);
        }
      

      function handleNoGeolocation(errorFlag) {
        if (errorFlag) {
          var content = 'Error: The Geolocation service failed.';
        } else {
          var content = 'Error: Your browser doesn\'t support geolocation.';
        }

        var options = {
          map: map,
          position: new google.maps.LatLng(60, 105),
          content: content
        };

        var infowindow = new google.maps.InfoWindow(options);
        map.setCenter(options.position);
      }

            

                    var infoWindow = new google.maps.InfoWindow;
                
                    var onMarkerClick = function() {
                        var marker = this;
                        var latLng = marker.getPosition();
                        infoWindow.setContent('<%=evento.getNombre()%>' +
                            '<br><a data-ajax="false" href=<%=request.getContextPath()%>/SEventos?data=<%=evento.getIdevento()%>&accion=3>¿Cómo llegar?</a>' +
                            '<br><a href=<%=request.getContextPath()%>/SEventos?event=<%=evento.getIdevento()%>&accion=5>Detalles</a>' );


                        infoWindow.open(map, marker);
                    };
                    google.maps.event.addListener(map, 'click', function() {
                        infoWindow.close();
                    });

                    var marker1 = new google.maps.Marker({
                        map: map,
                        position: new google.maps.LatLng('<%=evento.getIdlugar().getLatitud()%>','<%=evento.getIdlugar().getLongitud()%>')
                    });

                    google.maps.event.addListener(marker1, 'click', onMarkerClick);
           
                });
        </script>
        <style type="text/css">
            body {
                font-family: sans-serif;
            }
            #map {
                width: 500px;
                height: 500px;
            }
                            #map img { max-width: none; } 
        </style>

    </body>
</html>