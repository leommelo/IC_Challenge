from pydantic import BaseModel
from typing import Optional, List
from datetime import date
from decimal import Decimal

class OperadoraBase(BaseModel):
    registro_ans: str
    cnpj: str
    razao_social: str
    nome_fantasia: Optional[str] = None
    modalidade: str
    
class OperadoraResponse(OperadoraBase):
    id: int
    logradouro: Optional[str] = None
    numero: Optional[str] = None
    complemento: Optional[str] = None
    bairro: Optional[str] = None
    cidade: Optional[str] = None
    uf: Optional[str] = None
    cep: Optional[str] = None
    ddd: Optional[str] = None
    telefone: Optional[str] = None
    fax: Optional[str] = None
    endereco_eletronico: Optional[str] = None
    representante: Optional[str] = None
    cargo_representante: Optional[str] = None
    regiao_comercializacao: Optional[str] = None
    data_registro_ans: Optional[date] = None

    class Config:
        orm_mode = True
        from_attributes = True

class DemonstracaoContabilBase(BaseModel):
    data: date
    reg_ans: int
    cd_conta_contabil: int
    descricao: str
    vl_saldo_inicial: Optional[Decimal] = None
    vl_saldo_final: Optional[Decimal] = None

class DemonstracaoContabilResponse(DemonstracaoContabilBase):
    id: int
    
    class Config:
        orm_mode = True

class OperadoraDespesaResponse(BaseModel):
    razao_social: str
    nome_fantasia: Optional[str] = None
    total_despesas: Decimal
    
    class Config:
        orm_mode = True