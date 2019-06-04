<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript">
	function validarLogin(){
		if(document.formCadastro.nome.value==""){
			alert("campo nome não informado");
			return false;
		}
		if(document.formCadastro.telefone.value==""){
			alert("campo telefone não informado");
			return false;
		}
		if(document.formCadastro.endereco.value==""){
			alert("campo endereco não informado");
			return false;
		}
		if(document.formCadastro.login.value==""){
			alert("campo login não informado");
			return false;
		}
		if(document.formCadastro.senha.value==""){
			alert("campo senha não informado");
			return false;
		}
		document.formCadastro.submit();
	}
</script>
<title>Cadastre-se</title>
</head>
<body>
<div align="right" valign="top">
	<form>
		<u><a class="waves-effect waves-light btn" Style="background:red"  href="./login.jsp">Login</a></u>
	</form>
	</div>
	<div>
	<form name="formCadastro" action="cadastreSeServlet" method="get">
	<u><h2>Formulário de Cadastro</h2></u>
	<input type="text" name="nome" placeholder="nome"></input>
	<input type="number" name="telefone" placeholder="telefone"></input>
	<input type="text" name="endereco" placeholder="endereço"></input>
	<input type="text" name="login" placeholder="login"></input>
	<input type="password" name="senha" placeholder="senha"></input>
	<input class="waves-effect waves-light btn" Style="background:red"
			type="submit" value="confirmar" name="confirmar" onclick="validarLogin()"></input>
	</form>
	<%
	
	String str = (String) session.getAttribute("CADASTRADO"); 
	if (str != null){
	%>
	<h3  Style="color:#530EE8"><u><td><%=str %></td></u></h3>
	<%} %>
	</div>
</body>
</html>