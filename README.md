# SAC - Sistema de Agenda de Contatos

**Projeto Acadêmico** - Disciplina de Programação Orientada a Objetos

O **SAC** é uma aplicação Desktop desenvolvida em Java para o gerenciamento de contatos pessoais e profissionais. O sistema utiliza a biblioteca Swing para a interface gráfica e MySQL para persistência de dados, implementando o padrão de arquitetura MVC (Model-View-Controller).

---

## Funcionalidades

O sistema contempla as seguintes funcionalidades:

- **Gerenciamento de Categorias**: Cadastro e listagem de categorias para organização dos contatos.
- **Gerenciamento de Contatos**: Cadastro, edição e associação de contatos a categorias.
- **Consultas**: Listagem e visualização de informações detalhadas dos registros.
- **Interface Gráfica**: Navegação via menu e formulários para manipulação de dados.

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)

- **Linguagem**: Java
- **Interface Gráfica**: Java Swing
- **Banco de Dados**: MySQL
- **Conectividade**: JDBC
- **IDE**: Eclipse IDE for Java Developers

## Estrutura do Projeto

O projeto adota a arquitetura MVC para separação de responsabilidades:

```
src/
├── controller/   # Regras de negócio e controle de fluxo
│   ├── CategoriaController.java
│   └── ContatoController.java
├── dao/          # Camada de acesso a dados (Data Access Object)
│   ├── CategoriaDAO.java
│   ├── ContatoDAO.java
│   └── Conexao.java
├── front/        # Interface gráfica (View)
│   ├── TelaPrincipal.java
│   ├── TelaContato.java
│   ├── TelaCategoria.java
│   └── TelaListagem.java
├── modelo/       # Entidades de domínio (Model)
│   ├── Contato.java
│   └── Categoria.java
└── module-info.java
```

## Configuração do Banco de Dados

Para a execução do sistema, é necessário configurar o banco de dados MySQL. Utilize o script abaixo para criação da base de dados e tabelas:

```sql
CREATE DATABASE IF NOT EXISTS SAC;
USE SAC;

-- tabela categoria
CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- tabela contato
CREATE TABLE Contato (
    id_contato INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150),
    id_categoria INT NOT NULL, -- foreign key
    
    -- relações
    CONSTRAINT fk_contato_categoria 
    FOREIGN KEY (id_categoria) 
    REFERENCES Categoria(id_categoria)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);
```

**Observação**: As credenciais de conexão estão definidas na classe `src/dao/Conexao.java`. Verifique e ajuste as constantes `USUARIO` e `SENHA` conforme a configuração do seu ambiente local.

## Instruções de Execução

1. Clone o repositório para o seu ambiente local.
2. Importe o projeto no Eclipse IDE for Java Developers (Recomendado)
3. Adicione o driver JDBC do MySQL ao `classpath` do projeto.
4. Execute o script SQL de configuração do banco de dados.
5. Execute a classe principal `src/front/TelaPrincipal.java`.

---
Desenvolvido por [Mateus](https://github.com/MateusMCG16)
