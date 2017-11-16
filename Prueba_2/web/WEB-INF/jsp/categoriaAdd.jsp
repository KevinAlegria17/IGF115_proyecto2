<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Añadir Categoria - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde añadir categoria</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
        <div style="text-align:center;">
        <form:form method="post" commandName="categoria" >
            <p>
                <form:label path="nombre">Nombre de la categoria</form:label>
                <form:input path="nombre" />
                <form:errors path="nombre" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="codigo">Codigo o Abreviatura de la categoria</form:label>
                <form:input path="codigo" maxlength="5" />
                <form:errors path="codigo" element="div" cssClass="alert alert-danger"/>
            </p>
            <hr/>
            <form:button class="btn btn-primary">Añadir Categoria</form:button>
        </form:form>
        </div>
    </body>
</html>
