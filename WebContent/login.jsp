<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.text.SimpleDateFormat, java.util.List, java.util.ArrayList, model.Usuario"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript">
	function validarLogin(){
		if(document.formLogin.usuario.value==""){
			alert("campo usuario não informado");
			return false;
		}
		if(document.formLogin.senha.value==""){
			alert("campo senha não informado");
			return false;
		}
		document.formLogin.submit();
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<h1>Faça seu login aqui!</h1>
	<form name="formLogin" action="login" method="post">
		<center>
			<input type="text" name="usuario"
				placeholder="Usuário"></input>
				</br></br>
				 <input
				type="password" name="senha" placeholder="Senha"></input>
				<input class="waves-effect waves-light btn" Style="background:red"  type="button" value="entrar" name="entrar" onclick="validarLogin()"></input>
		</center>
	</form>
</body>
</html>