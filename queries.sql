use sis_biblioteca;

-- 1. - Retornar dados do aluno que efetuou uma locação.
SELECT al.*
FROM operacao op
         LEFT JOIN aluno al ON op.aluno_id = al.id
WHERE op.aluno_id IS NOT NULL AND op.id = 3; -- ID da locação

-- 2. Retornar a quantidade de exemplares locados de um determinado livro.

SELECT l.titulo, COUNT(*)
FROM livro l
         LEFT JOIN exemplar e ON e.livro_id = l.id
         LEFT JOIN operacao op ON op.exemplar_id = e.id
WHERE op.tipo_operacao = 'LOCACAO' AND l.id = 4 -- ID do livro
GROUP BY l.titulo;

-- 3. Retornar qual o professor que mais locou exemplares.
SELECT p.nome, COUNT(*) AS 'quantidade_locada'
FROM professor p
         INNER JOIN operacao op ON op.professor_id = p.id
WHERE op.tipo_operacao = 'LOCACAO'
GROUP BY p.nome
ORDER BY quantidade_locada DESC
    LIMIT 1;