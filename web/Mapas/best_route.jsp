<%-- 
    Document   : best_route
    Created on : 17-may-2012, 14:00:27
    Author     : Christian
--%>

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
<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
<style type="text/css">
  html { height: 100% }
  body { height: 100%; margin: 0px; padding: 0px }
  #map_canvas { height: 100% }
</style>

<title>Event Detected</title>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
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
    
    google.maps.event.addListener(map, 'click', function(event) {
      if (origin == null) {
        origin = event.latLng;
        addMarker(origin);
      } else if (destination == null) {
        destination = event.latLng;
        addMarker(destination);
      } else {
        if (waypoints.length < 9) {
          waypoints.push({ location: destination, stopover: true });
          destination = event.latLng;
          addMarker(destination);
        } else {
          alert("Maximum number of waypoints reached");
        }
      }
    });
    
    origin = new google.maps.LatLng(-12.07,-77.08)
    addMarker(origin);
    destination = new google.maps.LatLng(-12.06962,-77.05733)
    addMarker(destination);
    calcRoute();
  }

  function addMarker(latlng) {
    markers.push(new google.maps.Marker({
      position: latlng, 
      map: map,
      icon: "http://maps.google.com/mapfiles/marker" + String.fromCharCode(markers.length + 65) + ".png"
    }));    
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
      case "bicycling":
        mode = google.maps.DirectionsTravelMode.BICYCLING;
        break;
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
        travelMode: mode,
        optimizeWaypoints: document.getElementById('optimize').checked,
        avoidHighways: document.getElementById('highways').checked,
        avoidTolls: document.getElementById('tolls').checked
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
  <table style="width: 400px">
    <tr>
      <td><input type="checkbox" id="optimize" checked />Optimizar ruta</td>
      <td>
        <select id="mode" onchange="updateMode()">
          <option value="driving">Carro</option>
          <option value="walking">Caminar</option>
          <option value="bicycling">Bicicleta</option>

        </select>
      </td>
    </tr>
    <tr>
      <td><input type="checkbox" id="highways" checked />Evitar carretera</td>
      <td><input type="button" value="Reset" onclick="reset()" /></td>
    </tr>
    <tr>
      <td><input type="checkbox" id="tolls" checked />Evitar peaje</td>
      <td><input type="button" value="Get Directions" onclick="calcRoute()" /></td>
      <td></td>
    </tr>
  </table>
<div data-role="content" style="padding:0;">

    <div id="map_canvas" style="border: 1px solid black; position:relative; width:398px; height:398px"></div>
    
  </div>
</body>
</html>

