-- • Quais as 10 operadoras com maiores despesas em "EVENTOS/ SINISTROS CONHECIDOS OU
-- AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR" no último trimestre?

-- A partir de hoje:
SELECT 
    oa.razao_social,
    oa.nome_fantasia,
    SUM(dc.vl_saldo_final) AS total_despesas
FROM demonstracoes_contabeis dc
JOIN operadoras_ativas oa 
    ON dc.reg_ans = oa.registro_ans
WHERE 
    dc.descricao = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR '
    AND dc.data >= DATE_SUB(CURDATE(), INTERVAL 3 MONTH)  -- Últimos 3 meses
GROUP BY oa.razao_social, oa.nome_fantasia
ORDER BY total_despesas DESC
LIMIT 10;

-- Considerando os últimos 3 meses de dados
SELECT 
    oa.razao_social,
    oa.nome_fantasia,
    SUM(dc.vl_saldo_final) AS total_despesas
FROM demonstracoes_contabeis dc
JOIN operadoras_ativas oa 
    ON dc.reg_ans = oa.registro_ans
WHERE 
    dc.descricao = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR '
    AND dc.data BETWEEN '2023-07-01' AND '2023-09-30'  -- Último trimestre baseado nos dados
GROUP BY oa.razao_social, oa.nome_fantasia
ORDER BY total_despesas DESC
LIMIT 10;


-- • Quais as 10 operadoras com maiores despesas nessa categoria no último ano?
SELECT 
    oa.razao_social,
    oa.nome_fantasia,
    SUM(dc.vl_saldo_final) AS total_despesas
FROM demonstracoes_contabeis dc
JOIN operadoras_ativas oa 
    ON dc.reg_ans = oa.registro_ans
WHERE 
    dc.descricao = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR '
    AND dc.data >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)  -- Últimos 12 meses
GROUP BY oa.razao_social, oa.nome_fantasia
ORDER BY total_despesas DESC
LIMIT 10;
