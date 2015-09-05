<%-- 
    Document   : ver_ruta
    Created on : 23-may-2012, 12:33:51
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sw2.ed.bean.BEvento"%>
<jsp:useBean id="evento" type="BEvento" scope="request"/>
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




        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
        <script type="text/javascript">
            var directionDisplay;
            var directionsService = new google.maps.DirectionsService();
            var map;
            var origin = null;
            var destination = null;
            var waypoints = [];
            var markers = [];
            var directionsVisible = false;

            function initialize() {
                directionsDisplay = new google.maps.DirectionsRenderer();
                var lima = new google.maps.LatLng(-12.07113, -77.06497);
                var myOptions = {
                    zoom:13,
                    mapTypeId: google.maps.MapTypeId.ROADMAP,
                    center: lima
                }
                map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
                directionsDisplay.setMap(map);
                directionsDisplay.setPanel(document.getElementById("directionsPanel"));
            
                if (navigator.geolocation) { 
                    navigator.geolocation.getCurrentPosition(function(position) {  
                        origin = new google.maps.LatLng(position.coords.latitude, 
                        position.coords.longitude);
                        
                        var lima = new google.maps.LatLng(-12.07113, -77.06497);
                        var myOptions = {
                            zoom:13,
                            mapTypeId: google.maps.MapTypeId.ROADMAP,
                            center: lima
                        }
                        map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
                        directionsDisplay.setMap(map);
                        directionsDisplay.setPanel(document.getElementById("directionsPanel"));
    

    
                        if(navigator.geolocation) {
                            navigator.geolocation.getCurrentPosition(function(position) {
                                pos = new google.maps.LatLng(position.coords.latitude,
                                position.coords.longitude);

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


                        destination = new google.maps.LatLng('<%=evento.getIdlugar().getLatitud()%>','<%=evento.getIdlugar().getLongitud()%>')

                        calcRoute();
                    });
                } 
                else {
                    alert('W3C Geolocation API is not available');
                } 
            }

            function calcRoute() {
                if (origin == null) {
                    alert("Click on the map to add a start point");
                    return;
                }
    
                if (destination == null) {
                    alert("Click on the map to add an end point");
                    return;
                }
    
                var mode;
                switch (document.getElementById("mode").value) {
                    //  case "bicycling":
                    //     mode = google.maps.DirectionsTravelMode.BICYCLING;
                    //     break;
                    case "driving":
                        mode = google.maps.DirectionsTravelMode.DRIVING;
                        break;
                    case "walking":
                        mode = google.maps.DirectionsTravelMode.WALKING;
                        break;
                }
    
                var request = {
                    origin: origin,
                    destination: destination,
                    waypoints: waypoints,
                    travelMode: mode

                };
    
                directionsService.route(request, function(response, status) {
                    if (status == google.maps.DirectionsStatus.OK) {
                        directionsDisplay.setDirections(response);
                    }
                });
    
                clearMarkers();
                directionsVisible = true;
            }
  
            function updateMode() {
                if (directionsVisible) {
                    calcRoute();
                }
            }
  
            function clearMarkers() {
                for (var i = 0; i < markers.length; i++) {
                    markers[i].setMap(null);
                }
            }
  
            function clearWaypoints() {
                markers = [];
                origin = null;
                destination = null;
                waypoints = [];
                directionsVisible = false;
            }
  
            function reset() {
                clearMarkers();
                clearWaypoints();
                directionsDisplay.setMap(null);
                directionsDisplay.setPanel(null);
                directionsDisplay = new google.maps.DirectionsRenderer();
                directionsDisplay.setMap(map);
                directionsDisplay.setPanel(document.getElementById("directionsPanel"));    
            }
        </script>
    </head>

    <body onload="initialize()" style="font-family: sans-serif;">

        <div data-role="header" data-theme="f">
            <a href="" data-rel="back" data-icon="back" data-role="button" data-inline="true">Regresar</a>               
            <h1>Event Detected!</h1>
            <a href="<%= request.getContextPath()%>/index.jsp" data-icon="home" data-inline="true" data-mini="true" >Salir</a>
        </div>

        

       <table style="width: 400px">
        <tr>
           
            <td>
                <select id="mode" onchange="updateMode()">
                    <option value="driving">Carro</option>
                    <option value="walking">Caminar</option>
           <%--         <option value="bicycling">Bicicleta</option>
            --%>

                </select>
            </td>
        </tr>
    </table>
    <div data-role="content" style="padding:0;">

        <div id="map_canvas" style="border: 1px solid black; position:relative; width:398px; height:398px"></div>

    </div>

    <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
        <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
    </div><!-- /content -->    
    <style type="text/css">
            body {
                font-family: sans-serif;
            }
            #map_canvas {
                width: 500px;
                height: 500px;
            }
                            #map_canvas img { max-width: none; } 
        </style>
</body>
</html>
