<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Mecânico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { padding: 20px; }
        .welcome { margin-bottom: 30px; }
        table { margin-top: 20px; }
    </style>
</head>
<body>
<div class="container">
    <div class="welcome">
        <h2>Bem-vindo, <c:out value="${usuario.nome}"/>!</h2>
        <p>Você está logado como <strong>Mecânico</strong></p>
    </div>

    <h3>Ordens de Serviço</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Veículo</th>
            <th>Descrição</th>
            <th>Status</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ordens}" var="os">
            <tr>
                <td><c:out value="${os.id}"/></td>
                <td><c:out value="${os.veiculo.modelo}"/></td>
                <td><c:out value="${os.descricao}"/></td>
                <td>
                            <span class="badge
                                <c:choose>
                                    <c:when test="${os.status == 'FINALIZADO'}">bg-success</c:when>
                                    <c:when test="${os.status == 'CANCELADO'}">bg-danger</c:when>
                                    <c:otherwise>bg-warning text-dark</c:otherwise>
                                </c:choose>">
                                <c:out value="${os.status}"/>
                            </span>
                </td>
                <td>
                    <a href="detalhesOS?id=${os.id}" class="btn btn-sm btn-primary">Detalhes</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>