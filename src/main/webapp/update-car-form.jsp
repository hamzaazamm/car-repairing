<!DOCTYPE html>
<html>

<head>
	<title>Update Car</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-car-style.css">
</head>

<body>
<div id="wrapper">
	<div id="header">
		<h2>Car System</h2>
	</div>
</div>

<div id="container">
	<h3>Update Car</h3>

	<form action="CarControllerServlet" method="GET">

		<input type="hidden" name="command" value="UPDATE" />

		<input type="hidden" name="car_id" value="${THE_CAR.id}" />

		<table>
			<tbody>
			<tr>
				<td><label>Make:</label></td>
				<td><input type="text" name="make"
						   value="${THE_CAR.make}" /></td>
			</tr>

			<tr>
				<td><label>Model:</label></td>
				<td><input type="text" name="model"
						   value="${THE_CAR.model}" /></td>
			</tr>

			<tr>
				<td><label>Engine Capacity:</label></td>
				<td><input type="text" name="enginecapacity"
						   value="${THE_CAR.enginecapacity}" /></td>
			</tr>

			<tr>
				<td><label>Color:</label></td>
				<td><input type="text" name="color"
						   value="${THE_CAR.color}" /></td>
			</tr>

			<tr>
				<td><label>Doors:</label></td>
				<td><input type="text" name="doors"
						   value="${THE_CAR.doors}" /></td>
			</tr>

			<tr>
				<td><label>Suspension:</label></td>
				<td><input type="text" name="suspension"
						   value="${THE_CAR.suspension}" /></td>
			</tr>

			<tr>
				<td><label>Price:</label></td>
				<td><input type="text" name="price"
						   value="${THE_CAR.price}" /></td>
			</tr>

			<tr>
				<td><label>Description:</label></td>
				<td><textarea name="description">${THE_CAR.description}</textarea></td>
			</tr>

			<tr>
				<td><label></label></td>
				<td><input type="submit" value="Save" class="save" /></td>
			</tr>

			</tbody>
		</table>
	</form>

	<div style="clear: both;"></div>

	<p>
		<a href="CarControllerServlet">Back to List</a>
	</p>
</div>
</body>

</html>
