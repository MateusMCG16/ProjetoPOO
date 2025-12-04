package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Contato;
import dao.Conexao;

public class ContatoDAO {

    public void salvar(Contato contato) {
        String sql = "INSERT INTO Contato (nome, email, telefone, id_categoria) VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, contato.getNome());
            pstm.setString(2, contato.getEmail());
            pstm.setString(3, contato.getTelefone());
            pstm.setInt(4, contato.getCategoria().getIdCategoria());
            
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Contato salvo com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar contato: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 2. ATUALIZAR (Update)
    public void atualizar(Contato contato) {
        String sql = "UPDATE Contato SET nome = ?, email = ?, telefone = ?, id_categoria = ? WHERE id_contato = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, contato.getNome());
            pstm.setString(2, contato.getEmail());
            pstm.setString(3, contato.getTelefone());
            pstm.setInt(4, contato.getCategoria().getIdCategoria());
            pstm.setInt(5, contato.getIdContato());
            
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar contato: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 3. EXCLUIR (Delete)
    public void excluir(int idContato) {
        String sql = "DELETE FROM Contato WHERE id_contato = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, idContato);
            
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Contato excluído com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir contato: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contato> listar() {
        String sql = "SELECT c.*, cat.nome as nome_categoria FROM Contato c " +
                     "INNER JOIN Categoria cat ON c.id_categoria = cat.id_categoria";
        
        List<Contato> contatos = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();
            
            while (rset.next()) {
                Contato contato = new Contato();
                Categoria categoria = new Categoria();
                
                contato.setIdContato(rset.getInt("id_contato"));
                contato.setNome(rset.getString("nome"));
                contato.setEmail(rset.getString("email"));
                contato.setTelefone(rset.getString("telefone"));
                
                categoria.setIdCategoria(rset.getInt("id_categoria"));
                categoria.setNome(rset.getString("nome_categoria"));
                
                contato.setCategoria(categoria);
                
                contatos.add(contato);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) rset.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return contatos;
    }
    
    public List<Contato> pesquisar(String termo) {
        String sql = "SELECT c.*, cat.nome as nome_categoria FROM Contato c " +
                     "INNER JOIN Categoria cat ON c.id_categoria = cat.id_categoria " +
                     "WHERE c.nome LIKE ?";
        
        List<Contato> contatos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + termo + "%"); // O % permite buscar partes do texto
            rset = pstm.executeQuery();
            
            while (rset.next()) {
                Contato contato = new Contato();
                Categoria categoria = new Categoria();
                
                contato.setIdContato(rset.getInt("id_contato"));
                contato.setNome(rset.getString("nome"));
                contato.setEmail(rset.getString("email"));
                contato.setTelefone(rset.getString("telefone"));
                
                categoria.setIdCategoria(rset.getInt("id_categoria"));
                categoria.setNome(rset.getString("nome_categoria"));
                
                contato.setCategoria(categoria);
                contatos.add(contato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) rset.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contatos;
    }
}