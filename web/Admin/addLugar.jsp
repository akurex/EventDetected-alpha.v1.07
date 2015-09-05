<%-- 
    Document   : admin_edit
    Created on : 16-may-2012, 11:22:18
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sw2.ed.bean.BLugar, java.util.ArrayList, com.sw2.ed.bean.BUsuario"%>
<jsp:useBean id="listalugares" type=" ArrayList<BLugar>" scope="request" />
<jsp:useBean id="nombre" type="String" scope="request" />
<jsp:useBean id="descripcion" type="String" scope="request" />
<!DOCTYPE html>
<%

HttpSession session1 = request.getSession(true);
BUsuario usuario = (BUsuario) session1.getAttribute("administrador");

if(usuario.getNombre() == null){

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
         <script type="text/javascript">
            $(document).bind("mobileinit", function() {
                $.mobile.ajaxEnabled = false;
            })
        </script>  
        <script src="js/jquery.mobile-1.1.0.min.js"></script>
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
        <style type="text/css">
            body {font-family: sans-serif;} #map {width: 420px; height: 475px;} #map img { max-width: none; }  
        </style>
        
        <script type="text/javascript">
        var geocoder;
        var map;
        var infowindow2;
        function initialize() {
            geocoder = new google.maps.Geocoder();
            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 12,
                center: new google.maps.LatLng(-12, -77),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            });



        <% for (int i = 0; i < listalugares.size(); i++) {%>

                var infoWindow = new google.maps.InfoWindow;
                
                var onMarkerClick = function() {
                    var marker = this;
                    var latLng = marker.getPosition();
                    infoWindow.setContent('<%=listalugares.get(i).getNombre()%>'+
                        '<br><a data-ajax="false" href=<%=request.getContextPath()%>/SAdmin?data=<%=listalugares.get(i).getIdlugar()%>&accion=15&nombre=<%=nombre%>&descripcion=<%=descripcion%>>Seleccionar</a>'
                );


                    infoWindow.open(map, marker);
                };
                google.maps.event.addListener(map, 'click', function() {
                    infoWindow.close();
                });

                var marker1 = new google.maps.Marker({
                    map: map,
                    position: new google.maps.LatLng('<%=listalugares.get(i).getLatitud()%>','<%=listalugares.get(i).getLongitud()%>'),
                    icon: "http://labs.google.com/ridefinder/images/mm_20_yellow.png",
                    shadow: "http://labs.google.com/ridefinder/images/mm_20_shadow.png",
                    title: '<%=listalugares.get(i).getNombre()%>'
                });

                google.maps.event.addListener(marker1, 'click', onMarkerClick);
        <% }%>
            
            }  
            function codeAddress() {
                var address = document.getElementById("address").value;
                geocoder.geocode( { 'address': address}, function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        map.setCenter(results[0].geometry.location);
                        var marker2 = new google.maps.Marker({
                            map: map, 
                            position: results[0].geometry.location,
                            icon: "http://labs.google.com/ridefinder/images/mm_20_blue.png",
                            shadow: "http://labs.google.com/ridefinder/images/mm_20_shadow.png"
                        });
                    } else {
                        alert("Geocode was not successful for the following reason: " + status);
                    }
                });
            }
                
                
            function findAddress(event) {
                geocoder = new google.maps.Geocoder();
                geocoder.geocode({latLng: event.latLng}, function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        if (results[0]) {
                            document.getElementById("direccion").value = results[0].formatted_address;
                        }
                    }
                });
            }
                    
            function nuevo() {
           
                var marker = new google.maps.Marker({
                    position: map.getCenter(), 
                    map: map,
                    draggable:true,
                    title: "Nuevo Lugar"
                });
             
                geocoder = new google.maps.Geocoder();
                geocoder.geocode({latLng: marker.getPosition()}, function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        if (results[0]) {
                            document.getElementById("direccion").value = results[0].formatted_address;
                        }
                    }
                });
                document.getElementById('latitud').value = marker.position.lat();
                document.getElementById('longitud').value = marker.position.lng();
                 
                google.maps.event.addListener(
                marker,
                'dragend',
                function(event) {
                    findAddress(event);
                    document.getElementById('latitud').value = marker.position.lat();
                    document.getElementById('longitud').value = marker.position.lng();
                }
            );
            }      
             
    </script>
        


    </head>
    <body onload="initialize()">
        <div data-role= "page">
            <div data-role="header" data-theme="f">
                <a href="" data-rel="back" data-icon="back" data-role="button" data-inline="true">Regresar</a>               
                <h1>Event Detected!</h1>
                <a href="<%= request.getContextPath()%>/SLogin?accion=4" data-icon="home" data-inline="true" data-mini="true" >Salir</a>
            </div>
            <div class="ui-grid-a">
                <div class="ui-block-a" id="map"></div>

                <div class="ui-block-b">		
                    <div>
                        <input id="address" type="textbox">
                        <input value="Buscar" onclick="codeAddress()" type="button">
                    </div>


                    <div data-role="collapsible" data-collapsed="true">
                        <h6>Nuevo Lugar</h6>
                        <button id="drop" onclick="nuevo()">Nuevo Marcador</button>
                        <form action="<%=request.getContextPath()%>/SAdmin" method="Post">
                            <table style="width: 450px">

                                <tr>
                                    <td><label for="nombrex">Nombre: </label>
                                        <input type="text" name="nombrex" id="nombrex" /></td>
                                </tr>
                                <tr>
                                    <td><label for="nombre">Dirección: </label>
                                        <input type="text" name="direccion" id="direccion" /></td>
                                </tr>
                                <tr>
                                    <td><button type="submit" data-theme="f" name="submit" value="submit-value" >Guardar</button>    
                                        <input type="hidden" name="nombrex" id="nombrex"  />
                                        <input type="hidden" name="latitud" id="latitud"  />
                                        <input type="hidden" name="longitud" id="longitud"  />
                                        <input type="hidden" name="direccion" id="direccion"  />
                                        <input type="hidden" name="nombre" id="nombre" value="<%=nombre%>" />
                                        <input type="hidden" name="descripcion" id="descripcion" value="<%=descripcion%>" />
                                       
                                        <input type="hidden" name="accion" id="accion" value="14" /></td>
                                </tr>

                                <tr>
                            </table>  </form>
                    </div>


                </div>

            </div>

            <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
                <p>&copy; Proyecto - Software para Telecomunicaciones 2 - Event Detected !</p>                
            </div><!-- /content -->    


        </div>

        <%--- AGREGAR EN EL OTRO LADO  ---%>
    

    
</body>    
</html>
