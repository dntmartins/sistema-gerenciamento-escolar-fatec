<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<title>Matricula de Disciplinas</title>
</head>
<body>
	<h1>Cadastro de Disciplinas</h1>
	<form action="gradeHorario" method="post">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>1 Semestre</th>
					<th>2 Semestre</th>
					<th>3 Semestre</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${disciplines}" var="discipline">
					<tr>
						<td>
						<c:if test="${discipline.getModule().getName().equals("1 Semestre")}">
							<input type="checkbox" name="checkedRows"
							value="${discipline.getId()}">
							<c:out value="${discipline.getName()}" />
						</c:if>
						</td>
						<td>
						<c:if test="${discipline.getModule().getName().equals("2 Semestre")}">
							<input type="checkbox" name="checkedRows"
							value="${discipline.getId()}">
							<c:out value="${discipline.getName()}" />
						</c:if>
						</td>
						<td>
						<c:if test="${discipline.getModule().getName().equals("3 Semestre")}">
							<input type="checkbox" name="checkedRows"
							value="${discipline.getId()}">
							<c:out value="${discipline.getName()}" />
						</c:if>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<input type="submit" name="submit" value="Cadastrar">
	</form>
</body>
</html>