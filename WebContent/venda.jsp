<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page
	import="java.text.SimpleDateFormat, java.util.List, java.util.ArrayList, model.Produto, persistence.ProdutoDao, model.Usuario"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
		<%
	Usuario u = (Usuario) session.getAttribute("usuarioautenticado");
	String nomeUsuario = u.getLogin();
	if(nomeUsuario==null){
		throw new ServletException("Nenhum Usuario logado");
	}
	%>
	<%	
		List<Produto> listaSacola = (List<Produto>) session.getAttribute("LISTAPRODUTO");
		ProdutoDao pDao = new ProdutoDao();
		Produto p = new Produto();
		List<Produto> listaProduto = pDao.pesquisarTodos();
		session.setAttribute("LISTASACOLA", listaSacola);		
	%>
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
	<form action="sacola" method="get">
	<td>
		<select class="waves-effect waves-light" name="produtosSlc" onchange="calcular(this.value);">
			<%
			for (Produto pr : listaProduto){
				
			%>
			<option value="<%=pr.getId()%>"><%=pr.getNome()%></option>
			<%
			}
			%>
		</select>		
	</td>
		<input class="waves-effect waves-light btn" Style="background:red"  type="submit" value="Adicionar a Sacola" name="adcionar"></input>


			<%
				double valorTotal = 0;
				if (listaSacola != null) {
				for (Produto pro : listaSacola) {
					valorTotal = pro.getValor() + valorTotal;
		%>
		<table border="3">
			<tr>
				<th>nome:</th>
				<td><%=pro.getNome()%></td>
				<th>valor:</th>
				<td><%=pro.getValor()%></td>
				<td>
				</td>
			</tr>
		</table>
				<%
				}
			} 
		%>
				</form>
		<h2>Valor: <%=valorTotal %></h2>
		<div align="right"  valign="top">
		<form action="sacola" method="post">
		<select name="formaSlc">
			<h4>Selecione a forma de pagamento: </h4>
			<option value="1">Dinheiro</option>
			<option value="2">Cartão de Crédito</option>
			<option value="3">Cartão de Débito</option>
		</select>
		<input class="waves-effect waves-light btn" Style="background:blue"  type="submit" value="Finalizar Venda" name="adcionar"></input>
		</form>
		</div>
</body>
 <nav class="red lighten-1" role="navigation">
    
      <ul class="right hide-on-med-and-down">
      </ul>
	
      <ul id="nav-mobile" class="side-nav">
        </ul>
      </div>
  </nav>
</html>