CREATE DATABASE sis_biblioteca;
USE sis_biblioteca;
-- Criar tabela Autor
CREATE TABLE autor (id int primary key auto_increment, nome varchar(100) not null, data_nascimento date, pseudonimo varchar(100));
-- Criar tabela Gênero
CREATE TABLE genero (id int primary key auto_increment, genero varchar(50) not null);
-- Criar tabela Endereço
CREATE TABLE endereco (
                          id int primary key auto_increment,
                          rua varchar(100) not null,
                          numero varchar(50) not null,
                          bairro varchar(50) not null,
                          cep varchar(50),
                          cidade varchar(50) not null,
                          estado varchar(50) not null
);
-- Criar tabela Professor
CREATE TABLE professor (
                           id int primary key auto_increment,
                           nome varchar(100) not null,
                           telefone varchar(11),
                           email varchar(50) not null,
                           disciplina varchar(50) not null,
                           credencial varchar(10) not null,
                           cpf varchar(11) not null,
                           endereco_id int null, constraint fk_professor_endereco foreign key (endereco_id) references endereco(id),
                           login varchar(12) not null unique,
                           senha text not null
);
-- Criar tabela Aluno
CREATE TABLE aluno (
                       id int primary key auto_increment,
                       nome varchar(100) not null,
                       telefone varchar(11),
                       email varchar(50) not null,
                       curso varchar(50) not null,
                       periodo varchar(10) not null,
                       turno varchar(30) not null,
                       matricula varchar(10) not null,
                       cpf varchar(11) not null,
                       endereco_id int null, constraint fk_aluno_endereco foreign key (endereco_id) references endereco(id),
                       login varchar(12) not null unique,
                       senha text not null
);
-- Criar tabela Funcionário
CREATE TABLE funcionario (
                             id int primary key auto_increment,
                             nome varchar(100) not null,
                             telefone varchar(11),
                             email varchar(50) not null,
                             credencial varchar(10) not null,
                             cpf varchar(11) not null,
                             endereco_id int null, constraint fk_funcionario_endereco foreign key (endereco_id) references endereco(id),
                             login varchar(12) not null unique,
                             senha text not null
);
-- Criar tabela Editora
CREATE TABLE editora(
                        id int primary key auto_increment,
                        nome varchar(100) not null,
                        cnpj varchar(14) not null,
                        endereco_id int null, constraint fk_editora_endereco foreign key (endereco_id) references endereco(id)
);
-- Criar tabela Livro
CREATE TABLE livro(
                      id int primary key auto_increment,
                      titulo varchar(150) not null,
                      editora_id int not null, constraint fk_livro_editora foreign key (editora_id) references editora(id),
                      genero_id int not null, constraint fk_livro_genero foreign key (genero_id) references genero(id),
                      autor_id int not null, constraint fk_livro_autor foreign key (autor_id) references autor(id),
                      edicao varchar(10) not null,
                      sinopse text not null
);
-- Criar tabela Exemplar
CREATE TABLE exemplar(
                         id int primary key auto_increment,
                         livro_id int not null, constraint fk_exemplar_livro foreign key (livro_id) references livro(id)
);
-- Criar tabela Operação
CREATE TABLE operacao(
                         id int primary key auto_increment,
                         funcionario_locacao_id int not null, constraint fk_operacao_funcionario_locacao foreign key (funcionario_locacao_id) references funcionario(id),
                         funcionario_devolucao_id int, constraint fk_operacao_funcionario_devolucao foreign key (funcionario_devolucao_id) references funcionario(id),
                         aluno_id int, constraint fk_operacao_aluno foreign key (aluno_id) references aluno(id),
                         professor_id int, constraint fk_operacao_professor foreign key (professor_id) references professor(id),
                         funcionario_id int, constraint fk_operacao_funcionario foreign key (funcionario_id) references funcionario(id),
                         exemplar_id int, constraint fk_operacao_exemplar foreign key (exemplar_id) references exemplar(id),
                         tipo_operacao ENUM('LOCACAO', 'DEVOLUCAO') not null,
                         data_locacao datetime not null,
                         data_devolucao datetime not null,
                         data_devolvido datetime
);
-- Criar tabela LivroAutor
CREATE TABLE livro_autor(
                            id int primary key auto_increment,
                            livro_id int not null, constraint fk_livro_autor_livro foreign key (livro_id) references livro(id),
                            autor_id int, constraint fk_livro_autor_autor foreign key (autor_id) references autor(id)
);
-- Criar tabela LivroGenero
CREATE TABLE livro_genero(
                             id int primary key auto_increment,
                             livro_id int not null, constraint fk_livro_genero_livro foreign key (livro_id) references livro(id),
                             genero_id int, constraint fk_livro_genero_genero foreign key (genero_id) references genero(id)
);



