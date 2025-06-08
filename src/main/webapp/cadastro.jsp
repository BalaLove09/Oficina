<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Cadastrar</title>
  <meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/oficina-theme.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
  <h1 class="mb-4">Cadastro de Usuário</h1>

  <form method="post" action="${pageContext.request.contextPath}/cadastro" class="row g-3">

    <div class="col-12">
      <input type="text" name="nome" placeholder="Nome" required class="form-control" />
    </div>

    <div class="col-12">
      <input type="email" name="email" placeholder="Email" required class="form-control" />
    </div>

    <div class="col-12">
      <input type="password" name="senha" placeholder="Senha" required class="form-control" />
    </div>

    <div class="col-12">
      <select name="tipo" required class="form-select">
        <option value="">Selecione o tipo</option>
        <option value="CLIENTE">Cliente</option>
        <option value="MECANICO">Mecânico</option>
      </select>
    </div>

    <div class="col-12">
      <button type="submit" class="btn btn-primary">Cadastrar</button>
    </div>

  </form>

  <c:if test="${not empty mensagemErro}">
    <p class="mt-3 text-danger"><c:out value="${mensagemErro}"/></p>
  </c:if>
  <c:if test="${not empty mensagemSucesso}">
    <p class="mt-3 text-success"><c:out value="${mensagemSucesso}"/></p>
  </c:if>

  <div class="mt-3">
    Já tem uma conta? <a href="${pageContext.request.contextPath}/login.jsp">Faça login aqui</a>
  </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
