<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Consolas - Prueba_2</title>
    </head>
    <body>
        <h1>Hola desde consulta de consolas</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
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
                    <c:forEach items="${datos}" var="dato">
                        <tr>
                            <th><c:out value="${dato.id_con}"/></th>
                            <th><c:out value="${dato.nombre_con}"/></th>
                            <th><c:out value="${dato.codigo_con}"/></th>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
