<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Añadir Videojuego - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Hola desde añadir videojuego</h1>
        <a href="<c:url value="/home.htm"/>">Regresar a Home</a>
        <div style="text-align:center;">
        <form:form method="post" commandName="videojuego" >
            
            <p>
                <form:label path="categoria">Categoria</form:label>
                <form:select path="categoria">
                    <form:option value="0">Seleccione... </form:option>
                    <c:forEach items="${cats}" var="cat">
                    <form:option value="${cat.id}"><c:out value="${cat.nombre}"/></form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="categoria" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="consola">Consola</form:label>
                <form:select path="consola">
                    <form:option value="0">Seleccione... </form:option>
                    <c:forEach items="${cons}" var="con">
                        
                        <form:option value="${con.id_con}"><c:out value="${con.nombre_con}"/></form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="consola" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <p>
                <form:label path="nombre">Nombre</form:label>
                <form:input path="nombre" />
                <form:errors path="nombre" element="div" cssClass="alert alert-danger"/>
            
            </p>
            <hr/>
            <form:button class="btn btn-primary">Añadir</form:button>
        </form:form>
        </div>
    </body>
</html>
