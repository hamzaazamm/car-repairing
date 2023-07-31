<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Car Tracker App</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

<div id="wrapper">
	<div id="header">
		<h2>Car System</h2>
	</div>
</div>

<div id="container">

	<div id="content">

		<!-- put new button: Add Car -->

		<input type="button" value="Add Car"
			   onclick="window.location.href='add-car-form.jsp'; return false;"
			   class="add-car-button"
		/>

		<table>

			<tr>
				<th>Make</th>
				<th>Model</th>
				<th>Engine Capacity</th>
				<th>Color</th>
				<th>Doors</th>
				<th>Suspension</th>
				<th>Price</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
			<% String contextPath;
				contextPath = request.getContextPath();

			%>
			<c:forEach var="tempCar" items="${CAR_LIST}">

				<!-- set up a link for each car -->
				<c:url var="tempLink" value="//CarControllerServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="car_id" value="${tempCar.id}" />
				</c:url>

				<!--  set up a link to delete a car -->
				<c:url var="deleteLink" value="//CarControllerServlet">
					<c:param name="command" value="DELETE" />
					<c:param name="car_id" value="${tempCar.id}" />
				</c:url>

				<tr>
					<td> ${tempCar.make} </td>
					<td> ${tempCar.model} </td>
					<td> ${tempCar.enginecapacity} </td>
					<td> ${tempCar.color} </td>
					<td> ${tempCar.doors} </td>
					<td> ${tempCar.suspension} </td>
					<td> ${tempCar.price} </td>
					<td> ${tempCar.description} </td>
					<td>
						<a href="${tempLink}">Update</a>
						|
						<a href="${deleteLink}"
						   onclick="if (!(confirm('Are you sure you want to delete this car?'))) return false">
							Delete</a>
					</td>
				</tr>

			</c:forEach>

		</table>

	</div>

</div>
</body>


</html>
