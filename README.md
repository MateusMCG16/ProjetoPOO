# Sistema de Agenda de Contatos (SAC)

Projeto simples de agenda de contatos utilizando Java Swing, JDBC e MySQL, seguindo a arquitetura **MVC (Model-View-Controller)** e o padrão **DAO (Data Access Object)**.

## Funcionalidades

*   **Login**: Acesso restrito via usuário e senha.
*   **CRUD Completo**:
    *   **C**adastrar Contatos e Categorias.
    *   **R** listar e Pesquisar Contatos.
    *   **U**pdate (Editar) Contatos existentes.
    *   **D**elete (Excluir) Contatos.

## Como Executar

1.  **Banco de Dados**: Crie o banco `SAC` no MySQL usando o script abaixo.
2.  **Configuração**: Verifique se a senha do banco em `src/dao/Conexao.java` corresponde à sua.
3.  **Execução**: Rode o arquivo `src/front/TelaLogin.java`.
4.  **Login**:
    *   **Usuário**: `admin`
    *   **Senha**: `admin`

## Script SQL

```sql
CREATE DATABASE IF NOT EXISTS SAC;
USE SAC;

CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Contato (
    id_contato INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150),
    telefone VARCHAR(20),
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);
```

## Estrutura MVC

*   **Model**: Classes `Contato` e `Categoria`.
*   **View (Front)**: Telas (`TelaLogin`, `TelaPrincipal`, `TelaListagem`, etc).
*   **Controller**: `ContatoController` e `CategoriaController` (Regras de negócio).
*   **DAO**: `ContatoDAO` e `CategoriaDAO` (Acesso ao banco).
