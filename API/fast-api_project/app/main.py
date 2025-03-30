from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from .routes import operadoras, demonstracoes
from . import models
from .database import engine

# Criar as tabelas no banco de dados (se não existirem)
models.Base.metadata.create_all(bind=engine)

app = FastAPI(
    title="API de Operadoras ANS",
    description="API para consulta de dados cadastrais das operadoras de planos de saúde ativas na ANS e suas demonstrações contábeis",
    version="1.0.0"
)

@app.get("/test-db")
def test_database_connection():
    try:
        # Tenta criar uma conexão
        conn = engine.connect()
        conn.close()
        return {"status": "success", "message": "Conexão com o banco de dados estabelecida com sucesso!"}
    except Exception as e:
        return {"status": "error", "message": f"Erro ao conectar ao banco de dados: {str(e)}"}

# Configurar CORS para permitir acesso do frontend Vue.js
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Em produção, especifique os domínios permitidos
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Incluir as rotas
app.include_router(operadoras.router, tags=["Operadoras"], prefix="/api")
app.include_router(demonstracoes.router, tags=["Demonstrações Contábeis"], prefix="/api")

@app.get("/")
def read_root():
    return {"message": "Bem-vindo à API de Operadoras da ANS"}