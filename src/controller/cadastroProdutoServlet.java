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

import model.Produto;
import persistence.ProdutoDao;

/**
 * Servlet implementation class cadastroProdutoServlet
 */
@WebServlet("/cadproduto")
public class cadastroProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public cadastroProdutoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println("entrou no get do cadproduto");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ProdutoDao pDao = new ProdutoDao();
		List<Produto> listaProduto = new ArrayList<>();
		String cmd = request.getParameter("cmd");
		if("alterar".equals(cmd)) {
			
		}else if("remover".equals(cmd)) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				pDao.removerProdutos(id);
				listaProduto = pDao.pesquisarTodos();
				session.setAttribute("PRODUTO", listaProduto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}else {
		try {
			System.out.println("entrou no try");
			listaProduto = pDao.pesquisarTodos();
			session.setAttribute("PRODUTO", listaProduto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		response.sendRedirect("./painel.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));
		double valor = Double.parseDouble(request.getParameter("valor"));
		System.out.println("entrou no post do capdroduto");
		ProdutoDao pDao = new ProdutoDao();
		Produto p = new Produto();
		p.setNome(nome);
		p.setQuantidade(quantidade);
		p.setValor(valor);
		try {
			System.out.println("entrou no try do cadproduto");
			pDao.insereProdutos(p);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./painel.jsp");
	}

}
