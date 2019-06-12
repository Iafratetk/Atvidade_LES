package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Usuario;



public class LoginDao {
	Connection c;
	
	public LoginDao() {
		GenericDAO gDao = new GenericDAO();
		c = gDao.getConnection();
	}
	
	public List<Usuario> pesquisarTodosUsuarios () throws SQLException{
		System.out.println("entrou no pesquisar todos usuarios");
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		String sql = "SELECT id,id_cliente,usu,senha,tipo FROM usuario";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
		    Usuario usu = new Usuario();
			usu.setId(rs.getInt("id"));
			usu.setIdCliente(rs.getInt("id_cliente"));
			usu.setLogin(rs.getString("usu"));
			usu.setSenha(rs.getString("senha"));
			usu.setTipo(rs.getInt("tipo"));
			listaUsuarios.add(usu);
		}
		rs.close();
		ps.close();
		return listaUsuarios;
	}

	public void insereUsuario(Usuario u) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("entrou no insere usuario");
		String sql = "INSERT INTO usuario (id_cliente, usu,senha, tipo ) VALUES (?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, u.getIdCliente());
		ps.setString(2, u.getLogin());
		ps.setString(3, u.getSenha());
		ps.setInt(4,u.getTipo());
		ps.execute();
		ps.close();
	}
	public void removerUsuario(int id) throws SQLException {
		String sql = "DELETE FROM usuario WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		ps.close();
	}
	public void atualizaUsuario (Usuario usu) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE usuario SET  id_cliente = ?, usu = ?, senha = ?, tipo = ?   WHERE id = ? ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, usu.getIdCliente());
		ps.setString(2, usu.getLogin());
		ps.setString(3, usu.getSenha());
		ps.setInt(4, usu.getTipo());
		ps.setInt(5, usu.getId());
		ps.execute();
		ps.close();
	}
}
