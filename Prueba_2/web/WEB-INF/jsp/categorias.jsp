<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Categorias - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde consulta de categorias</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
        <br/>
        <a href="<c:url value="categoriaAdd.htm"/>" class="btn btn-success">Añadir Categoria</a>
                            
        <div>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Codigo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datos}" var="dato">
                        <tr>
                            <th><c:out value="${dato.id}"/></th>
                            <th><c:out value="${dato.nombre}"/></th>
                            <th><c:out value="${dato.codigo}"/></th>
                            <th><a href="<c:url value="deleteCat.htm?id=${dato.id}"/>" class="btn btn-danger">Eliminar</a></th>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
