from sqlalchemy import Column, Integer, String, Date, Text, DECIMAL, BigInteger, ForeignKey
from sqlalchemy.orm import relationship
from .database import Base

class OperadoraAtiva(Base):
    __tablename__ = "operadoras_ativas"

    id = Column(Integer, primary_key=True, index=True, autoincrement=True)
    registro_ans = Column(String(20), index=True, unique=True)
    cnpj = Column(String(20), index=True)
    razao_social = Column(String(255))
    nome_fantasia = Column(String(255))
    modalidade = Column(String(100))
    logradouro = Column(String(255))
    numero = Column(String(20))
    complemento = Column(String(100))
    bairro = Column(String(100))
    cidade = Column(String(100))
    uf = Column(String(2))
    cep = Column(String(10))
    ddd = Column(String(5))
    telefone = Column(String(20))
    fax = Column(String(20))
    endereco_eletronico = Column(String(255))
    representante = Column(String(255))
    cargo_representante = Column(String(100))
    regiao_comercializacao = Column(String(100))
    data_registro_ans = Column(Date)
    
    # Relacionamento com demonstrações contábeis
    demonstracoes = relationship("DemonstracaoContabil", back_populates="operadora")


class DemonstracaoContabil(Base):
    __tablename__ = "demonstracoes_contabeis"
    
    id = Column(Integer, primary_key=True, index=True, autoincrement=True)
    data = Column(Date, nullable=False, index=True)
    reg_ans = Column(Integer, ForeignKey("operadoras_ativas.registro_ans"), index=True)
    cd_conta_contabil = Column(BigInteger, nullable=False)
    descricao = Column(String(255), nullable=False)
    vl_saldo_inicial = Column(DECIMAL(15, 2))
    vl_saldo_final = Column(DECIMAL(15, 2))
    
    # Relacionamento com operadoras
    operadora = relationship("OperadoraAtiva", back_populates="demonstracoes")