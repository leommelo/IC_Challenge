# API Operadoras de Saúde

Este projeto é uma aplicação web full-stack desenvolvida com FastAPI (backend) e Vue.js (frontend) para gerenciamento de operadoras de saúde.

## Estrutura do Projeto

```
API/
├── fast-api_project/     # Backend em FastAPI
├── frontend/            # Frontend em Vue.js
├── sql/                 # Scripts e arquivos SQL
└── raw/                 # Dados brutos
```

## Tecnologias Utilizadas

### Backend
- Python 3.8+
- FastAPI
- SQLAlchemy
- PostgreSQL

### Frontend
- Vue.js

## Pré-requisitos

### Backend
- Python 3.8 ou superior
- pip (gerenciador de pacotes Python)
- PostgreSQL instalado e configurado

### Frontend
- Node.js 14+ ou superior
- npm ou yarn

## Configuração do Ambiente

### Backend

1. Crie um ambiente virtual Python:
```bash
python -m venv venv
source venv/bin/activate  # Linux/Mac
venv\Scripts\activate     # Windows
```

2. Instale as dependências:
```bash
cd fast-api_project
pip install -r requirements.txt
```

3. Configure as variáveis de ambiente:
```bash
# Crie um arquivo .env com as seguintes variáveis
DATABASE_URL=postgresql://usuario:senha@localhost:5432/nome_do_banco
```

### Frontend

1. Instale as dependências:
```bash
cd frontend
npm install
```

## Executando o Projeto

### Backend

1. Inicie o servidor FastAPI:
```bash
cd fast-api_project
uvicorn main:app --reload
```

O servidor estará disponível em `http://localhost:8000`

### Frontend

1. Inicie o servidor de desenvolvimento:
```bash
cd frontend
npm run serve
```

A aplicação estará disponível em `http://localhost:8080`

## Banco de Dados

1. Crie o banco de dados PostgreSQL:
```bash
createdb nome_do_banco
```

2. Execute os scripts SQL:
```bash
psql -d nome_do_banco -f sql/init.sql
```

## Documentação da API

A documentação da API está disponível em:
- Swagger UI: `http://localhost:8000/docs`
- ReDoc: `http://localhost:8000/redoc`

## Coleção Postman

Uma coleção do Postman está disponível no arquivo `API Operadoras de Saúde.postman_collection.json` para facilitar os testes da API.

## Estrutura de Arquivos

- `fast-api_project/`: Contém o código fonte do backend em FastAPI
- `frontend/`: Contém o código fonte do frontend em Vue.js
- `sql/`: Scripts SQL para criação e manipulação do banco de dados
- `raw/`: Dados brutos utilizados na aplicação

## Desenvolvimento

### Backend
- A API segue a arquitetura REST
- Utiliza FastAPI para roteamento e validação de dados
- Implementa autenticação e autorização
- Segue as melhores práticas de desenvolvimento Python

### Frontend
- Desenvolvido com Vue.js 3
- Utiliza TailwindCSS para estilização
- Implementa componentes reutilizáveis
- Segue as melhores práticas de desenvolvimento Vue.js

