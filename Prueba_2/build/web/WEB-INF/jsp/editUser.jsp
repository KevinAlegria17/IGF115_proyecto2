<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Editar Usuario - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde editar usuario</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
        <hr/>
        <a href="<c:url value="/usuarios.htm"/>">Regresar a Lista de usuarios</a>
        <hr/>
        <div style="text-align:center;">
        <form:form method="post" commandName="usuario" >
            <p>
                <form:label path="nombre">Nombre</form:label>
                <form:input path="nombre" />
                <form:errors path="nombre" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="prestamo">Prestamo</form:label>
                <form:select path="prestamo">
                    <form:option value="0">NO</form:option>
                    <form:option value="1">SI</form:option>
                </form:select>
                <form:errors path="prestamo" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <hr/>
            <form:button class="btn btn-primary">Finalizar Edicion</form:button>
        </form:form>
        
        </div>
    </body>
</html>
