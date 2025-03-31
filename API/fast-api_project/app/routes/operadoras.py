from fastapi import APIRouter, Depends, HTTPException, Query
from sqlalchemy.orm import Session
from typing import List
from .. import models, schemas, database
from sqlalchemy import or_

router = APIRouter()

@router.get("/operadoras/{registro_ans}", response_model=schemas.OperadoraResponse)
def obter_operadora(registro_ans: str, db: Session = Depends(database.get_db)):
    """
    Obtém os detalhes de uma operadora específica pelo Registro ANS
    """
    operadora = db.query(models.OperadoraAtiva).filter(
        models.OperadoraAtiva.registro_ans == registro_ans
    ).first()
    
    if operadora is None:
        raise HTTPException(status_code=404, detail="Operadora não encontrada")
    
    return {
        "id": operadora.id,
        "modalidade": operadora.modalidade,
        "registro_ans": str(operadora.registro_ans),
        "cnpj": str(operadora.cnpj),
        "razao_social": operadora.razao_social,
        "nome_fantasia": operadora.nome_fantasia,
        "cidade": operadora.cidade,
        "uf": operadora.uf,
        "logradouro": operadora.logradouro,
        "numero": operadora.numero,
        "complemento": operadora.complemento,
        "bairro": operadora.bairro,
        "cep": operadora.cep,
        "ddd": operadora.ddd,
        "telefone": operadora.telefone,
        "fax": operadora.fax,
        "endereco_eletronico": operadora.endereco_eletronico,
        "representante": operadora.representante,
        "cargo_representante": operadora.cargo_representante,
        "regiao_comercializacao": operadora.regiao_comercializacao,
        "data_registro_ans": operadora.data_registro_ans,    
        }