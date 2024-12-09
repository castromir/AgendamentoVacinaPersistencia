<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Vacinas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="index.html">Agendador de Vacinas</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#about">Sobre</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#contact">Contato</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
<div class="container mt-5">
    <h1 class="mb-4">Relação de Vacinas</h1>
    
    <!-- Botão para adicionar nova vacina -->
    <div class="d-flex justify-content-end mb-3">
        <a href="vacina_page.html" class="btn btn-success">
            <span class="fs-4">+</span> Adicionar Vacina
        </a>
    </div>

    <!-- Tabela de Vacinas -->
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>Código</th>
                <th>Título</th>
                <th>Doses</th>
                <th>Periodicidade</th>
                <th>Intervalo</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="vacina" items="${vacinas}">
                <tr>
                    <td>${vacina.id}</td>
                    <td>${vacina.titulo}</td>
                    <td>${vacina.doses}</td>
                    <td>
                    <c:choose>
                            <c:when test="${vacina.periodicidade == 1}">Diário</c:when>
                            <c:when test="${vacina.periodicidade == 2}">Semanal</c:when>
                            <c:when test="${vacina.periodicidade == 3}">Mensal</c:when>
                            <c:when test="${vacina.periodicidade == 4}">Anual</c:when>
                            <c:otherwise>Não definido</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="javascript:confirmar(${vacina.id})" class="btn btn-danger btn-sm">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function confirmar(id) {
    	let resposta = confirm("Confirma excluir essa vacina?")
    	
    	if(resposta === true){
    		window.location.href = "removeVacina?codigo=" + id
    	}
    }
</script>
</body>
</html>
