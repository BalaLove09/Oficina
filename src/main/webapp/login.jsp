<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Login - Oficina Mecânica</title>
    <!-- Bootstrap CSS (opcional) -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/oficina-theme.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container">
    <div class="login-container">
        <h2 class="text-center mb-4">Login</h2>

        <!-- Exibe mensagens de erro (se houver) -->
        <c:if test="${not empty erro}">
            <div class="alert alert-danger" role="alert">
                    ${erro}
            </div>
        </c:if>

        <!-- Formulário de Login -->
        <form action="login" method="post">
        <div class="mb-3">
                <label for="email" class="form-label">E-mail:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="senha" class="form-label">Senha:</label>
                <input type="password" class="form-control" id="senha" name="senha" required>
            </div>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">Entrar</button>
            </div>
        </form>

        <!-- Link para cadastro (opcional) -->
        <div class="mt-3 text-center">
            <p>Não tem uma conta? <a href="${pageContext.request.contextPath}/cadastro.jsp">Cadastre-se</a></p>
        </div>
    </div>
</div>

<!-- Bootstrap JS (opcional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>