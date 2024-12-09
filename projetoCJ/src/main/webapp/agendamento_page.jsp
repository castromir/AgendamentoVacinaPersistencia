<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agendar Vacina</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Agendamento de Vacina</h2>
    <form action="agendarVacina">
        <div class="mb-3">
            <label for="usuario" class="form-label">Selecione o Usuário</label>
            <select class="form-select" name="usuarioId" id="usuario" required>
                <option value="">Selecione um Usuário</option>
                <!-- Iteração com JSTL para listar usuários -->
                <c:forEach var="usuario" items="${usuarios}">
                    <option value="${usuario.id}">${usuario.nome}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="vacina" class="form-label">Selecione a Vacina</label>
            <select class="form-select" name="vacinaId" id="vacina" required>
                <option value="">Selecione uma Vacina</option>
                <!-- Iteração com JSTL para listar vacinas -->
                <c:forEach var="vacina" items="${vacinas}">
                    <option value="${vacina.id}">${vacina.titulo}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="data" class="form-label">Selecione a Data</label>
            <input type="date" class="form-control" name="data" id="data" required>
        </div>

        <button type="submit" class="btn btn-primary">Agendar</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
