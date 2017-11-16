<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Videojuegos - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde consulta de videojuegos</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
        <hr/>
        <a href="<c:url value="/VJadd.htm"/>">Añadir Videojuego</a>
        <div>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Categoria</th>
                        <th>Consola</th>
                        <th>Nombre</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datos}" var="dato">
                        <tr>
                            <th><c:out value="${dato.id}"/></th>
                            <th><c:out value="${dato.categoria}"/></th>
                            <th><c:out value="${dato.consola}"/></th>
                            <th><c:out value="${dato.nombre}"/></th>
                            <th><a href="<c:url value="/VJedit.htm?id=${dato.id}"/>">Editar</a></th>
                            <th><a href="">Eliminar</a></th>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
