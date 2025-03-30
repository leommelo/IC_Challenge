from fastapi import APIRouter, Depends, HTTPException, Query
from sqlalchemy.orm import Session
from typing import List
from .. import models, schemas, database
from sqlalchemy import or_

router = APIRouter()

@router.get("/operadoras/", response_model=List[schemas.OperadoraResponse])
def buscar_operadoras(
    termo: str = Query(None, description="Termo de busca para encontrar operadoras"),
    skip: int = 0, 
    limit: int = 100,
    db: Session = Depends(database.get_db)
):
    """
    Busca textual na lista de cadastros de operadoras.
    O termo de busca será aplicado em:
    - Registro ANS
    - CNPJ
    - Razão Social
    - Nome Fantasia
    """
    if not termo:
        # Se não houver termo de busca, retorna todas as operadoras com paginação
        return db.query(models.OperadoraAtiva).offset(skip).limit(limit).all()
    
    # Busca por diferentes campos com o operador OR
    resultados = db.query(models.OperadoraAtiva).filter(
        or_(
            models.OperadoraAtiva.registro_ans.ilike(f"%{termo}%"),
            models.OperadoraAtiva.cnpj.ilike(f"%{termo}%"),
            models.OperadoraAtiva.razao_social.ilike(f"%{termo}%"),
            models.OperadoraAtiva.nome_fantasia.ilike(f"%{termo}%"),
            models.OperadoraAtiva.cidade.ilike(f"%{termo}%"),
            models.OperadoraAtiva.uf.ilike(f"%{termo}%")
        )
    ).offset(skip).limit(limit).all()
    
    return resultados

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
    
    return operadora