<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page
	import="java.text.SimpleDateFormat, java.util.List, java.util.ArrayList, model.Produto, model.Cliente, model.Usuario, model.Pedido, model.PedidoProduto, persistence.LoginDao, persistence.ClienteDao, persistence.PedidoDao, persistence.ProdutoDao, controller.SessionCounter, exception.InvalidUserAccesDenied"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Painel de administrador</title>
</head>
   <nav class="red lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="./principal.jsp" class="brand-logo">Energize Company</a>
      <ul class="right hide-on-med-and-down">
      </ul>
	
      <ul id="nav-mobile" class="side-nav">
        </ul>
      </div>
  </nav>
  <div align="right" valign="top">
	<a class="waves-effect waves-light btn" Style="background:red"  href="remover.jsp">Sair</a>
</div>
<body>
<div>
	<%
	Usuario us = (Usuario) session.getAttribute("usuarioautenticado");
	String nomeUsuario = us.getLogin();
	int tipo = us.getTipo();
	if(nomeUsuario==null){
		throw new ServletException("Nenhum Usuario logado");
	}
	if(tipo == 1){
		throw new InvalidUserAccesDenied("Acesso negado!");
	}
	%>
	<div>
		<%
		int contadorUsuario =0;
		List<Usuario> listaU = new ArrayList<>();
		LoginDao uDao = new LoginDao();
		listaU = uDao.pesquisarTodosUsuarios();
		for (Usuario user : listaU){
			contadorUsuario = contadorUsuario + 1;
		}
		int contadorCliente = 0;
		List<Cliente> listaC = new ArrayList<>();
		ClienteDao cDao = new ClienteDao();
		listaC = cDao.pesquisarTodosClientes();
		for (Cliente cli : listaC){
			contadorCliente = contadorCliente + 1;
		}
		int contadorPedido = 0;
		double tot = 0;
		List<Pedido> listaP = new ArrayList<>();
		PedidoDao pDao = new PedidoDao();
		listaP = pDao.pesquisarTodosPedidos();
		for (Pedido ped : listaP){
			contadorPedido = contadorPedido + 1;
			tot = ped.getValorTotal() + tot;
		}
		int contadorProduto =0;
		List<Produto> listaPr = new ArrayList<>();
		ProdutoDao prDao = new ProdutoDao();
		listaPr = prDao.pesquisarTodos();
		for (Produto pr : listaPr){
			contadorProduto =  contadorProduto + 1;
		}
		double media = tot/contadorPedido;
		%>
		<table border="3">
		<tr>
			
			<h5 style="color:blue">Quantidade de Usuarios Cadastrados: <h5 style="color:red"><%=contadorUsuario%></h5></h5>
			<h5 style="color:blue">Quantidade de Clientes Cadastrados: <h5 style="color:red"><%=contadorCliente%></h5></h5>
			<h5 style="color:blue">Quantidade de Pedido Realizados:        <h5 style="color:red"><%=contadorPedido%></h5></h5>
			<h5 style="color:blue">Quantidade de Produtos Cadastrados: <h5 style="color:red"><%=contadorProduto %></h5></h5>
			<h5 style="color:blue">Quantidade de Dinheiro Negociado: <h5 style="color:red"><%=tot%> R$</h5></h5>
			<h5 style="color:blue">M�dia de valor por Pedido:  <h5 style="color:red"><%=media%> R$</h5></h5>
			<h5 style="color:green">Sess�es Iniciadas: <%=SessionCounter.getActiveSessions()%></h5>
		</tr>
		</table>
	</div>
