# 📞 SAC - Sistema de Agenda de Contatos

> **Projeto Acadêmico** - Trabalho desenvolvido para a disciplina de Programação Orientada a Objetos / Desenvolvimento de Software.

O **SAC** é uma aplicação Desktop desenvolvida em Java que permite o gerenciamento de contatos pessoais e profissionais, organizados por categorias. O sistema utiliza uma interface gráfica amigável (Swing) e persistência de dados em banco relacional (MySQL).

---

## 🚀 Funcionalidades

O sistema oferece as seguintes funcionalidades principais:

- **Gerenciamento de Categorias**:
  - Cadastro de novas categorias (ex: Família, Trabalho, Amigos).
- **Gerenciamento de Contatos**:
  - Cadastro de contatos com Nome, E-mail, Telefone e Categoria.
  - Associação de contatos a categorias existentes.
- **Consultas**:
  - Listagem geral de contatos.
  - Pesquisa de contatos.
- **Interface Gráfica**:
  - Menu principal intuitivo para navegação.
  - Formulários para entrada de dados.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: [Java](https://www.java.com/) (JDK 11 ou superior)
- **Interface Gráfica**: Java Swing (Biblioteca `java.desktop`)
- **Banco de Dados**: [MySQL](https://www.mysql.com/)
- **Conectividade**: JDBC (Java Database Connectivity)
- **IDE Recomendada**: VS Code, Eclipse ou IntelliJ IDEA

## 📂 Estrutura do Projeto

O projeto segue o padrão de arquitetura em camadas (MVC simplificado):

```
src/
├── dao/          # Data Access Objects - Camada de acesso ao banco de dados
│   ├── CategoriaDAO.java
│   ├── ContatoDAO.java
│   └── Conexao.java
├── front/        # Frontend - Telas e Interface Gráfica (Swing)
│   ├── TelaPrincipal.java
│   ├── TelaContato.java
│   ├── TelaCategoria.java
│   └── TelaListagem.java
├── modelo/       # Modelos - Classes POJO que representam as entidades
│   ├── Contato.java
│   └── Categoria.java
└── module-info.java
```

## ⚙️ Pré-requisitos e Configuração

Antes de executar o projeto, certifique-se de ter instalado:
1.  **Java JDK** (versão 11 ou superior).
2.  **MySQL Server**.
3.  **Driver JDBC do MySQL** (arquivo `.jar`) adicionado ao classpath do projeto.

### 🗄️ Configuração do Banco de Dados

1. Crie um banco de dados chamado `SAC` no seu MySQL.
2. Execute o seguinte script SQL para criar as tabelas necessárias:

```sql
CREATE DATABASE IF NOT EXISTS SAC;
USE SAC;

CREATE TABLE Categoria (
    idCategoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Contato (
    idContato INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20),
    idCategoria INT,
    FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria)
);
```

3. Verifique a classe `src/dao/Conexao.java` e ajuste as credenciais se necessário:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/SAC";
   private static final String USUARIO = "root"; // Seu usuário
   private static final String SENHA = "sua_senha"; // Sua senha
   ```

## ▶️ Como Executar

1.  Clone este repositório ou baixe os arquivos.
2.  Abra o projeto na sua IDE de preferência.
3.  Certifique-se de que o driver JDBC do MySQL está configurado nas bibliotecas do projeto.
4.  Execute a classe principal:
    -   `src/front/TelaPrincipal.java`

## 📝 Autor

Desenvolvido por **Mateus** como parte das atividades acadêmicas.

---
*Este projeto é para fins educacionais e demonstração de conceitos de CRUD com Java Swing e JDBC.*
