USE ans_data;

SET GLOBAL local_infile = 1;
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;


-- Primeiro Arquivo
LOAD DATA LOCAL INFILE 'D:/Projetos/Intuitive_care/API/raw/1T2023.csv'
INTO TABLE demonstracoes_contabeis
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@data, @reg_ans, @cd_conta_contabil, @descricao, @vl_saldo_inicial, @vl_saldo_final)
SET 
    data = IF(@data = '', NULL, @data),
    reg_ans = CAST(@reg_ans AS UNSIGNED),
    cd_conta_contabil = CAST(@cd_conta_contabil AS UNSIGNED),
    descricao = CONVERT(@descricao USING utf8mb4),
    vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
    vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');
    
LOAD DATA LOCAL INFILE 'D:/Projetos/Intuitive_care/API/raw/2T2023.csv'
INTO TABLE demonstracoes_contabeis
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@data, @reg_ans, @cd_conta_contabil, @descricao, @vl_saldo_inicial, @vl_saldo_final)
SET 
    data = IF(@data = '', NULL, @data),
    reg_ans = CAST(@reg_ans AS UNSIGNED),
    cd_conta_contabil = CAST(@cd_conta_contabil AS UNSIGNED),
    descricao = CONVERT(@descricao USING utf8mb4),
    vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
    vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA LOCAL INFILE 'D:/Projetos/Intuitive_care/API/raw/3T2023.csv'
INTO TABLE demonstracoes_contabeis
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@data, @reg_ans, @cd_conta_contabil, @descricao, @vl_saldo_inicial, @vl_saldo_final)
SET 
    data = IF(@data = '', NULL, @data),
    reg_ans = CAST(@reg_ans AS UNSIGNED),
    cd_conta_contabil = CAST(@cd_conta_contabil AS UNSIGNED),
    descricao = CONVERT(@descricao USING utf8mb4),
    vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
    vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA LOCAL INFILE 'D:/Projetos/Intuitive_care/API/raw/4T2023.csv'
INTO TABLE demonstracoes_contabeis
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@data, @reg_ans, @cd_conta_contabil, @descricao, @vl_saldo_inicial, @vl_saldo_final)
SET 
    data = IF(@data = '', NULL, @data),
    reg_ans = CAST(@reg_ans AS UNSIGNED),
    cd_conta_contabil = CAST(@cd_conta_contabil AS UNSIGNED),
    descricao = CONVERT(@descricao USING utf8mb4),
    vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
    vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA LOCAL INFILE 'D:/Projetos/Intuitive_care/API/raw/1T2024.csv'
INTO TABLE demonstracoes_contabeis
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@data, @reg_ans, @cd_conta_contabil, @descricao, @vl_saldo_inicial, @vl_saldo_final)
SET 
    data = IF(@data = '', NULL, @data),
    reg_ans = CAST(@reg_ans AS UNSIGNED),
    cd_conta_contabil = CAST(@cd_conta_contabil AS UNSIGNED),
    descricao = CONVERT(@descricao USING utf8mb4),
    vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
    vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA LOCAL INFILE 'D:/Projetos/Intuitive_care/API/raw/2T2024.csv'
INTO TABLE demonstracoes_contabeis
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@data, @reg_ans, @cd_conta_contabil, @descricao, @vl_saldo_inicial, @vl_saldo_final)
SET 
    data = IF(@data = '', NULL, @data),
    reg_ans = CAST(@reg_ans AS UNSIGNED),
    cd_conta_contabil = CAST(@cd_conta_contabil AS UNSIGNED),
    descricao = CONVERT(@descricao USING utf8mb4),
    vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
    vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');

LOAD DATA LOCAL INFILE 'D:/Projetos/Intuitive_care/API/raw/3T2024.csv'
INTO TABLE demonstracoes_contabeis
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@data, @reg_ans, @cd_conta_contabil, @descricao, @vl_saldo_inicial, @vl_saldo_final)
SET 
    data = IF(@data = '', NULL, @data),
    reg_ans = CAST(@reg_ans AS UNSIGNED),
    cd_conta_contabil = CAST(@cd_conta_contabil AS UNSIGNED),
    descricao = CONVERT(@descricao USING utf8mb4),
    vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
    vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');
    
LOAD DATA LOCAL INFILE 'D:/Projetos/Intuitive_care/API/raw/4T2024.csv'
INTO TABLE demonstracoes_contabeis
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@data, @reg_ans, @cd_conta_contabil, @descricao, @vl_saldo_inicial, @vl_saldo_final)
SET 
    data = IF(@data = '', NULL, @data),
    reg_ans = CAST(@reg_ans AS UNSIGNED),
    cd_conta_contabil = CAST(@cd_conta_contabil AS UNSIGNED),
    descricao = CONVERT(@descricao USING utf8mb4),
    vl_saldo_inicial = REPLACE(@vl_saldo_inicial, ',', '.'),
    vl_saldo_final = REPLACE(@vl_saldo_final, ',', '.');
    

LOAD DATA LOCAL INFILE 'D:/Projetos/Intuitive_care/API/raw/Relatorio_cadop.csv'
INTO TABLE operadoras_ativas
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(registro_ans, cnpj, razao_social, nome_fantasia, modalidade, logradouro, numero, complemento, 
 bairro, cidade, uf, cep, ddd, telefone, fax, endereco_eletronico, representante, 
 cargo_representante, regiao_comercializacao, @data_registro)
SET 
	data_registro_ans = STR_TO_DATE(@data_registro, '%Y-%m-%d'),
	ddd = NULLIF(@ddd, ''),
    regiao_comercializacao = NULLIF(@regiao_comercializacao, '');



