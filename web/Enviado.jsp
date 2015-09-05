<%-- 
    Document   : Enviado
    Created on : 09-jun-2012, 9:59:15
    Author     : kristiam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
         <div data-role="dialog" id="popupBasic" data-theme="f">

            <div data-role="header" data-theme="f" >

                <h1>Event Detected</h1>
            </div><!-- /header -->

            <div data-role="content" data-theme="c">	                
                <p>En breve le llegará a su correo una dirección web para recuperar su contraseña.</p>		
                <a href="<%= request.getContextPath()%>/index.jsp" data-role="button" data-theme="f" data-inline="true" data-icon="back">Ok</a>
            </div>

        </div><!-- /page popup --> 
    </body>
</html>
