<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>VIH - Editar Material</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde Edicion de Material de laboratorio</h1>
        <br>
        <a href="<c:url value="index.htm"/>">Regresar a Home</a>
        <br>
        <div style="text-align:center;">
            <form:form method="POST" commandName="material" >
                
                <p>
                    <form:label path="tipoFabricacion">Tipo de fabricacion:</form:label>
                    <form:input path="tipoFabricacion" />
                    <form:errors path="tipoFabricacion" element="div" cssClass="alert alert-danger"/>

                </p>
                <p>
                    <form:label path="uso">Uso:</form:label>
                    <form:input path="uso" />
                    <form:errors path="uso" element="div" cssClass="alert alert-danger"/>

                </p>
                <p>
                    <form:label path="formaPresentacion">Forma de Presentacion:</form:label>
                    <form:input path="formaPresentacion" />
                    <form:errors path="formaPresentacion" element="div" cssClass="alert alert-danger"/>

                </p>
                <hr/>
                <form:button class="btn btn-primary">Editar Material de laboratorio</form:button>
            </form:form>
            <h1><c:out value="${respuestaerror}"/></h1>
        </div>
    </body>
</html>
