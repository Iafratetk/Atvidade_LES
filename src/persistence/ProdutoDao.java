package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Produto;

public class ProdutoDao {

	private Connection c;
	
	public ProdutoDao() {
		GenericDAO gDao = new GenericDAO();
		c=gDao.getConnection();
	}
	
	public List<Produto> pesquisarTodos () throws SQLException{
		System.out.println("entrou no pesquisar todos produtos");
		List<Produto> listaProdutos = new ArrayList<Produto>();
		String sql = "SELECT id, nome, quantidade, valor FROM produto ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
		    Produto prod = new Produto();
			prod.setId(rs.getInt("id"));
			prod.setNome(rs.getString("nome"));
			prod.setQuantidade(rs.getInt("quantidade"));
			prod.setValor(rs.getDouble("valor"));
			listaProdutos.add(prod);
		}
		rs.close();
		ps.close();
		return listaProdutos;
	}
	public void insereProdutos(Produto prod) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("entrou no insere produtos");
		String sql = "INSERT INTO produto (nome, quantidade, valor ) VALUES (?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, prod.getNome());
		ps.setInt(2, prod.getQuantidade());
		ps.setDouble(3, prod.getValor());
		ps.execute();
		ps.close();
	}
	public void removerProdutos(int id) throws SQLException {
		String sql = "DELETE produto WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		ps.close();
	}
	public void atualizaProdutos(Produto prod) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE produto SET nome = ?, quantidade = ?, valor = ?   WHERE id = ? ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, prod.getNome());
		ps.setInt(2, prod.getQuantidade());
		ps.setDouble(3, prod.getValor());
		ps.setInt(4, prod.getId());
		ps.execute();
		ps.close();
	}
}
