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

    public boolean salvar(int idContato, String nome, String email, String telefone, Categoria categoria) {
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo Nome é obrigatório!");
            return false;
        }
        
        if (categoria == null) {
            JOptionPane.showMessageDialog(null, "Selecione uma categoria!");
            return false;
        }

        Contato contato = new Contato();
        contato.setIdContato(idContato);
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setTelefone(telefone);
        contato.setCategoria(categoria);

        try {
            if (idContato == 0) {
                dao.salvar(contato);
                JOptionPane.showMessageDialog(null, "Contato salvo com sucesso!");
            } else {
                dao.atualizar(contato);
                JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso!");
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar/atualizar contato: " + e.getMessage());
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

    public boolean excluir(int idContato) {
        try {
            dao.excluir(idContato);
            JOptionPane.showMessageDialog(null, "Contato excluído com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir contato: " + e.getMessage());
            return false;
        }
    }
}