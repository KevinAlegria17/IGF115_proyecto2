<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Monitoreo VIH - Home</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde Home</h1>
        <a href="">Regresar a Home</a>
        <div style="text-align:center;">
            <a href="<c:url value="MaterialLaboratorioAdd.htm"/>">Ingresar material de laboratorio</a>
            </br>
            <hr/>
            </br>
            <a href="<c:url value="MaterialTarAdd.htm"/>">Ingresar material de laboratorio</a>
        
        </div>
    </body>
</html>
