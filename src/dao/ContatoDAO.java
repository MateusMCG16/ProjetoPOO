package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Contato;

public class ContatoDAO {

    public void salvar(Contato contato) throws Exception {
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
            
        } catch (Exception e) {
            throw new Exception("Erro ao salvar contato no banco: " + e.getMessage());
        } finally {
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        }
    }

    public void atualizar(Contato contato) throws Exception {
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
            
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar contato: " + e.getMessage());
        } finally {
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        }
    }

    public void excluir(int idContato) throws Exception {
        String sql = "DELETE FROM Contato WHERE id_contato = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, idContato);
            
            pstm.execute();
            
        } catch (Exception e) {
            throw new Exception("Erro ao excluir contato: " + e.getMessage());
        } finally {
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        }
    }

    public List<Contato> listar() throws Exception {
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
                Contato contato = montarContato(rset);
                contatos.add(contato);
            }
            
        } catch (Exception e) {
            throw new Exception("Erro ao listar contatos: " + e.getMessage());
        } finally {
            if (rset != null) rset.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        }
        
        return contatos;
    }
    
    public List<Contato> pesquisar(String termo) throws Exception {
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
            pstm.setString(1, "%" + termo + "%");
            rset = pstm.executeQuery();
            
            while (rset.next()) {
                Contato contato = montarContato(rset);
                contatos.add(contato);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao pesquisar contatos: " + e.getMessage());
        } finally {
            if (rset != null) rset.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        }
        return contatos;
    }

    private Contato montarContato(ResultSet rset) throws Exception {
        Contato contato = new Contato();
        Categoria categoria = new Categoria();
        
        contato.setIdContato(rset.getInt("id_contato"));
        contato.setNome(rset.getString("nome"));
        contato.setEmail(rset.getString("email"));
        contato.setTelefone(rset.getString("telefone"));
        
        categoria.setIdCategoria(rset.getInt("id_categoria"));
        categoria.setNome(rset.getString("nome_categoria"));
        
        contato.setCategoria(categoria);
        return contato;
    }
}