</div>
<h1>PRODUTO</h1>

	<form action="cadproduto" method="post">
		<input type="text" name="nome" placeholder="nome"></input> <input
			type="text" name="quantidade" placeholder="quantidade"></input> <input
			type="text" name="valor" placeholder="valor"></input> <input class="waves-effect waves-light btn" Style="background:red"
			type="submit" value="inserir" name="inserir"></input>

	</form>
		<form action="cadproduto" method="get">
		<input class="waves-effect waves-light btn" Style="background:blue" type="submit" value="pesquisar" name="pesquisar"></input>
				<%
			List<Produto> lp = new ArrayList<>();
			lp = (List<Produto>) session.getAttribute("PRODUTO");
			if (lp != null) {
				for (Produto p : lp) {
		%>
		<table border="3">
			<tr>
				<th>id:</th>
				<td><%=p.getId()%></td>
				<th>nome:</th>
				<td><%=p.getNome()%></td>
				<th>quantidade:</th>
				<td><%=p.getQuantidade()%></td>
				<th>valor:</th>
				<td><%=p.getValor()%></td>
				<td>
				<a href="./cadproduto?cmd=editar&id=<%=p.getId()%>">Editar</a>
				<a href="./cadproduto?cmd=remover&id=<%=p.getId()%>">Remover</a>
				</td>
				
			</tr>
		</table>
		<%
			System.out.println(p.getNome());
				}
			} else {

			}
		%>

	</form>
	<h1>CLIENTE</h1>
		<form action="cadcliente" method="post">
		<input type="text" name="nome" placeholder="nome"></input> <input
			type="text" name="telefone" placeholder="Telefone"></input> <input
			type="text" name="endereco" placeholder="Endereco"></input> <input class="waves-effect waves-light btn" Style="background:red" 
			type="submit" value="inserir" name="inserir"></input>
	</form>
	<form action="cadcliente" method="get">
		<input class="waves-effect waves-light btn" Style="background:blue" type="submit" value="pesquisar" name="pesquisar"></input>
		<%
			List<Cliente> lc = new ArrayList<>();
			lc = (List<Cliente>) session.getAttribute("CLIENTE");
			if (lc != null) {
				for (Cliente c : lc) {
		%>
		<table width="200" height="10" border="4">
			<tr>
				<th>id:</th>
				<td><%=c.getId()%></td>
				<th>nome:</th>
				<td><%=c.getNome()%></td>
				<th>Telefone:</th>
				<td><%=c.getTelefone()%></td>
				<th>Endereco:</th>
				<td><%=c.getEndereco()%></td>
				<td>
				<a href="./cadcliente?cmd=editar&id=<%=c.getId()%>">Editar</a>
				<a href="./cadcliente?cmd=remover&id=<%=c.getId()%>">Remover</a>
				</td>
			</tr>
		</table>
		<%
			System.out.println(c.getNome());
				}
			} else {

			}
		%>
	</form>

<h1> USUARIOS</h1>
<form action="cadusuario" method="post">
	<input type="text" name="idCliente" placeholder="id cliente"></input>
	<input type="text" name="login" placeholder="login"></input>
	<input type="password" name="senha" placeholder="senha"></input>
	<input type="text" name="tipo" placeholder="tipo"></input>
	<input class="waves-effect waves-light btn" Style="background:red" type="submit" name="inserir" value="inserir"></input>
