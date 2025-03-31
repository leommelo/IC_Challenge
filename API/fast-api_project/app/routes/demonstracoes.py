from fastapi import APIRouter, Depends, HTTPException, Query
from sqlalchemy.orm import Session
from typing import List
from .. import models, schemas, database
from sqlalchemy import func, text
from datetime import datetime, timedelta

router = APIRouter()

@router.get("/demonstracoes/maiores-despesas/trimestre", response_model=List[schemas.OperadoraDespesaResponse])
def maiores_despesas_trimestre(db: Session = Depends(database.get_db)):
    """
    Retorna as 10 operadoras com maiores despesas em 
    'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
    no último trimestre
    """
    # Aqui você tem duas opções:
    
    # Opção 1: Utilizando os últimos 3 meses a partir da data atual
    tres_meses_atras = datetime.now().date() - timedelta(days=90)
    
    # SQL direto convertido para SQLAlchemy
    resultado = db.query(
        models.OperadoraAtiva.razao_social,
        models.OperadoraAtiva.nome_fantasia,
        func.sum(models.DemonstracaoContabil.vl_saldo_final).label("total_despesas")
    ).join(
        models.DemonstracaoContabil, 
        models.DemonstracaoContabil.reg_ans == models.OperadoraAtiva.registro_ans
    ).filter(
        models.DemonstracaoContabil.descricao == "EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR ",
        models.DemonstracaoContabil.data >= tres_meses_atras
    ).group_by(
        models.OperadoraAtiva.razao_social, 
        models.OperadoraAtiva.nome_fantasia
    ).order_by(
        text("total_despesas DESC")
    ).limit(10).all()
    
    return resultado

@router.get("/demonstracoes/maiores-despesas/sete-meses", response_model=List[schemas.OperadoraDespesaResponse])
def maiores_despesas_trimestre(db: Session = Depends(database.get_db)):
    """
    Retorna as 10 operadoras com maiores despesas em 
    'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
    no último semestre
    """

    tres_meses_atras = datetime.now().date() - timedelta(days=210)
    
    # SQL direto convertido para SQLAlchemy
    resultado = db.query(
        models.OperadoraAtiva.razao_social,
        models.OperadoraAtiva.nome_fantasia,
        func.sum(models.DemonstracaoContabil.vl_saldo_final).label("total_despesas")
    ).join(
        models.DemonstracaoContabil, 
        models.DemonstracaoContabil.reg_ans == models.OperadoraAtiva.registro_ans
    ).filter(
        models.DemonstracaoContabil.descricao == "EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR ",
        models.DemonstracaoContabil.data >= tres_meses_atras
    ).group_by(
        models.OperadoraAtiva.razao_social, 
        models.OperadoraAtiva.nome_fantasia
    ).order_by(
        text("total_despesas DESC")
    ).limit(10).all()
    
    return resultado

@router.get("/demonstracoes/maiores-despesas/ano", response_model=List[schemas.OperadoraDespesaResponse])
def maiores_despesas_ano(db: Session = Depends(database.get_db)):
    """
    Retorna as 10 operadoras com maiores despesas em 
    'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
    no último ano
    """
    um_ano_atras = datetime.now().date() - timedelta(days=365)
    
    resultado = db.query(
        models.OperadoraAtiva.razao_social,
        models.OperadoraAtiva.nome_fantasia,
        func.sum(models.DemonstracaoContabil.vl_saldo_final).label("total_despesas")
    ).join(
        models.DemonstracaoContabil, 
        models.DemonstracaoContabil.reg_ans == models.OperadoraAtiva.registro_ans
    ).filter(
        models.DemonstracaoContabil.descricao == "EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR ",
        models.DemonstracaoContabil.data >= um_ano_atras
    ).group_by(
        models.OperadoraAtiva.razao_social, 
        models.OperadoraAtiva.nome_fantasia
    ).order_by(
        text("total_despesas DESC")
    ).limit(10).all()
    
    return resultado