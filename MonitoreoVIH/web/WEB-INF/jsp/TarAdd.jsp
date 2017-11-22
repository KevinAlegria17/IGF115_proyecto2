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
        <h1>Hola desde Añadir Material TAR</h1>
        <a href="">Regresar a Home</a>
        <div style="text-align:center;">
        <form:form method="post" commandName="material-tar" >
            <p>
                <form:label path="id">ID:</form:label>
                <form:input path="id" />
                <form:errors path="id" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="idLineaNivel">Linea de Nivel:</form:label>
                <form:select path="idLineaNivel">
                    <form:option value="0">Seleccione... </form:option>
                    <c:forEach items="${lineas}" var="linea">
                    <form:option value="${linea.ID_LINEA}"><c:out value="${linea.NOMBRE_LINEA}"/></form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="idLineaNivel" element="div" cssClass="alert alert-danger"/>
            
            </p>
            
            <p>
                <form:label path="nombreComercial">Nombre Comercial:</form:label>
                <form:input path="nombreComercial" />
                <form:errors path="nombreComercial" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="accionFarmacologica">Accion Farmacologica:</form:label>
                <form:input path="accionFarmacologica" />
                <form:errors path="accionFarmacologica" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <hr/>
            <form:button class="btn btn-primary">Añadir Material TAR</form:button>
        </form:form>
        </div>
    </body>
</html>