</form>
<form action="cadusuario" method="get">
		<input class="waves-effect waves-light btn" Style="background:blue" type="submit" value="pesquisar" name="pesquisar"></input>
		<%
			List<Usuario> lu = new ArrayList<>();
			lu = (List<Usuario>) session.getAttribute("USUARIO");
			if (lu != null) {
				for (Usuario u : lu) {
		%>
		<table width="200" height="10" border="4">
			<tr>
				<th>Id:</th>
				<td><%=u.getId()%></td>
				<th>Id Cliente:</th>
				<td><%=u.getIdCliente()%></td>
				<th>Login:</th>
				<td><%=u.getLogin()%></td>
				<th>senha:</th>
				<td><%=u.getSenha()%></td>
				<th>Tipo:</th>
				<td><%=u.getTipo()%></td>
								<td>
				<a href="./cadusuario?cmd=editar&id=<%=u.getId()%>">Editar</a>
				<a href="./cadusuario?cmd=remover&id=<%=u.getId()%>">Remover</a>
				</td>
			</tr>
		</table>
		<%
			System.out.println(u.getTipo());
				}
			} else {

			}
		%>
	</form>
		<h1>PEDIDO</h1>

	<form action="cadpedido" method="post">
		<input type="text" name="idCliente" placeholder="id cliente"></input>
		<input type="text" name="valorTotal" placeholder="Valor Total"></input>
		<input type="text" name="formaPagamento"
			placeholder="Forma de Pagamento"></input> <input class="waves-effect waves-light btn" Style="background:red" type="submit"
			value="inserir" name="inserir"></input>
	</form>
		<form action="cadpedido" method="get">
		<input class="waves-effect waves-light btn" Style="background:blue" type="submit" value="pesquisar" name="pesquisar"></input>
				<%
			List<Pedido> lpe = new ArrayList<>();
			lpe = (List<Pedido>) session.getAttribute("PEDIDO");
			if (lpe != null) {
				for (Pedido p : lpe) {
		%>
		<table width="200" height="10" border="4">
			<tr>
				<th>Id:</th>
				<td><%=p.getId()%></td>
				<th>Id Cliente:</th>
				<td><%=p.getIdCliente()%></td>
				<th>Valor Total:</th>
				<td><%=p.getValorTotal()%></td>
				<th>Forma Pagamento:</th>
				<td><%=p.getFormaPagamento()%></td>
				<td>
				<a href="./cadpedido?cmd=editar&id=<%=p.getId()%>">Editar</a>
				<a href="./cadpedido?cmd=remover&id=<%=p.getId()%>">Remover</a>
				</td>

			</tr>
		</table>
		<%
			System.out.println(p.getId());
				}
			} else {

			}
		%>
	</form>
	

	<h1>PEDIDO PRODUTO</h1>

	<form action="cadpedidoproduto" method="post">
		<input type="text" name="idPedido" placeholder="id pedido"></input> <input
			type="text" name="idProduto" placeholder="id produto"></input> <input
			type="text" name="valorProduto" placeholder="valor produto"></input>
			<input type="text" name="quantidadeProduto" placeholder="quantidade"></input>
		<input class="waves-effect waves-light btn" Style="background:red" type="submit" value="inserir" name="inserir"></input>

	</form>
	
	<form action="cadpedidoproduto" method="get">
		<input class="waves-effect waves-light btn" Style="background:blue" type="submit" value="pesquisar" name="pesquisar"></input>
		<%
			List<PedidoProduto> lpp = new ArrayList<>();
			lpp = (List<PedidoProduto>) session.getAttribute("PEDIDOPRODUTO");
			if (lpp != null) {
				for (PedidoProduto pp : lpp) {
		%>
		<table width="200" height="10" border="4">
			<tr>
				<th>Id Pedido Produto:</th>
				<td><%=pp.getId()%></td>
				<th>Id Produto:</th>
				<td><%=pp.getIdProduto()%></td>
				<th>Id Pedido:</th>
				<td><%=pp.getIdPedido()%></td>
				<th>Valor Produtos:</th>
				<td><%=pp.getValorProduto()%></td>
				<th>Quantidade: </th>
				<td><%=pp.getQuantidade()%></td>
				<td>
				<a href="./cadpedidoproduto?cmd=editar&id=<%=pp.getId()%>">Editar</a>
				<a href="./cadpedidoproduto?cmd=remover&id=<%=pp.getId()%>">Remover</a>
				</td>
			</tr>
		</table>
		<%

				}
			} else {

			}
		%>
	
	</form>
</body>
 <nav class="red lighten-1" role="navigation">
    
      <ul class="right hide-on-med-and-down">
      </ul>
	
      <ul id="nav-mobile" class="side-nav">
        </ul>
      </div>
  </nav>
</html>