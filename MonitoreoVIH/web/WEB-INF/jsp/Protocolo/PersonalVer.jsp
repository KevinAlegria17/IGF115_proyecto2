<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>VIH - Materiales de laboratorio</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde Ver Personal de area</h1>
        <a href="<c:url value="index.htm"/>">Regresar a Home</a>
        <div>
            <table class="table table-bordered  table-condensed">
                <thead>
                    <tr>
                        <th>ID de personal de area</th>
                        <th>Area</th>
                        <th>Cantidad de personal disponible</th>
                        <th>Porcentaje de rotacion del personal</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th><c:out value="${respuesta[0]}"/></th>
                        <th><c:out value="${respuesta[1]}"/></th>
                        <th><c:out value="${respuesta[2]}"/></th>
                        <th><c:out value="${respuesta[3]}"/></th>
                    </tr>
                </tbody>
            </table>
            <a href="<c:url value="/Personals.htm"/>" class="btn btn-success">Regresar</a>
        </div>
        <h1><c:out value="${respuestaerror}"/></h1>
    </body>
</html>
