<%-- 
    Document   : admin_edit
    Created on : 16-may-2012, 11:22:18
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
        <title>My Page</title> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/custom.css" />
        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <script src="js/jquery.js"></script>
        <script src="js/jquery.mobile-1.1.0.min.js"></script>
        
        
    </head> 
    <body>
        <h1>Soy el mapa</h1>
    </body>
</html>
