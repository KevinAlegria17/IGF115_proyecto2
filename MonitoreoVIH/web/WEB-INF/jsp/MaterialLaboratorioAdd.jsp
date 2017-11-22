<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>VIH - Materiales</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde Añadir Material</h1>
        <a href="">Regresar a Home</a>
        <div style="text-align:center;">
        <form:form method="post" commandName="existencia" >
            <p>
                <form:label path="id">ID:</form:label>
                <form:input path="id" />
                <form:errors path="id" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="codigo">Codigo:</form:label>
                <form:input path="codigo" />
                <form:errors path="codigo" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="fechaConteo">Fecha Conteo</form:label>
                <form:input path="fechaConteo" />
                <form:errors path="fechaConteo" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="cantidadExistencia">Cantidad en exitencia:</form:label>
                <form:input path="cantidadExistencia" />
                <form:errors path="cantidadExistencia" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="cantidadFaltante">Cantidad faltante:</form:label>
                <form:input path="cantidadFaltante" />
                <form:errors path="cantidadFaltante" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="observacion">Observaciones:</form:label>
                <form:input path="observacion" />
                <form:errors path="observacion" element="div" cssClass="alert alert-danger"/>
            </p>
            <hr/>
            <form:button class="btn btn-primary">Siguiente</form:button>
        </form:form>
        </div>
    </body>
</html>
