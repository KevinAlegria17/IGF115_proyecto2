<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>VIH - Añadir Personal</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde Añadir Personal de area</h1>
        <br>
        <a href="<c:url value="index.htm"/>">Regresar a Home</a>
        <br>
        <div style="text-align:center;">
            <form:form method="POST" commandName="personal" >

                <p>
                    <form:label path="id">ID de personal</form:label>
                    <form:input path="id" />
                    <form:errors path="id" element="div" cssClass="alert alert-danger"/>

                </p>
                <p>
                    <form:label path="area">Area de personal:</form:label>
                    <form:input path="area" />
                    <form:errors path="area" element="div" cssClass="alert alert-danger"/>

                </p>
                <p>
                    <form:label path="cantidad_personal_disponible">Cantidad de personal disponible:</form:label>
                    <form:input path="cantidad_personal_disponible" />
                    <form:errors path="cantidad_personal_disponible" element="div" cssClass="alert alert-danger"/>

                </p>
                <p>
                    <form:label path="porcentaje">Porcentaje de rotacion:</form:label>
                    <form:input path="porcentaje" />
                    <form:errors path="porcentaje" element="div" cssClass="alert alert-danger"/>

                </p>
                <hr/>
                <form:button class="btn btn-primary">Añadir Personal de area</form:button>
            </form:form>
            <h1><c:out value="${respuesta}"/></h1>
        </div>
    </body>
</html>
