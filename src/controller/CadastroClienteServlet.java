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
		try {
			System.out.println("entrou no try do cadCliente");
			PrintWriter out = response.getWriter();
			listaCliente = cDao.pesquisarTodosClientes();
			session.setAttribute("CLIENTE", listaCliente);
			response.sendRedirect("./index.jsp");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		response.sendRedirect("./index.jsp");
	}

}
