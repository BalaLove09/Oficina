-- Tabela de Usuários (com nome incluído)
CREATE TABLE usuario (
                         id_usuario SERIAL PRIMARY KEY,
                         nome VARCHAR(80) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         senha VARCHAR(100) NOT NULL, -- Armazene hash na prática!
                         tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('MECANICO', 'CLIENTE')),
                         ativo BOOLEAN DEFAULT TRUE
);

-- Tabela de Mecânicos (dados específicos)
CREATE TABLE mecanico (
                          id_mecanico SERIAL PRIMARY KEY,
                          id_usuario INT NOT NULL UNIQUE,
                          FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Tabela de Clientes (dados específicos)
CREATE TABLE cliente (
                         id_cliente SERIAL PRIMARY KEY,
                         id_usuario INT NOT NULL UNIQUE,
                         FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Tabela de Ordens de Serviço (Versão Simplificada)
CREATE TABLE veiculo (
                         id SERIAL PRIMARY KEY,
                         id_cliente INT NOT NULL,
                         marca VARCHAR(50),
                         modelo VARCHAR(50),
                         placa VARCHAR(20),
                         FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE
);

CREATE TABLE demanda (
                         id SERIAL PRIMARY KEY,
                         titulo VARCHAR(100),
                         descricao TEXT,
                         id_mecanico INT NOT NULL,
                         FOREIGN KEY (id_mecanico) REFERENCES mecanico(id_mecanico) ON DELETE CASCADE
);
