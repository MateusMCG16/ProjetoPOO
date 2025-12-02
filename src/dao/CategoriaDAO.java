package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Categoria;
import pacote.Conexao;

public class CategoriaDAO {

    public void salvar(Categoria categoria) {
        String sql = "INSERT INTO Categoria (nome) VALUES (?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, categoria.getNome());
            
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Categoria salva com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar categoria: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Categoria> listar() {
        String sql = "SELECT * FROM Categoria";
        
        List<Categoria> categorias = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();
            
            while (rset.next()) {
                Categoria categoria = new Categoria();
                
                categoria.setIdCategoria(rset.getInt("id_categoria"));
                
                categoria.setNome(rset.getString("nome"));
                
                categorias.add(categoria);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) rset.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return categorias;
    }
}