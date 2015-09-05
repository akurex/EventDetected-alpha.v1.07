

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sw2.ed.bean.BUsuario"%>
<!DOCTYPE html>

<html> 
    <head> 
        <LINK REL="SHORTCUT ICON" HREF="/~your_directory/logo.ico">
        <title>Event Detected</title> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/jquery.mobile-1.1.0.css" />
        <script src="js/jquery.js"></script>
        <script src="js/jquery.mobile-1.1.0.min.js"></script>
    </head> 
    <body> 

        <div data-role="page">

            <P ALIGN="center"><IMG SRC="http://i.imgur.com/a1jFJ.png" WIDTH="250" BORDER="0"></p>
            <form action="<%= request.getContextPath()%>/SLogin" method="Post">
                <div data-role="content">	

                    <label for="name"> <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">

                        El usuario y/o la contraseña no son válidos.Se diferencian mayúsculas de minúsculas y es posible que su teclado esté bloqueado para MAYÚSCULAS.</font>
                    </label> 
                    <ul data-role="listview" data-inset="true" data-divider-theme="f" >
                        <li data-role="list-divider" ><h1>Loguéate...!</h1></li>
                        <li data-role="fieldcontain" class="ui-hide-label">
                            <label for="name">Username...:</label>
                            <input type="text" name="username" id="username" value="" placeholder="Username"/>
                        </li>
                        <li data-role="fieldcontain" class="ui-hide-label">
                            <label for="password">Password...:</label>
                            <input type="password" name="password" id="password" value="" placeholder="Password"/>
                        </li>

                        <li data-role="fieldcontain" >
                            <button type="submit" data-theme="f" name="submit" value="submit-value">Ingresar</button>
                            <input type="hidden" name="accion" id="accion" value="1">
                            <h5><a href="<%= request.getContextPath()%>/Contrasenha/Recuperar.jsp" data-transition="fade"data-rel="dialog">
                                    Recuperar Contraseña
                                </a></h5>

                        </li>      
                    </ul>

                </div><!-- /content -->
            </form>
            <div data-role="footer" data-inset="true" class="footer-docs" data-theme="c" >	
                <p>&copy; Proyecto - Software para Telecomunicaciones 2</p>             

            </div><!-- /content -->

        </div><!-- /page -->

        <div data-role="page" id="two" data-theme="f" >

            <div data-role="header" data-theme="f">
                <h1>!Bievenido!</h1>
            </div><!-- /header -->

            <div data-role="content" data-theme="c">	

                <ul data-role="listview" data-inset="true" data-divider-theme="f" >
                    <li data-role="list-divider" ><h1>Ingrese su contraseña:</h1></li>                    
                    <li data-role="fieldcontain" class="ui-hide-label">
                        <label for="password">Password...:</label>
                        <input type="password" name="password" id="password" value="" placeholder="Password"/>
                    </li>
                    <li data-role="fieldcontain" >
                        <h5><a href="" data-role="button" data-theme="f">Ingresar</a></h5>                        
                </ul>
            </div><!-- /content -->

        </div><!-- /page popup --> 
        <div data-role="page" id="popupBasic" data-theme="f">

            <div data-role="header" >
                <h1>Event Detected</h1>
            </div><!-- /header -->

            <div data-role="content">	                
                <p>En breve le llegará a su correo una dirección web para recuperar su contraseña.</p>		
                <a href="<%= request.getContextPath()%>/index.jsp" data-role="button" data-inline="true" data-icon="back">Ok</a>
            </div>

        </div><!-- /page popup --> 

        <div data-role="page" id="ok" data-theme="f">

            <div data-role="header" data-theme="f">
                <h1>Event Detected !</h1>
            </div><!-- /header -->

            <div data-role="content" data-theme="c">	                
                <p>¡Evento agregado satisfactoriamente!</p>		
                <a href="#one" data-rel="back" data-role="button" data-inline="true" data-icon="back" data-theme="f">Ok</a>
            </div><!-- /content -->

        </div><!-- /page popup --> 

        <div data-role="page" id="borrarusuario" data-theme="f">

            <div data-role="header" data-theme="f">
                <h1>Event Detected !</h1>
            </div><!-- /header -->

            <div data-role="content" data-theme="c">	                
                <p>¡Usuario Bloqueado satisfactoriamente!</p>		
                <a href="<%= request.getContextPath()%>/Admin/adminUser.jsp"  data-role="button" data-inline="true" data-icon="back" data-theme="f">Ok</a>
            </div><!-- /content -->

        </div><!-- /page popup --> 

        <div data-role="page" id="borrarevento" data-theme="f">

            <div data-role="header" data-theme="f">
                <h1>Event Detected !</h1>
            </div><!-- /header -->

            <div data-role="content" data-theme="c">	                
                <p>¡Evento borrado satisfactoriamente!</p>		
                <a href="<%= request.getContextPath()%>/Admin/adminEvents.jsp"  data-role="button" data-inline="true" data-icon="back" data-theme="f">Ok</a>
            </div><!-- /content -->

        </div><!-- /page popup --> 

        <div data-role="page" id="agregarevento" data-theme="f">

            <div data-role="header" data-theme="f">
                <h1>Event Detected !</h1>
            </div><!-- /header -->

            <div data-role="content" data-theme="c">	                
                <p>¡Evento agregado satisfactoriamente!</p>		
                <a href="<%= request.getContextPath()%>/Admin/adminEvents.jsp"  data-role="button" data-inline="true" data-icon="back" data-theme="f">Ok</a>
            </div><!-- /content -->

        </div><!-- /page popup --> 

        <div data-role="page" id="guardarevento" data-theme="f">

            <div data-role="header" data-theme="f">
                <h1>Event Detected !</h1>
            </div><!-- /header -->

            <div data-role="content" data-theme="c">	                
                <p>¡Evento guardado satisfactoriamente!</p>		
                <a href="<%= request.getContextPath()%>/Admin/adminEvents.jsp"  data-role="button" data-inline="true" data-icon="back" data-theme="f">Ok</a>
            </div><!-- /content -->


            <div data-role="page" id="agregaruser" data-theme="f">

                <div data-role="header" data-theme="f">
                    <h1>Event Detected !</h1>
                </div><!-- /header -->

                <div data-role="content" data-theme="c">	                
                    <p>¡Usuario agregado satisfactoriamente!</p>		
                    <a href="<%= request.getContextPath()%>/Admin/adminUser.jsp"  data-role="button" data-inline="true" data-icon="back" data-theme="f">Ok</a>
                </div><!-- /content -->

            </div><!-- /page popup -->

    </body>
</html>