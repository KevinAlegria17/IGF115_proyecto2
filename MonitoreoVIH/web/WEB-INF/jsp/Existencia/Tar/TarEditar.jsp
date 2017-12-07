<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>VIH - Editar TAR</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde Edicion de Material TAR</h1>
        <br>
        <a href="<c:url value="index.htm"/>">Regresar a Home</a>
        <br>
        <div style="text-align:center;">
            <form:form method="POST" commandName="tar" >
                
                <p>
                    <form:label path="nombreComercial">Nombre comercial:</form:label>
                    <form:input path="nombreComercial" />
                    <form:errors path="nombreComercial" element="div" cssClass="alert alert-danger"/>

                </p>
                <p>
                    <form:label path="accionFarmacologica">Accion farmacologica:</form:label>
                    <form:input path="accionFarmacologica" />
                    <form:errors path="accionFarmacologica" element="div" cssClass="alert alert-danger"/>

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
                
                <hr/>
                <form:button class="btn btn-primary">Editar Material de laboratorio</form:button>
            </form:form>
            <h1><c:out value="${respuestaerror}"/></h1>
        </div>
    </body>
</html>