-- TABELA: endereco
INSERT INTO endereco (rua, numero, bairro, cep, cidade, estado) VALUES
                                                                    ('Rua A', '123', 'Centro', '57000-000', 'Maceió', 'AL'),
                                                                    ('Rua B', '456', 'Jatiúca', '57001-000', 'Maceió', 'AL'),
                                                                    ('Rua C', '789', 'Ponta Verde', '57002-000', 'Maceió', 'AL'),
                                                                    ('Rua D', '101', 'Trapiche', '57003-000', 'Maceió', 'AL'),
                                                                    ('Rua E', '202', 'Benedito Bentes', '57004-000', 'Maceió', 'AL'),
                                                                    ('Rua F', '303', 'Farol', '57005-000', 'Maceió', 'AL'),
                                                                    ('Rua G', '404', 'Gruta', '57006-000', 'Maceió', 'AL'),
                                                                    ('Rua H', '505', 'Serraria', '57007-000', 'Maceió', 'AL'),
                                                                    ('Rua I', '606', 'Mangabeiras', '57008-000', 'Maceió', 'AL'),
                                                                    ('Rua J', '707', 'Jaraguá', '57009-000', 'Maceió', 'AL');

-- TABELA: editora
INSERT INTO editora (nome, cnpj, endereco_id) VALUES
                                                  ('HarperCollins', '11223344556677', 1),
                                                  ('Suma', '99887766554433', 2),
                                                  ('Globo Livros', '22334455667788', 3),
                                                  ('Companhia das Letras', '33445566778899', 4),
                                                  ('Intrínseca', '44556677889900', 5),
                                                  ('Record', '55667788990011', 6),
                                                  ('Rocco', '66778899001122', 7),
                                                  ('Planeta', '77889900112233', 8),
                                                  ('L&PM', '88990011223344', 9),
                                                  ('DarkSide', '99001122334455', 10);

-- TABELA: genero
INSERT INTO genero (genero) VALUES
                                ('Ficção'),
                                ('Romance'),
                                ('Fantasia'),
                                ('Terror'),
                                ('História'),
                                ('Biografia'),
                                ('Aventura'),
                                ('Mistério'),
                                ('Drama'),
                                ('Suspense');

-- TABELA: autor
INSERT INTO autor (nome, data_nascimento, pseudonimo) VALUES
                                                          ('João Silva', '1980-05-10', 'J. Silva'),
                                                          ('Maria Oliveira', '1975-09-23', NULL),
                                                          ('Carlos Souza', '1990-12-01', 'C. S. Night'),
                                                          ('Ana Beatriz', '1985-03-14', 'A. B. Lima'),
                                                          ('Paulo Lima', '1960-11-30', NULL),
                                                          ('Fernanda Costa', '1992-07-07', 'F. C.'),
                                                          ('Rafael Martins', '1988-04-20', 'R. M.'),
                                                          ('Juliana Andrade', '1970-08-25', NULL),
                                                          ('Lucas Rocha', '1995-02-10', 'L. R.'),
                                                          ('Camila Dias', '1983-06-16', 'C. D.');

-- TABELA: livro
INSERT INTO livro (titulo, editora_id, genero_id, autor_id, edicao, sinopse) VALUES
                                                                                 ('O Segredo do Vale', 1, 1, 1, '1ª', 'Uma jornada misteriosa.'),
                                                                                 ('Amor em Tempos Modernos', 2, 2, 2, '2ª', 'Uma história de amor e superação.'),
                                                                                 ('Sombras da Noite', 3, 4, 3, '1ª', 'Contos de arrepiar.'),
                                                                                 ('As Crônicas do Tempo', 4, 3, 4, '3ª', 'Viagens temporais incríveis.'),
                                                                                 ('O Último Herói', 5, 7, 5, '1ª', 'Aventura épica em terras distantes.'),
                                                                                 ('Segredos da Floresta', 6, 8, 6, '2ª', 'Mistérios escondidos entre as árvores.'),
                                                                                 ('Reflexos da Alma', 7, 9, 7, '1ª', 'Drama familiar tocante.'),
                                                                                 ('Sussurros no Escuro', 8, 10, 8, '1ª', 'Suspense psicológico intenso.'),
                                                                                 ('Histórias Reais', 9, 6, 9, '4ª', 'Biografias impactantes.'),
                                                                                 ('O Livro Perdido', 10, 5, 10, '2ª', 'Uma busca histórica por um manuscrito.');

