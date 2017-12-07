<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">

        <link rel="icon" href="../../../../favicon.ico">

        <title>Monitoreo VIH - Material Laboratorio</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
        <!-- Custom styles for this template -->
        <link href="jumbotron.css" rel="stylesheet">
    </head>

    <body>

        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" href="#">Monitoreo VIH</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item ">
                        <a class="nav-link" href="<c:out value="index.htm"/>">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="PlanificacionPanel.htm">Planes</a>
                    </li>
                    <li class="nav-item " >
                        <a class="nav-link" href="Personals.htm">Protocolos y Personal</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="Laboratorios.htm">Material laboratorio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Tars.htm">Material TAR</a>
                    </li>

                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="text" placeholder="Digite" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
                </form>
            </div>
        </nav><!-- NavBar-->

        <main role="main">

            <!-- Main jumbotron for a primary marketing message or call to action -->
            <div class="jumbotron">

                <div class="container">
                    <h1 class="display-4">Añadiendo Material de laboratorio...</h1>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <form:form method="post" commandName="material-lab" >
                            <p>
                                <form:label path="id">ID:</form:label><br>
                                <form:input path="id" />
                                <form:errors path="id" element="div" cssClass="alert alert-danger"/>

                            </p>
                            <p>
                                <form:label path="tipoFabricacion">Tipo de fabricacion:</form:label><br>
                                <form:input path="tipoFabricacion" />
                                <form:errors path="tipoFabricacion" element="div" cssClass="alert alert-danger"/>

                            </p>
                            <p>
                                <form:label path="uso">Uso:</form:label><br>
                                <form:input path="uso" />
                                <form:errors path="uso" element="div" cssClass="alert alert-danger"/>

                            </p>
                            <p>
                                <form:label path="formaPresentacion">Forma de Presentacion:</form:label><br>
                                <form:input path="formaPresentacion" />
                                <form:errors path="formaPresentacion" element="div" cssClass="alert alert-danger"/>

                            </p>
                            <form:button class="btn btn-primary">Añadir Material de laboratorio</form:button>
                        </form:form>
                        
                    </div>
                    <div class="col-md-8">
                        <a  class="btn btn-outline-success btn-lg" href="index.htm" role="button">Regresar </a>
                        <br>
                        <p><c:out value="${respuesta}"/></p>
                    </div>
                </div>
                    <hr>
            </div> <!-- /container -->

        </main><!-- Principal -->

        <footer class="container">
            <p>&copy; Grupo 09 - IG115 - 2017</p>
        </footer><!-- Footer -->

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="../../../../assets/js/vendor/popper.min.js"></script>
        <script src="../../../../dist/js/bootstrap.min.js"></script>
    </body>
</html>
