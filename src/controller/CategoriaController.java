package controller;

import dao.CategoriaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Categoria;

public class CategoriaController {

    private CategoriaDAO dao;

    public CategoriaController() {
        this.dao = new CategoriaDAO();
    }

    public boolean salvar(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome da categoria é obrigatório!");
            return false;
        }

        Categoria categoria = new Categoria();
        categoria.setNome(nome);

        try {
            dao.salvar(categoria);
            JOptionPane.showMessageDialog(null, "Categoria salva com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar categoria: " + e.getMessage());
            return false;
        }
    }

    public List<Categoria> listar() {
        try {
            return dao.listar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar categorias: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}