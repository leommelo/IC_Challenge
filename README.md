# IC Challenge

Este repositório contém dois projetos principais relacionados ao processamento e gerenciamento de dados de operadoras de saúde:

1. **PdfToCsv**: Ferramenta Java para conversão de PDFs em CSV
2. **API**: Sistema web full-stack para gerenciamento de operadoras de saúde

## Estrutura do Projeto

```
Intuitive_care/
├── PdfToCsvJava/        # Ferramenta de conversão PDF para CSV
│   ├── src/             # Código fonte Java
│   ├── input/           # Arquivos PDF de entrada
│   └── output/          # Arquivos CSV gerados
│
└── API/                 # Sistema web de gerenciamento
    ├── fast-api_project/  # Backend FastAPI
    ├── frontend/         # Frontend Vue.js
    ├── sql/             # Scripts SQL
    └── raw/             # Dados brutos
```

## Fluxo de Trabalho

1. **Processamento de PDFs (PdfToCsvJava)**
   - Recebe arquivos PDF de procedimentos médicos
   - Converte para formato CSV estruturado
   - Gera arquivos na pasta `output/`

2. **Gerenciamento via API**
   - Importa os dados CSV processados
   - Fornece interface web para visualização e gerenciamento
   - Permite consultas e atualizações dos dados

## Documentação Detalhada

Cada projeto possui sua própria documentação detalhada:

- [Documentação do PdfToCsvJava](PdfToCsvJava/README.md)
- [Documentação da API](API/README.md)

## Pré-requisitos Gerais

- Java JDK 17 (para PdfToCsvJava)
- Python 3.8+ (para API)
- Node.js 14+ (para API)
- PostgreSQL (para API)
- Maven (para PdfToCsvJava)

## Como Usar

1. **Processamento de PDFs**
   - Coloque os arquivos PDF na pasta `PdfToCsvJava/input/`
   - Execute o programa Java
   - Os arquivos CSV serão gerados em `PdfToCsvJava/output/`

2. **Gerenciamento via API**
   - Configure o ambiente conforme documentação da API
   - Inicie o backend FastAPI
   - Inicie o frontend Vue.js
   - Acesse a interface web para gerenciar os dados

## Desenvolvimento

### PdfToCsvJava
- Desenvolvido em Java 17
- Utiliza Apache PDFBox para processamento de PDFs
- Gera arquivos CSV estruturados

### API
- Backend em FastAPI (Python)
- Frontend em Vue.js
- Banco de dados PostgreSQL
- Interface web responsiva com TailwindCSS
