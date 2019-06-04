package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;




public class PedidoDao {
	Connection c;
	
	public PedidoDao() {
		GenericDAO gDao = new GenericDAO();
		c = gDao.getConnection();
	}
	
	public List<Pedido> pesquisarTodosPedidos () throws SQLException{
		System.out.println("entrou no pesquisar todos Pedidos");
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		String sql = "SELECT id,id_cliente, valor_total, forma_pagamento FROM pedido ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
		    Pedido ped = new Pedido();
			ped.setId(rs.getInt("id"));
			ped.setIdCliente(rs.getInt("id_cliente"));
			ped.setValorTotal(rs.getDouble("valor_total"));
			ped.setFormaPagamento(rs.getInt("forma_pagamento"));
			listaPedidos.add(ped);
		}
		rs.close();
		ps.close();
		return listaPedidos;
	}
	
	public void inserePedidos(Pedido ped) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("entrou no insere Pedido");
		String sql = "INSERT INTO pedido (id_cliente, valor_total, forma_pagamento) VALUES(?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, ped.getIdCliente());
		ps.setDouble(2, ped.getValorTotal());
		ps.setInt(3, ped.getFormaPagamento());
		ps.execute();
		ps.close();
	}

	public void removerPedido(int id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE pedido WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		ps.close();
	}
	
	public void atualizaPedido (Pedido ped) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE pedido SET  id_cliente = ?, valor_total = ?, forma_pagamento = ?  WHERE id = ? ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, ped.getIdCliente());
		ps.setDouble(2, ped.getValorTotal());
		ps.setInt(3, ped.getFormaPagamento());
		ps.setInt(4, ped.getId());
		ps.execute();
		ps.close();
	}

}
