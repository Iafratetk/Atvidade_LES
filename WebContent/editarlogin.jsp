<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page
	import="java.text.SimpleDateFormat, java.util.List, java.util.ArrayList, model.Produto, model.Cliente, model.Usuario, model.Pedido, model.PedidoProduto"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript">
	function validarLogin(){
		if(document.formEditar.login.value==""){
			alert("campo login não informado");
			return false;
		}
		if(document.formEditar.senha.value==""){
			alert("campo senha não informado");
			return false;
		}
		document.formEditar.submit();
	}
</script>
<title>Alterar Login</title>
</head>
<body>
	<%
	Usuario u = (Usuario) session.getAttribute("usuarioautenticado");
	String nomeUsuario = u.getLogin();
	if(nomeUsuario==null){
		throw new ServletException("Nenhum Usuario logado");
	}
	%>
	<% String login = request.getParameter("login");
	   String senha = request.getParameter("senha");
	   int id = Integer.parseInt(request.getParameter("id"));
	   int tipo = Integer.parseInt(request.getParameter("tipo"));
	   int idCliente = Integer.parseInt(request.getParameter("idCliente"));
	%>
	<form name="formEditar" action="editalogin" method="post">
	<td>Seu número de usuário é: </td><u><input type="text" readonly="true" name="id" value="<%=id%>"></input></u>
	<input type="text" name="login" value="<%=login%>"></input>
	<input type="text" name="senha" value="<%=senha%>"></input>
	<input class="waves-effect waves-light btn" Style="background:red"  type="button" value="editar" name="Editar" onclick="validarLogin()"></input>
	<input type="text" style="visibility: hidden" readonly="true" name="tipo" value="<%=tipo%>"></input>
	<input type="text" style="visibility: hidden" readonly="true" name="idCliente" value="<%=idCliente%>"></input>
	</form>
	
</body>
</html>