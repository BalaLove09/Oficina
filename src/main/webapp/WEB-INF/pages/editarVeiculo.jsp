<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
  <title>Editar Veículo</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/oficina-theme.css" rel="stylesheet">
  <style>
    body { padding: 20px; }
  </style>
</head>
<body>
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-header">
          <%-- Verifica se 'id' está presente para determinar se é edição ou cadastro --%>
          <h4><c:out value="${empty veiculo.id ? 'Adicionar Novo Veículo' : 'Editar Veículo'}"/></h4>
        </div>
        <div class="card-body">
          <%-- O FORMULARIO SEMPRE SUBMETE PARA /veiculo --%>
          <form action="${pageContext.request.contextPath}/veiculo" method="post">
            <%-- Campo ID apenas se for edição --%>
            <c:if test="${not empty veiculo.id}">
              <input type="hidden" name="id" value="${veiculo.id}">
            </c:if>

            <%-- AÇÃO: "alterar" se tem ID, "inserir" se não tem ID --%>
            <input type="hidden" name="acao" value="${empty veiculo.id ? 'inserir' : 'alterar'}">

            <div class="mb-3">
              <label for="marca" class="form-label">Marca</label>
              <input type="text" class="form-control" id="marca" name="marca"
                     value="${veiculo.marca}" required>
            </div>

            <div class="mb-3">
              <label for="modelo" class="form-label">Modelo</label>
              <input type="text" class="form-control" id="modelo" name="modelo"
                     value="${veiculo.modelo}" required>
            </div>

            <div class="mb-3">
              <label for="placa" class="form-label">Placa</label>
              <input type="text" class="form-control" id="placa" name="placa"
                     value="${veiculo.placa}" required>
            </div>

            <div class="d-grid gap-2">
              <button type="submit" class="btn btn-primary">Salvar</button>
              <%-- Botão Cancelar: Volta para a listagem na dashboard --%>
              <a href="${pageContext.request.contextPath}/veiculo?opcao=listar"
                 class="btn btn-secondary">Cancelar</a>
            </div>
          </form>

          <c:if test="${not empty erro}">
            <div class="alert alert-danger mt-3">${erro}</div>
          </c:if>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>