<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Usuarios - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde consulta de usuarios</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
        <hr/>
        <a href="<c:url value="/addUser.htm"/>">Añadir Usuarios</a>
        <div>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Ha realizado Prestamo?</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datos}" var="dato">
                        <tr>
                            <th><c:out value="${dato.id}"/></th>
                            <th><c:out value="${dato.nombre}"/></th>
                            <th><c:out value="${dato.prestamo}"/></th>
                            <th><a href="<c:url value="/editUser.htm?id=${dato.id}"/>">Editar</a></th>
                            <th><a href="">Eliminar</a></th>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
