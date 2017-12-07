<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>VIH - Materiale TAR</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde Ver material TAR</h1>
        <a href="<c:url value="index.htm"/>">Regresar a Home</a>
        <div>
            <table class="table table-bordered  table-condensed">
                <thead>
                    <tr>
                        <th>ID de TAR</th>
                        <th>Nombre Comercial</th>
                        <th>Accion farmacologica</th>
                        <th>ID de Existencia</th>
                        <th>ID de Linea</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th><c:out value="${respuesta[2]}"/></th>
                        <th><c:out value="${respuesta[0]}"/></th>
                        <th><c:out value="${respuesta[1]}"/></th>
                        <th><c:out value="${respuesta[3]}"/></th>
                        <th><c:out value="${respuesta[4]}"/></th>
                    </tr>
                </tbody>
            </table>
            <a href="<c:url value="/Tars.htm"/>" class="btn btn-success">Regresar</a>
        </div>
        <h1><c:out value="${respuestaerror}"/></h1>
    </body>
</html>
