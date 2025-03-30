-- Criar o banco de dados (caso ainda não exista)
CREATE DATABASE IF NOT EXISTS ans_data;
USE ans_data;

-- Criar a tabela unificada para demonstrativos contábeis
CREATE TABLE IF NOT EXISTS demonstracoes_contabeis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ano YEAR NOT NULL,
    trimestre TINYINT NOT NULL,
    data DATE NOT NULL,
    reg_ans INT NOT NULL,
    cd_conta_contabil BIGINT NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    vl_saldo_inicial DECIMAL(15,2) NOT NULL,
    vl_saldo_final DECIMAL(15,2) NOT NULL
);

-- Criar a tabela de operadoras ativas
CREATE TABLE IF NOT EXISTS operadoras_ativas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    registro_ans INT NOT NULL,
    cnpj BIGINT NOT NULL,
    razao_social VARCHAR(255) NOT NULL,
    nome_fantasia VARCHAR(255),
    modalidade VARCHAR(100) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf CHAR(2) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    ddd INT NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    fax VARCHAR(20),
    endereco_eletronico VARCHAR(255),
    representante VARCHAR(255),
    cargo_representante VARCHAR(100),
    regiao_comercializacao INT NOT NULL,
    data_registro_ans DATE NOT NULL
);
