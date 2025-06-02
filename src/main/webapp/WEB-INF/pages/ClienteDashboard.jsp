<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="br.csi.model.Usuario" %>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/oficina-theme.css" rel="stylesheet">
    <style>
        body { padding: 20px; }
        .welcome { margin-bottom: 30px; }
        .card { margin-bottom: 20px; }
        .badge { font-size: 0.9em; }
        .row { align-items: flex-start; }
    </style>
</head>
<body>

<div class="container">
    <div class="welcome d-flex justify-content-between align-items-center">
        <div>
            <h2>Bem-vindo, <c:out value="${usuario.nome}"/>!</h2>
            <p>Você está logado como <strong>Cliente</strong></p>
        </div>
        <a href="${pageContext.request.contextPath}/login?acao=logout" class="btn btn-danger">Sair</a>
    </div>

    <%-- Exibição de Mensagens de Sucesso/Erro --%>
    <c:if test="${not empty mensagemSucesso}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <c:out value="${mensagemSucesso}"/>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
    <c:if test="${not empty mensagemErro}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <c:out value="${mensagemErro}"/>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h5>Adicionar Veículo</h5>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/veiculo" method="post">
                        <input type="hidden" name="acao" value="inserir"> <%-- Adiciona a ação para o servlet --%>
                        <div class="mb-3">
                            <label for="marca" class="form-label">Marca</label>
                            <input type="text" class="form-control" id="marca" name="marca" required>
                        </div>
                        <div class="mb-3">
                            <label for="modelo" class="form-label">Modelo</label>
                            <input type="text" class="form-control" id="modelo" name="modelo" required>
                        </div>
                        <div class="mb-3">
                            <label for="placa" class="form-label">Placa</label>
                            <input type="text" class="form-control" id="placa" name="placa" required>
                        </div>

                        <button type="submit" class="btn btn-primary">Cadastrar Veículo</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h5>Meus Veículos</h5>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${empty veiculos}">
                            <p class="text-muted">Nenhum veículo cadastrado.</p>
                        </c:when>
                        <c:otherwise>
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>Marca/Modelo</th>
                                        <th>Placa</th>
                                        <th>Ações</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${veiculos}" var="veiculo">
                                        <tr>
                                            <td>
                                                <strong><c:out value="${veiculo.marca}"/> <c:out value="${veiculo.modelo}"/></strong>
                                            </td>
                                            <td>
                                                <c:out value="${veiculo.placa}"/>
                                            </td>
                                            <td>
                                                    <%-- Links de Edição e Exclusão --%>
                                                <a href="${pageContext.request.contextPath}/veiculo?opcao=editar&info=${veiculo.id}"
                                                   class="btn btn-sm btn-outline-primary">Editar</a>
                                                <a href="${pageContext.request.contextPath}/veiculo?opcao=excluir&info=${veiculo.id}"
                                                   class="btn btn-sm btn-outline-danger"
                                                   onclick="return confirm('Tem certeza que deseja excluir este veículo?');">Excluir</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>