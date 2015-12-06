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
<title>Matricula de Disciplinas</title>
</head>
<body>

	<div class="container">
		<h1>Matricula de Disciplinas</h1>
		<h3>Grade de Horarios</h3>
		<div class="alert alert-danger" id="msgMatriculaError"
			style="font-size: 20px;" hidden="hidden"></div>
		<div class="alert alert-success" id="msgMatriculaSuccess"
			style="font-size: 20px;" hidden="hidden"></div>
		<form action="gradeHorario" method="post">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>1 Semestre</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${disciplines}" var="discipline">
						<tr>
							<c:if test="${discipline.getModule().getName().equals("1 Semestre")}">
								<td><input type="checkbox" name="checkedRows[]"
									value="${discipline.getId()}"> <c:out
										value="${discipline.getName()}" /></td>
							</c:if>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>2 Semestre</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${disciplines}" var="discipline">
						<tr>
							<c:if test="${discipline.getModule().getName().equals("2 Semestre")}">
								<td><input type="checkbox" name="checkedRows[]"
									value="${discipline.getId()}"> <c:out
										value="${discipline.getName()}" /></td>
							</c:if>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>3 Semestre</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${disciplines}" var="discipline">
						<tr>
							<c:if test="${discipline.getModule().getName().equals("3 Semestre")}">
								<td><input type="checkbox" name="checkedRows[]"
									value="${discipline.getId()}"> <c:out
										value="${discipline.getName()}" /></td>
							</c:if>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<input class="btn btn-primary btn-lg" type="button"
				onclick="sendSelectedDisciplines()" name="submit" value="Cadastrar">
			<input class="btn btn-warning btn-lg" type="reset" name="reset"
				value="Limpar">
		</form>
	</div>
</body>
</html>