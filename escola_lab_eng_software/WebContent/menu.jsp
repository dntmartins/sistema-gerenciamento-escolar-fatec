<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.4.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/ajax.js"></script>
<title>Menu do SIGA</title>
</head>
<body>
	<img src="img/logosiga.png"><a style="float:right; margin:10px" class="btn btn-danger" href="logout">Logout</a>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="jumbotron">
		<h1>Bem-Vindo ${nome}!</h1>
		<br>
		<br>
			<ul>
				<li><a href="#">Cadastro de Permissoes</a></li>
				<li><a href="#">Cadastro de Usuarios</a></li>
				<li><a href="#">Cadastro de Disciplina</a></li>
				<li><a href="gradeHorario">Matricula de disciplinas</a></li>
				
			</ul>
			<br />
		</div>
	</div>
</body>
</html>