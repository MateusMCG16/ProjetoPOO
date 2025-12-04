package controller;

import dao.ContatoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Contato;

public class ContatoController {

    private ContatoDAO dao;

    public ContatoController() {
        this.dao = new ContatoDAO();
    }

    public boolean salvar(String nome, String email, String telefone, Categoria categoria) {
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo Nome é obrigatório!");
            return false;
        }
        
        if (categoria == null) {
            JOptionPane.showMessageDialog(null, "Selecione uma categoria!");
            return false;
        }

        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setTelefone(telefone);
        contato.setCategoria(categoria);

        try {
            dao.salvar(contato);
            JOptionPane.showMessageDialog(null, "Contato salvo com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar contato: " + e.getMessage());
            return false;
        }
    }

    public List<Contato> listar() {
        try {
            return dao.listar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar contatos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Contato> pesquisar(String termo) {
        try {
            return dao.pesquisar(termo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}