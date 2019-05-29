package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;


public class ClienteDao {
	
	private Connection c;
	
	public ClienteDao() {
		GenericDAO gDao = new GenericDAO();
		c = gDao.getConnection();
	}
	
	public List<Cliente> pesquisarTodosClientes () throws SQLException{
		System.out.println("entrou no pesquisar todos Clientes");
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		String sql = "SELECT id, nome, telefone, endereco FROM cliente ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
		    Cliente cli = new Cliente();
			cli.setId(rs.getInt("id"));
			cli.setNome(rs.getString("nome"));
			cli.setTelefone(rs.getString("telefone"));
			cli.setEndereco(rs.getString("endereco"));
			listaClientes.add(cli);
		}
		rs.close();
		ps.close();
		return listaClientes;
	}
	
	public void insereClientes(Cliente cli) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("entrou no insere Cliente");
		String sql = "INSERT INTO Cliente (nome, telefone, endereco ) VALUES (?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cli.getNome());
		ps.setString(2, cli.getTelefone());
		ps.setString(3, cli.getEndereco());
		ps.execute();
		ps.close();
	}
}