-- TABELA: exemplar
INSERT INTO exemplar (livro_id) VALUES
                                    (1), (2), (3), (4), (5), (6), (7), (8), (9), (10), (1), (2), (3), (1), (2), (3), (1);

-- TABELA: funcionario
INSERT INTO funcionario (nome, telefone, email, credencial, cpf, endereco_id, login, senha) VALUES
    ('Lucas Mendes', '82988887777', 'lucas@email.com', 'ADMIN', '12345678901', 1, 'lucasm', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Ana Lima', '82999998888', 'ana@email.com', 'FUNC', '10987654321', 2, 'analima', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Carlos Mota', '82991112222', 'carlos@email.com', 'FUNC', '23456789012', 3, 'carlosm', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Beatriz Ramos', '82992223333', 'beatriz@email.com', 'FUNC', '34567890123', 4, 'beatrizr', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Daniel Alves', '82993334444', 'daniel@email.com', 'ADMIN', '45678901234', 5, 'daniela', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Marina Teixeira', '82994445555', 'marina@email.com', 'FUNC', '56789012345', 6, 'marinat', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('João Pedro', '82995556666', 'joaop@email.com', 'FUNC', '67890123456', 7, 'joaop', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Carla Souza', '82996667777', 'carla@email.com', 'FUNC', '78901234567', 8, 'carlas', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Henrique Dias', '82997778888', 'henrique@email.com', 'FUNC', '89012345678', 9, 'henriqued', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Priscila Lopes', '82998889999', 'priscila@email.com', 'FUNC', '90123456789', 10, 'priscilal', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ==');

-- TABELA: professor
INSERT INTO professor (nome, telefone, email, disciplina, credencial, cpf, endereco_id, login, senha) VALUES
  ('Clara Torres', '82987654321', 'clara@escola.com', 'Literatura', 'PROF', '45678912300', 1, 'clarat', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
  ('André Silva', '82981234567', 'andre@escola.com', 'História', 'PROF', '56789123401', 2, 'andres', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
  ('Bianca Rocha', '82982345678', 'bianca@escola.com', 'Filosofia', 'PROF', '67891234502', 3, 'biancar', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
  ('Eduardo Lima', '82983456789', 'eduardo@escola.com', 'Sociologia', 'PROF', '78912345603', 4, 'eduardol', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
  ('Fernanda Dias', '82984567890', 'fernanda@escola.com', 'Matemática', 'PROF', '89013456704', 5, 'fernandad', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
  ('Rogério Cunha', '82985678901', 'rogerio@escola.com', 'Física', 'PROF', '90124567805', 6, 'rogerioc', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
  ('Isabela Pinto', '82986789012', 'isabela@escola.com', 'Química', 'PROF', '01235678906', 7, 'isabelap', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
  ('Marcos Dantas', '82987890123', 'marcos@escola.com', 'Biologia', 'PROF', '12346789007', 8, 'marcosd', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
  ('Juliana Melo', '82988901234', 'juliana@escola.com', 'Geografia', 'PROF', '23457890108', 9, 'julianam', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
  ('Otávio Luz', '82989012345', 'otavio@escola.com', 'Educação Física', 'PROF', '34568901209', 10, 'otaviol', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ==');

-- TABELA: aluno
INSERT INTO aluno (nome, telefone, email, curso, periodo, turno, matricula, cpf, endereco_id, login, senha) VALUES
    ('Pedro Cunha', '82991234567', 'pedro@aluno.com', 'Engenharia', '5º', 'Manhã', '20230001', '98765432100', 2, 'pedroc', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Laura Dias', '82992345678', 'laura@aluno.com', 'Medicina', '3º', 'Tarde', '20230002', '87654321001', 3, 'laurad', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Vinícius Rocha', '82993456789', 'vinicius@aluno.com', 'Direito', '7º', 'Noite', '20230003', '76543210012', 4, 'viniciusr', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Paula Costa', '82994567890', 'paula@aluno.com', 'Arquitetura', '6º', 'Manhã', '20230004', '65432100123', 5, 'paulac', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Tiago Melo', '82995678901', 'tiago@aluno.com', 'Odontologia', '2º', 'Tarde', '20230005', '54321001234', 6, 'tiagom', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Bruna Martins', '82996789012', 'bruna@aluno.com', 'Enfermagem', '8º', 'Noite', '20230006', '43210012345', 7, 'brunam', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Diego Lima', '82997890123', 'diego@aluno.com', 'Administração', '4º', 'Manhã', '20230007', '32100123456', 8, 'diegol', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Mariana Silva', '82998901234', 'mariana@aluno.com', 'Psicologia', '5º', 'Tarde', '20230008', '21001234567', 9, 'marianas', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Felipe Oliveira', '82999012345', 'felipe@aluno.com', 'Nutrição', '1º', 'Manhã', '20230009', '10012345678', 10, 'felipeo', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ=='),
    ('Rafaela Santos', '82990123456', 'rafaela@aluno.com', 'Veterinária', '9º', 'Noite', '20230010', '90123456789', 1, 'rafaelas', 'vDwk9JFua1Uf1CIaTP6m2CRZxPxkEV2Dm6i90dFB75g=$DNlQQJZqOq43gxkSgzd2qQ==');

-- TABELA: operacao (empréstimos)
INSERT INTO operacao (
    funcionario_locacao_id, funcionario_devolucao_id, aluno_id, professor_id, funcionario_id,
    exemplar_id, tipo_operacao, data_locacao, data_devolucao, data_devolvido
) VALUES
      (1, 2, 1, NULL, NULL, 1, 'DEVOLUCAO', '2025-05-01', '2025-05-08', '2025-05-07'),
      (2, 1, NULL, 1, NULL, 2, 'DEVOLUCAO', '2025-05-02', '2025-05-09', '2025-05-10');

INSERT INTO operacao (
    funcionario_locacao_id, funcionario_devolucao_id, aluno_id, professor_id, funcionario_id,
    exemplar_id, tipo_operacao, data_locacao, data_devolucao, data_devolvido
) VALUES
    (1, NULL, 1, NULL, NULL, 3, 'LOCACAO', '2025-06-05', '2025-06-12', NULL);

INSERT INTO operacao (
    funcionario_locacao_id, funcionario_devolucao_id, aluno_id, professor_id, funcionario_id,
    exemplar_id, tipo_operacao, data_locacao, data_devolucao, data_devolvido
) VALUES
      (2, NULL, NULL, 1, NULL, 4, 'LOCACAO', '2025-06-04', '2025-06-11', NULL),
      (2, NULL, NULL, 1, NULL, 5, 'LOCACAO', '2025-06-04', '2025-06-11', NULL),

-- Outro professor locou 1 livro
      (1, NULL, NULL, 2, NULL, 6, 'LOCACAO', '2025-06-06', '2025-06-13', NULL);

INSERT INTO operacao (
    funcionario_locacao_id, funcionario_devolucao_id, aluno_id, professor_id, funcionario_id,
    exemplar_id, tipo_operacao, data_locacao, data_devolucao, data_devolvido
) VALUES
    (2, NULL, NULL, NULL, 1, 7, 'LOCACAO', '2025-06-07', '2025-06-14', NULL);

-- 1.
-- Caso o objetivo seja visualizar os dados do aluno de todas as locações
SELECT op.id, op.tipo_operacao, al.*
FROM operacao op
         LEFT JOIN aluno al ON op.aluno_id = al.id
WHERE op.aluno_id IS NOT NULL AND op.tipo_operacao = 'LOCACAO';
-- Caso o objetivo seja visualizar os dados do aluno em uma locação específica
SELECT op.id, op.tipo_operacao, al.*
FROM operacao op
         LEFT JOIN aluno al ON op.aluno_id = al.id
WHERE op.aluno_id IS NOT NULL AND op.id = 3;

-- 2.
-- Select com foco em exemplares com locações ainda não devolvidas.
SELECT l.titulo, COUNT(*)
FROM livro l
         LEFT JOIN exemplar e ON e.livro_id = l.id
         LEFT JOIN operacao op ON op.exemplar_id = e.id
WHERE op.tipo_operacao = 'LOCACAO'
GROUP BY l.titulo;
-- Select com o objetivo de checar a quantidade de exemplares de um livro especifico.
SELECT l.titulo, COUNT(*)
FROM livro l
         LEFT JOIN exemplar e ON e.livro_id = l.id
         LEFT JOIN operacao op ON op.exemplar_id = e.id
WHERE op.tipo_operacao = 'LOCACAO' AND l.id = 4
GROUP BY l.titulo;

-- 3.
SELECT p.nome, COUNT(*) AS 'quantidade_locada'
FROM professor p
         LEFT JOIN operacao op ON op.professor_id = p.id
WHERE op.tipo_operacao = 'LOCACAO'
GROUP BY p.nome
ORDER BY quantidade_locada DESC LIMIT 1;