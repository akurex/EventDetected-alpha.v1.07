<%-- 
    Document   : Prueba1
    Created on : 06-jun-2012, 18:36:05
    Author     : kristiam
--%>

<%@page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Event Detected</title> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../css/jquery.mobile-1.1.0.css" />
        <script src="../js/jquery.js"></script>
        <script type="text/javascript">
            $(document).bind("mobileinit", function() {
                $.mobile.ajaxEnabled = false;
            })
        </script>  
        <script src="../js/jquery.mobile-1.1.0.js"></script>

        <script type="text/javascript" src="../js/jquery.validationEngine-en.js" charset="utf-8"></script>
        <script type="text/javascript" src="../js/jquery.validationEngine.js" charset="utf-8"></script>
        <script type="text/javascript" src="../js/pre_utopia_scripts.js"></script>  
        <link type="text/css" rel="stylesheet" href="../css/validationEngine.jquery.css"/>	

    </head>
    <body>
        <div data-role="page">
            <div data-role="header" data-theme="f">

                <h5>Recuperar Contraseña</h5>

            </div><!-- /header -->
            <div style="display: inline" data-theme="f">
                <P ALIGN="center"><img style="width: 288px; height: 80px; align: center" src="<%= request.getContextPath()%>/imgs/w7-banner-1.jpg" /></p>
            </div>
            <form id="frmPage1" action="<%= request.getContextPath()%>/SLogin" method="post" name="PForm1">
                <div data-role="content"> 
                    <div data-role="fieldcontain">
                        <fieldset data-role="controlgroup" data-mini="true">
                            <label for="textinput1">
                                Ingrese su correo electrónico:
                            </label>
                            <input type="text" name="textinput1" id="textinput1" placeholder="" value="" class="validate[custom[email],length[0,8]]" />
                                   </fieldset>
                                   <%--
                                       ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LfPadISAAAAAEuIHYwhsGSALDVc93-IINgaol3Z", "6LfPadISAAAAAP3IG_V8Y0CU2FXwtKWN6P1am6vl", false);
                                       out.print(c.createRecaptchaHtml(null, null));
                                   --%>
                                   <button type="submit" data-theme="f" name="submit" value="submit-value">Enviar</button>
                            <input type="hidden" name="accion" id="accion" value="2">
                            </div>
                            </div>
                            </form>
                            </div>
                            </body>
                            </html>
