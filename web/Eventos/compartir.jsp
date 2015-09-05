<%-- 
    Document   : compartir
    Created on : 26-may-2012, 12:35:24
    Author     : Denise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sw2.ed.bean.BEvento, com.sw2.ed.bean.BUsuario"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- AddThis Button BEGIN -->
        <div class="addthis_toolbox addthis_default_style addthis_32x32_style">
            <a class="addthis_button_preferred_1"></a>
            <a class="addthis_button_preferred_2"></a>
            <a class="addthis_button_preferred_4"></a>
            <a class="addthis_button_preferred_15"></a>
            <a class="addthis_button_preferred_7"></a>

            <a class="addthis_counter addthis_bubble_style"></a>
        </div>
        <script type="text/javascript">var addthis_config = {"data_track_addressbar":false};</script>
        <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-4fbf092973075f98"></script>
        <!-- AddThis Button END --> 
    </body>
</html>
