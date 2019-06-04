package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.PedidoProduto;



public class PedidoProdutoDao {
	
	
		Connection c;
		
		public PedidoProdutoDao() {
			GenericDAO gDao = new GenericDAO();
			c = gDao.getConnection();
		}
		
		public List<PedidoProduto> pesquisarTodosPedidosProdutos () throws SQLException{
			System.out.println("entrou no pesquisar todos PedidosProdutos");
			List<PedidoProduto> listaPedidosProdutos = new ArrayList<PedidoProduto>();
			String sql = "SELECT id_pedido_produto, id_pedido, id_produto, valor_produto, quantidade FROM pedido_produto";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    PedidoProduto pedProd = new PedidoProduto();
			    pedProd.setId(rs.getInt("id_pedido_produto"));
				pedProd.setIdPedido(rs.getInt("id_pedido"));
				pedProd.setIdProduto(rs.getInt("id_produto"));
				pedProd.setValorProduto(rs.getDouble("valor_produto"));
				pedProd.setQuantidade(rs.getInt("quantidade"));
				listaPedidosProdutos.add(pedProd);
			}
			rs.close();
			ps.close();
			return listaPedidosProdutos;
		}
		
		public void inserePedidoProduto(PedidoProduto pedProd) throws SQLException {
			// TODO Auto-generated method stub
			System.out.println("entrou no insere Pedido Produto");
			String sql = "INSERT INTO pedido_produto(id_pedido,id_produto,valor_produto, quantidade) VALUES (?,?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, pedProd.getIdPedido());
			ps.setInt(2, pedProd.getIdProduto());
			ps.setDouble(3, pedProd.getValorProduto());
			ps.setInt(4, pedProd.getQuantidade());
			ps.execute();
			ps.close();
		}
		
		public void removerPedidoProduto(int id) throws SQLException {
				// TODO Auto-generated method stub
				String sql = "DELETE pedido_produto WHERE id_pedido_produto = ?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setInt(1, id);
				ps.execute();
				ps.close();
		}
		public void atualizaPedidoProdutos(PedidoProduto pedProd) throws SQLException {
			// TODO Auto-generated method stub
			String sql = "UPDATE pedido_produto SET id_pedido = ?, id_produto = ?, valor_produto = ?, quantidade = ?   WHERE id = ? ";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, pedProd.getIdPedido());
			ps.setInt(2, pedProd.getIdProduto());
			ps.setDouble(3, pedProd.getValorProduto());
			ps.setInt(4, pedProd.getQuantidade());
			ps.setInt(5, pedProd.getId());
			ps.execute();
			ps.close();
		}

}
