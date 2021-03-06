package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import persistence.ClienteDao;




/**
 * Servlet implementation class CadastroClienteServlet
 */
@WebServlet("/cadcliente")
public class CadastroClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("entrou no get do cadCliente");
		HttpSession session = request.getSession();
		ClienteDao cDao = new ClienteDao();
		List<Cliente> listaCliente = new ArrayList<>();
		String cmd = request.getParameter("cmd");
		if("alterar".equals(cmd)) {
			
		}else if("remover".equals(cmd)) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				cDao.removerClientes(id);
				listaCliente = cDao.pesquisarTodosClientes();
				session.setAttribute("CLIENTE", listaCliente);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
		try {
			System.out.println("entrou no try do cadCliente");
			listaCliente = cDao.pesquisarTodosClientes();
			session.setAttribute("CLIENTE", listaCliente);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		response.sendRedirect("./painel.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String telefone =request.getParameter("telefone");
		String endereco = request.getParameter("endereco");
		System.out.println("entrou no post do cadcliente");
		ClienteDao cDao = new ClienteDao();
		Cliente c = new Cliente();
		c.setNome(nome);
		c.setTelefone(telefone);
		c.setEndereco(endereco);
		try {
			System.out.println("entrou no try do cadproduto");
			cDao.insereClientes(c);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./painel.jsp");
	}

}
