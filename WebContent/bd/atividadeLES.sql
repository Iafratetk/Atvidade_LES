CREATE DATABASE atividadeLES
GO
USE atividadeLES

CREATE TABLE tbl_produto (
id_produto INT PRIMARY KEY IDENTITY,
pro_nome VARCHAR(255)
)

INSERT INTO tbl_produto VALUES
('teste1'),
('teste2')

SELECT id_produto, pro_nome FROM tbl_produto 

CREATE TABLE cliente (
id INT PRIMARY KEY IDENTITY,
nome VARCHAR(255),
telefone VARCHAR(15),
endereco VARCHAR(255)
)

CREATE TABLE produto (
id INT PRIMARY KEY IDENTITY,
nome VARCHAR(255),
quantidade INT,
valor DECIMAL(7,2)
)

CREATE TABLE pedido (
id INT PRIMARY KEY IDENTITY,
id_cliente INT,
valor_total DECIMAL(7,2),
forma_pagamento INT
FOREIGN KEY (id_cliente) REFERENCES cliente(id),
)

CREATE TABLE pedido_produto (
id_pedido_produto INT PRIMARY KEY IDENTITY,
id_pedido INT,
id_produto INT,
valor_produto DECIMAL(7,2),
quantidade INT
FOREIGN KEY (id_produto) REFERENCES produto(id),
FOREIGN KEY (id_pedido) REFERENCES pedido(id)
)




SELECT id, nome, quantidade, valor FROM produto 

INSERT INTO produto (nome, quantidade, valor ) VALUES 
('teste',10,20.50)

SELECT id,nome,telefone,endereco FROM cliente

INSERT INTO cliente (nome, telefone, endereco)  VALUES
('Fulano', '11930465449','Avenida Aguia de Haia')

SELECT id,id_cliente, valor_total, forma_pagamento FROM pedido

INSERT INTO pedido (id_cliente, valor_total, forma_pagamento) VALUES
(1,20.50,1)

SELECT id_pedido, id_produto, valor_produto FROM pedido_produto

INSERT INTO pedido_produto(id_pedido,id_produto,valor_produto) VALUES
(1,1,20.50)

CREATE TABLE usuario (
id INT PRIMARY KEY IDENTITY,
id_cliente INT,
usu VARCHAR(255),
senha VARCHAR(255),
tipo INT
FOREIGN KEY (id_cliente) REFERENCES cliente(id)
)

SELECT id,id_cliente,usu,senha,tipo FROM usuario


SELECT * FROM pedido

SELECT * FROM produto

SELECT * FROM pedido_produto

SELECT * FROM cliente

SELECT * FROM usuario

INSERT INTO usuario VALUES
(null,'usuario02','123456',3)
