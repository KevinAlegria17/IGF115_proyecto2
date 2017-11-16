<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Añadir Usuario - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde añadir usuario</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
        <div style="text-align:center;">
        <form:form method="post" commandName="usuario" >
            <p>
                <form:label path="nombre">Nombre</form:label>
                <form:input path="nombre" />
                <form:errors path="nombre" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="prestamo">Prestamo</form:label>
                <form:input path="prestamo" value="0" disabled="true" />
                <form:errors path="prestamo" element="div" cssClass="alert alert-danger"/>
            </p>
            <hr/>
            <form:button class="btn btn-primary">Añadir Usuario</form:button>
        </form:form>
        </div>
    </body>
</html>
