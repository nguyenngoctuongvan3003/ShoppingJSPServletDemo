<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product page</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css"
	integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<style type="text/css">
body {
	display: flex;
	flex-direction: column;
	min-height: 800vh;
}

.table_center {
	margin-left: auto;
	margin-right: auto;
	width: 80%;
}

.imgProduct {
	width: 150px;
}
</style>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato; padding-left: 100px">
			<div class="navbar-brand">
				<a href="<%=request.getContextPath()%>" class="nav-link">Sample
					Shopping </a>
			</div>

		</nav>
	</header>

	<div class="row">
		<div class="container">
			<h3 class="text-center">List of products</h3>
			<hr>
			<br>
			<table cellpadding="2" ,cellspacing="2" border="1"
				class="table table-stripped table_center">
				<thead class="alert alert-info">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Photo</th>
						<th>Price</th>
						<th>Buy</th>
					</tr>
				</thead>
				
				<c:forEach var="product" items="${products }">
					<tr>
						<td>${product.id }</td>
						<td>${product.name }</td>
						<td>
							<img alt="" src="${pageContext.request.contextPath }/resources/images/${product.image}" class="imgProduct">
						</td>
						<td>${product.price }</td>
						
						<td>
							<a href="${pageContext.request.contextPath }/cart?action=buy&id=${product.id }">Buy</a>
						
						</td>
					</tr>

				</c:forEach>

			</table>
		</div>

	</div>
</body>
</html>