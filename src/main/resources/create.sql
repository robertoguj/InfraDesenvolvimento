INSERT INTO usuario (ativo, cadastro, email, matricula, nome, senha) VALUES (true,'2017-06-01','jose.rocha@cspecem.com','2510','José Rocha','admin');
INSERT INTO grupo (nome, descricao) VALUES ('ADMINISTRADOR','Administrador');
INSERT INTO grupo (nome, descricao) VALUES ('ANALISTA','Analista');
INSERT INTO grupo (nome, descricao) VALUES ('TECNICO','Técnico');
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (1,1);