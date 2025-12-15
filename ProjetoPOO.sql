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