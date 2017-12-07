<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">

        <link rel="icon" href="../../../../favicon.ico">

        <title>Monitoreo VIH - Ver materiales</title>

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
                        <a class="nav-link" href="index.htm">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="PlanificacionPanel.htm">Planes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Personals.htm">Protocolos y Personal</a>
                    </li>
                    <li class="nav-item active" >
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
                    <h1 class="display-4">Mostrando materiales de laboratorio...</h1>
                    <br><br>
                    <div style="text-align: center">
                        <div>
                            <a href="<c:url value="/MaterialLaboratorioAdd.htm"/>" class="btn btn-outline-primary">Añadir Material</a>
                            <br><br>
                            <table class="table table-bordered table-dark">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Tipo de fabricacion</th>
                                        <th>Uso</th>
                                        <th>Forma de presentacion</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${laboratorios}" var="laboratorio">
                                        <tr>
                                            <th><c:out value="${laboratorio.ID_LABORATORIO}"/></th>
                                            <th><c:out value="${laboratorio.tipoFabricacion}"/></th>
                                            <th><c:out value="${laboratorio.uso}"/></th>
                                            <th><c:out value="${laboratorio.formaPresentacion}"/></th>
                                            <th>
                                                <a href="<c:url value="/LaboratorioVer.htm?id=${laboratorio.ID_LABORATORIO}"/>" class="btn btn-info">Ver</a>
                                                <a href="<c:url value="/LaboratorioEditar.htm?id=${laboratorio.ID_LABORATORIO}"/>" class="btn btn-warning">Editar</a>
                                                <a href="<c:url value="/LaboratorioEliminar.htm?id=${laboratorio.ID_LABORATORIO}"/>" class="btn btn-danger">Eliminar</a></th>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <table>
                                <thead>
                                    <tr>
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                     <c:forEach items="${laboratorioserror}" var="laboratorioerror">
                                            <tr>
                                                <th><c:out value="${laboratorioerror}"/></th>
                                            </tr>
                                     </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <br><br>
                        <p><a class="btn btn-outline-success btn-lg" href="<c:out value="index.htm"/>" role="button">Regresar a la pagina principal </a></p>
                    </div>
                </div>
            </div>
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
