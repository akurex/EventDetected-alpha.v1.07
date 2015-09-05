<%-- 
    Document   : search
    Created on : 24-may-2012, 22:33:44
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

        <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
        <title>Google Maps Multiple Markers</title> 
        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <script src="http://maps.google.com/maps/api/js?sensor=false" 
        type="text/javascript"></script>
        <script src="js/Tooltip.v2.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <script src="js/jquery.js"></script>
        <script src="js/jquery.mobile-1.1.0.min.js"></script> 
    </head> 
    <body>
        <div id="map" style="width: 500px; height: 400px;"></div>

        <script type="text/javascript">
            var locations = [
                ['Bondi Beach', -12.09421, -77.07141, '../SEventos?accion=2'],
                ['Coogee Beach', -12.05879, -77.08394, '../SEventos?accion=2'],
                ['Cronulla Beach', -12.06744, -77.08866, '../SEventos?accion=2'],
                ['Manly Beach', -12.06744, -77.06128, '../SEventos?accion=2'],
                ['Maroubra Beach', -12.07021, -77.07999, '../SEventos?accion=1']
            ];

            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 12,
                center: new google.maps.LatLng(-12.06450, -77.06583),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            });

            var infowindow = new google.maps.InfoWindow();

            var marker, i;

            for (i = 0; i < locations.length; i++) {  
                marker = new google.maps.Marker({
                    position: new google.maps.LatLng(locations[i][1], locations[i][2]),
                    url: locations[i][3],
                    map: map
                });
 
                google.maps.event.addListener(marker, 'click', function() {
                    window.parent.location.href = marker.url;  
                });


            }
    
            var marker2 = new google.maps.Marker(new google.maps.LatLng(-33.88274,151.26740));
            var tooltip = new Tooltip(marker2,'Funciona',4);
            marker2.tooltip = tooltip;
            map.addOverlay(marker2);
            map.addOverlay(tooltip);
            google.maps.Event.addListener(marker2,'mouseover',function(){
                marker2.tooltip.show();
            });
            google.maps.Event.addListener(marker2,'mouseout',function(){
                marker2.tooltip.hide();
            });
        </script>
    </body>
</html>




