-- Tabela de Usuários (para login)
CREATE TABLE usuario (
                         id_usuario SERIAL PRIMARY KEY,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         senha VARCHAR(100) NOT NULL, -- Armazene hash na prática!
                         tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('MECANICO', 'CLIENTE')),
                         ativo BOOLEAN DEFAULT TRUE
);

-- Tabela de Mecânicos (dados específicos)
CREATE TABLE mecanico (
                          id_mecanico SERIAL PRIMARY KEY,
                          nome VARCHAR(80) NOT NULL,
                          especialidade VARCHAR(50) NOT NULL,
                          id_usuario INT NOT NULL UNIQUE,
                          FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Tabela de Clientes (dados específicos)
CREATE TABLE cliente (
                         id_cliente SERIAL PRIMARY KEY,
                         nome VARCHAR(80) NOT NULL,
                         telefone VARCHAR(20),
                         cpf CHAR(11) UNIQUE,
                         id_usuario INT NOT NULL UNIQUE,
                         FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Tabela de Veículos
CREATE TABLE veiculo (
                         id_veiculo SERIAL PRIMARY KEY,
                         modelo VARCHAR(50) NOT NULL,
                         marca VARCHAR(50) NOT NULL,
                         placa VARCHAR(10) UNIQUE NOT NULL,
                         ano INT,
                         id_cliente INT NOT NULL,
                         FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE
);

-- Tabela de Ordens de Serviço (OS)
CREATE TABLE ordem_servico (
                               id_ordem SERIAL PRIMARY KEY,
                               descricao TEXT NOT NULL,
                               data_entrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               data_saida TIMESTAMP,
                               valor DECIMAL(10, 2),
                               status VARCHAR(20) DEFAULT 'PENDENTE' CHECK (status IN ('PENDENTE', 'EM_ANDAMENTO', 'FINALIZADO', 'CANCELADO')),
                               id_veiculo INT NOT NULL,
                               id_mecanico INT NOT NULL,
                               FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo),
                               FOREIGN KEY (id_mecanico) REFERENCES mecanico(id_mecanico)
);

-- Usuários
INSERT INTO usuario (email, senha, tipo) VALUES
                                             ('mecanico@oficina.com', 'senha123', 'MECANICO'),
                                             ('cliente@email.com', 'senha456', 'CLIENTE');

-- Mecânico
INSERT INTO mecanico (nome, especialidade, id_usuario) VALUES
    ('João da Silva', 'Motor', 1);

-- Cliente
INSERT INTO cliente (nome, telefone, cpf, id_usuario) VALUES
    ('Maria Oliveira', '11999998888', '12345678901', 2);

-- Veículo
INSERT INTO veiculo (modelo, marca, placa, ano, id_cliente) VALUES
    ('Gol', 'Volkswagen', 'ABC1D23', 2020, 1);

-- Ordem de Serviço
INSERT INTO ordem_servico (descricao, id_veiculo, id_mecanico, status) VALUES
    ('Troca de óleo e filtro', 1, 1, 'EM_ANDAMENTO');


--Login (Verificar credenciais)
SELECT id_usuario, tipo FROM usuario WHERE email = 'mecanico@oficina.com' AND senha = 'senha123';

--Listar OSs de um Mecânico
SELECT os.id_ordem, v.modelo, v.placa, os.descricao, os.status
FROM ordem_servico os
         JOIN veiculo v ON os.id_veiculo = v.id_veiculo
WHERE os.id_mecanico = 1; -- ID do mecânico logado

--Listar Veículos de um Cliente
SELECT id_veiculo, modelo, marca, placa FROM veiculo WHERE id_cliente = 1;

--Atualizar Status de uma OS (Mecânico)
UPDATE ordem_servico SET status = 'FINALIZADO' WHERE id_ordem = 1;