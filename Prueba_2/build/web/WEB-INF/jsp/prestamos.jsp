<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Prestamos - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde consulta de prestamos</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
        <hr/>
        <a href="<c:url value="/addPrestamo.htm"/>">Añadir Prestamo</a>
        <div>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>ID Videojuego</th>
                        <th>ID Usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${prestamos}" var="prestamo">
                        <tr>
                            <th><c:out value="${prestamo.id}"/></th>
                            <th><c:out value="${prestamo.videojuego}"/></th>
                            <th><c:out value="${prestamo.usuario}"/></th>
                            <th><a href="<c:url value="/editPrestamo.htm?id=${prestamo.id}"/>">Editar</a></th>
                            <th><a href="">Eliminar</a></th>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
