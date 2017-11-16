<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Añadir Prestamo - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde añadir prestamo</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
        <div style="text-align:center;">
        <form:form method="post" commandName="prestamo" >
            
            <p>
                <form:label path="videojuego">Videojuego</form:label>
                <form:select path="videojuego">
                    <form:option value="0">Seleccione... </form:option>
                    <c:forEach items="${vjs}" var="vj">
                    <form:option value="${vj.id}"><c:out value="${vj.nombre}"/></form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="videojuego" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="usuario">Usuario</form:label>
                <form:select path="usuario">
                    <form:option value="0">Seleccione... </form:option>
                    <c:forEach items="${users}" var="user">
                        
                        <form:option value="${user.id}"><c:out value="${user.nombre}"/></form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="usuario" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <hr/>
            <form:button class="btn btn-primary">Añadir prestamo</form:button>
        </form:form>
        </div>
    </body>
</html>
