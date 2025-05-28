<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { padding: 20px; }
        .welcome { margin-bottom: 30px; }
        .card { margin-bottom: 20px; }
    </style>
</head>
<body>
<div class="container">
    <div class="welcome">
        <h2>Bem-vindo, <c:out value="${usuario.nome}"/>!</h2>
        <p>Você está logado como <strong>Cliente</strong></p>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h5>Meus Veículos</h5>
                </div>
                <div class="card-body">
                    <c:forEach items="${veiculos}" var="veiculo">
                        <div class="mb-3">
                            <h6><c:out value="${veiculo.marca} ${veiculo.modelo}"/></h6>
                            <p>Placa: <c:out value="${veiculo.placa}"/></p>
                            <a href="novaOS?veiculoId=${veiculo.id}" class="btn btn-sm btn-primary">Abrir OS</a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h5>Minhas Ordens de Serviço</h5>
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Data</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ordensCliente}" var="os">
                            <tr>
                                <td><c:out value="${os.id}"/></td>
                                <td><c:out value="${os.dataEntrada}"/></td>
                                <td><c:out value="${os.status}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>