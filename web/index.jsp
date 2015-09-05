
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
        
        <script type="text/javascript" src="js/jquery.validationEngine-en.js" charset="utf-8"></script>
        <script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
        <script type="text/javascript" src="js/pre_utopia_scripts.js"></script>  
        <link type="text/css" rel="stylesheet" href="css/validationEngine.jquery.css"/>	
    </head> 
    <body> 

        <div data-role="page">

            <P ALIGN="center"><IMG SRC="<%= request.getContextPath()%>/imgs/logo.png" WIDTH="250" BORDER="0"></p>
            <form id="frmPage1" action="<%= request.getContextPath()%>/SLogin" method="Post" name="PForm1">
                <div data-role="content">	

                    <ul data-role="listview" data-inset="true" data-divider-theme="f" >
                        <li data-role="list-divider" ><h1>Iniciar sesión:</h1></li>
                        <li data-role="fieldcontain" class="ui-hide-label">
                            <label for="name">Username...:</label>
                            <input type="text" required name="username" id="username" value="" placeholder="Username" class="validate[custom[onlyLetterNumber],length[0,6]]"/>
                        </li>
                        <li data-role="fieldcontain" class="ui-hide-label">
                            <label for="password">Password...:</label>
                            <input type="password" required name="password" id="password" value="" placeholder="Password"/>
                        </li>

                        <li data-role="fieldcontain" >
                            <button type="submit" data-theme="f" name="submit" value="submit-value">Ingresar</button>
                            <input type="hidden" name="accion" id="accion" value="1">
                            <h5><a data-ajax="false" href="<%= request.getContextPath()%>/Contrasenha/Recuperar.jsp">
                                    Recuperar Contraseña
                                </a>
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
                <a href="<%= request.getContextPath()%>/SAdmin?accion=2"  data-role="button"  data-inline="true" data-icon="back" data-theme="f">Ok</a>
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

        </div>    

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