# PdfToCsv

Este projeto é uma aplicação Java que converte arquivos PDF em formato CSV, utilizando a biblioteca Apache PDFBox para processamento de PDFs.

## Pré-requisitos

- Java JDK 17 ou superior
- Maven 3.6 ou superior
- Sistema operacional: Windows/Linux/MacOS

## Tecnologias Utilizadas

- Java 17
- Maven
- Apache PDFBox 2.0.29
- Apache Commons Compress 1.23.0

## Estrutura do Projeto

```
PdfToCsfJava/
├── src/
│   ├── main/         # Código fonte principal
│   └── test/         # Testes
├── input/            # Diretório para arquivos de entrada
├── output/           # Diretório para arquivos de saída
└── target/           # Diretório de build
```

## Preparação do Ambiente

1. Instale o Java JDK 17:
   - Baixe e instale o JDK 17 do site oficial da Oracle ou use OpenJDK
   - Configure a variável de ambiente JAVA_HOME
   - Adicione o bin do Java ao PATH do sistema

2. Instale o Maven:
   - Baixe o Maven do site oficial
   - Configure a variável de ambiente MAVEN_HOME
   - Adicione o bin do Maven ao PATH do sistema

3. Clone o repositório:
   ```bash
   git clone [URL_DO_REPOSITÓRIO]
   cd PdfToCsfJava
   ```

## Compilação do Projeto

Para compilar o projeto, execute:

```bash
mvn clean package
```

O arquivo JAR será gerado em `target/PdfToCsv-1.0-SNAPSHOT.jar`

## Executando o Projeto

1. Coloque os arquivos PDF que deseja converter na pasta `input/`
2. Execute o programa usando o comando:

```bash
java -jar target/PdfToCsv-1.0-SNAPSHOT.jar
```

Os arquivos CSV gerados serão salvos na pasta `output/`

## Estrutura de Arquivos

- `input/`: Diretório onde devem ser colocados os arquivos PDF para processamento
- `output/`: Diretório onde serão gerados os arquivos CSV
- `grupos.txt`: Arquivo de configuração com informações sobre os grupos de processamento

## Dependências

O projeto utiliza as seguintes dependências principais:

- Apache PDFBox: Para processamento de arquivos PDF
- Apache Commons Compress: Para manipulação de arquivos compactados

## Suporte

Para suporte ou dúvidas, entre em contato com a equipe de desenvolvimento. 