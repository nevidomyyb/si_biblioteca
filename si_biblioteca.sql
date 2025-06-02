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