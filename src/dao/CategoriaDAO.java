package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;

public class CategoriaDAO {

    public void salvar(Categoria categoria) throws Exception {
        String sql = "INSERT INTO Categoria (nome) VALUES (?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, categoria.getNome());
            pstm.execute();
            
        } catch (Exception e) {
            throw new Exception("Erro ao salvar categoria: " + e.getMessage());
        } finally {
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        }
    }

    public List<Categoria> listar() throws Exception {
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
            throw new Exception("Erro ao listar categorias: " + e.getMessage());
        } finally {
            if (rset != null) rset.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        }
        
        return categorias;
    }
}