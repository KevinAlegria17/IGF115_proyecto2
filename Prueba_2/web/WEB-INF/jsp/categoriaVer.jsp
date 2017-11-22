<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Ver Categoria - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde Ver categoria</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
        <br/>
        <a href="<c:url value="/categorias.htm"/>">Regresar</a>
        <br/>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Codigo</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${categorias}" var="categoria">
                        <tr>
                            <th><c:out value="${categoria.id}"/></th>
                            <th><c:out value="${categoria.nombre}"/></th>
                            <th><c:out value="${categoria.codigo}"/></th>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
