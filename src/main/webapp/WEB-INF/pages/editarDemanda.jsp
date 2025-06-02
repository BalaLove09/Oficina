<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Editar Demanda</title>
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
          <h4><c:out value="${empty demanda.id ? 'Adicionar Nova Demanda' : 'Editar Demanda'}"/></h4>
        </div>
        <div class="card-body">
          <form action="${pageContext.request.contextPath}/demanda" method="post">
            <c:if test="${not empty demanda.id}">
              <input type="hidden" name="id" value="${demanda.id}">
            </c:if>

            <input type="hidden" name="acao" value="${empty demanda.id ? 'inserir' : 'alterar'}">

            <div class="mb-3">
              <label for="titulo" class="form-label">Título</label>
              <input type="text" class="form-control" id="titulo" name="titulo"
                     value="${demanda.titulo}" required>
            </div>

            <div class="mb-3">
              <label for="descricao" class="form-label">Descrição</label>
              <textarea class="form-control" id="descricao" name="descricao" rows="3" required>${demanda.descricao}</textarea>
            </div>

            <div class="d-grid gap-2">
              <button type="submit" class="btn btn-primary">Salvar</button>
              <a href="${pageContext.request.contextPath}/demanda?opcao=listar"
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