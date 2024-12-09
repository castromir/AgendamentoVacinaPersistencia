<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Relação de agendas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
        }
    </style>
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
    <h2>Gerenciar Listagens</h2>
    <!-- Abas -->
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <button class="nav-link active" data-target="#listagem1">Todos Agendamentos</button>
        </li>
        <li class="nav-item">
            <button class="nav-link" data-target="#listagem2">Agendamentos para hoje</button>
        </li>
        <li class="nav-item">
            <button class="nav-link" data-target="#listagem3">Agendamentos realizados e cancelados</button>
        </li>
    </ul>

    <!-- Conteúdo das Listagens -->
    <div id="listagem1" class="tab-content active">
        <h3>Todos Agendamentos</h3>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Situação</th>
                    <th>Data agendada</th>
                    <th>Data situação</th>
                    <th>Nome paciente</th>
                    <th>Vacina</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${listaCompleta}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.situacao}</td>
                        <td>${item.data}</td>
                        <td>${item.dataSituacao}</td>
                        <!-- Exibindo o nome do usuário -->
                        <td>${item.usuario.nome}</td>
                        <!-- Exibindo o nome da vacina -->
                        <td>${item.vacina.titulo}</td>
                        <td>
	                	<!-- Botão para concluir -->
	                	<button class="btn btn-success btn-sm" onclick="concluir(${item.id})">
	                    	Concluir
	                	</button>
	                	<!-- Botão para cancelar -->
	                	<button class="btn btn-danger btn-sm" onclick="cancelar(${item.id})">
	                    	Cancelar
	                	</button>
	            		</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="listagem2" class="tab-content">
        <h3>Agendamentos para Hoje</h3>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Situação</th>
                    <th>Data agendada</th>
                    <th>Data situação</th>
                    <th>Nome paciente</th>
                    <th>Vacina</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${listaHoje}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.situacao}</td>
                        <td>${item.data}</td>
                        <td>${item.dataSituacao}</td>
                        <!-- Exibindo o nome do usuário -->
                        <td>${item.usuario.nome}</td>
                        <!-- Exibindo o nome da vacina -->
                        <td>${item.vacina.titulo}</td>
                        <td>
	                	<!-- Botão para concluir -->
	                	<button class="btn btn-success btn-sm" onclick="concluir(${item.id})">
	                    	Concluir
	                	</button>
	                	<!-- Botão para cancelar -->
	                	<button class="btn btn-danger btn-sm" onclick="cancelar(${item.id})">
	                    	Cancelar
	                	</button>
	            		</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
	<div id="listagem3" class="tab-content">
        <h3>Agendamentos realizados e cancelados</h3>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Situação</th>
                    <th>Data agendada</th>
                    <th>Data situação</th>
                    <th>Nome paciente</th>
                    <th>Vacina</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${listaOrdenada}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.situacao}</td>
                        <td>${item.data}</td>
                        <td>${item.dataSituacao}</td>
                        <!-- Exibindo o nome do usuário -->
                        <td>${item.usuario.nome}</td>
                        <!-- Exibindo o nome da vacina -->
                        <td>${item.vacina.titulo}</td>
                        <td>
	                	<!-- Botão para concluir -->
	                	<button class="btn btn-success btn-sm" onclick="concluir(${item.id})">
	                    	Concluir
	                	</button>
	                	<!-- Botão para cancelar -->
	                	<button class="btn btn-danger btn-sm" onclick="cancelar(${item.id})">
	                    	Cancelar
	                	</button>
	            		</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Alternar entre abas
    document.querySelectorAll('.nav-link').forEach(button => {
        button.addEventListener('click', () => {
            document.querySelectorAll('.tab-content').forEach(tab => tab.classList.remove('active'));
            document.querySelectorAll('.nav-link').forEach(link => link.classList.remove('active'));
            
            const target = button.getAttribute('data-target');
            document.querySelector(target).classList.add('active');
            button.classList.add('active');
        });
    });
</script>
<script>
    function concluir(id) {
    	let resposta = confirm("Confirma a realização dessa agenda?")
    	
    	if(resposta === true){
    		window.location.href = "concluir?codigo=" + id
    	}
    }
    function cancelar(id) {
    	let resposta = confirm("Confirma o cancelamento dessa agenda?")
    	
    	if(resposta === true){
    		window.location.href = "cancelar?codigo=" + id
    	}
    }
</script>
</body>
</html>
