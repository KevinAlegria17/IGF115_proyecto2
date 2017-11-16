
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Home - Prueba_2</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <div style="text-align: center;">
            <h1>Hola desde prueba 2</h1>
            <h2>Estas en Home</h2>
            <hr/>
            <a href="<c:url value="/videojuegos.htm"/>">Ver lista de videojuegos</a>
            <hr/>
            <a href="<c:url value="/consolas.htm "/>">Ver lista de consolas para videojuegos</a>
            <hr/>
            <a href="<c:url value="/categorias.htm "/>">Ver lista de categorias para videojuegos</a>
            <hr/>
            <a href="<c:url value="/usuarios.htm "/>">Ver lista de usuarios registrados</a>
            <hr/>
            <a href="<c:url value="/prestamos.htm "/>">Ver lista de prestamos registrados</a>
            <hr/>
        </div>
        
    </body>
</html>
