<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart Page</title>
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
			<h3 class="text-center">List of Products</h3>
			<hr>
			<br>
			<table cellpadding="2" ,cellspacing="2" border="1"
				class="table table-striped table_center">
				<thead class="alert alert-info">
					<tr>
						<th>Option</th>
						<th>Id</th>
						<th>Name</th>
						<th>Photo</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Sub Total</th>
					</tr>

				</thead>
				<!--  
				<c:set var="total" value="0"></c:set>
				<c:forEach var="item" items="${sessionScope.cart }">
					<c:set var="total"
						value="${total+ item.product.price * item.quantity }"></c:set>
					<tr>
						<td align="center"><a
							href="${pageContext.request.contextPath }/cart?action=remove&id=${item.product.id}"
							onclick="return confirm('Are yous sure?')">Remove</a></td>
						<td>${item.product.id }</td>
						<td>${item.product.name }</td>
						<td><img alt=""
							src="${pageContext.request.contextPath }/resources/images/${item.product.image}"
							class="imgProduct"></td>
						<td>${item.product.price }</td>
						<td>${item.quantity }</td>
						<td>${item.product.price * item.quantity }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6">Total</td>
					<td>${total }</td>
				
				</tr>
				-->
				
				<c:set var="total" value="0"></c:set>
				<c:forEach var="item" items="${sessionScope.cart }">
					
					<tr>
						<td>
							<a href="${pageContext.request.contextPath }/cart?action=remove&id=${item.product.id }">Remove</a>
						</td>
						<td>${item.product.id }</td>
						<td>${item.product.name }</td>
						<td>
							<img class="imgProduct" alt="" src="${pageContext.request.contextPath }/resources/images/${item.product.image }">
						</td>
						<td>${item.product.price }</td>
						<td>${item.quantity }</td>
						<td>${item.quantity * item.product.price }</td>
						
					</tr>
				
				</c:forEach>
			</table>
			<br>
			<h6 class="text-center">
				<a href="${pageContext.request.contextPath }/product">Continue Shopping</a>
			</h6>
		</div>

	</div>
</body>
</html>