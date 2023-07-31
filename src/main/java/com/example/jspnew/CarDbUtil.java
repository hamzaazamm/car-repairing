package com.example.jspnew;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDbUtil {

	private DataSource dataSource;

	public CarDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Car> getCars() throws Exception {

		List<Car> Cars = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from car order by make, model";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("car_id");
				String make = myRs.getString("make");
				String model = myRs.getString("model");
				String engineCapacity = myRs.getString("engine_capacity");
				String color = myRs.getString("color");
				String doors = myRs.getString("doors");
				String suspension = myRs.getString("suspension");
				int price = myRs.getInt("price");
				String description = myRs.getString("description");

				// create new car object
				Car tempCar = new Car(id, make, model, engineCapacity, color, doors, suspension, price, description);

				// add it to the list of cars
				Cars.add(tempCar);
			}

			return Cars;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addCar(Car theCar) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into car "
					+ "(make, model, engine_capacity, color, doors, suspension, price, description) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the car
			myStmt.setString(1, theCar.getMake());
			myStmt.setString(2, theCar.getModel());
			myStmt.setString(3, theCar.getEnginecapacity());
			myStmt.setString(4, theCar.getColor());
			myStmt.setString(5, theCar.getDoors());
			myStmt.setString(6, theCar.getSuspension());
			myStmt.setInt(7, theCar.getPrice());
			myStmt.setString(8, theCar.getDescription());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Car getCar(String theCarId) throws Exception {

		Car theCar = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int carId;

		try {
			// convert car id to int
			carId = Integer.parseInt(theCarId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected car
			String sql = "select * from car where car_id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, carId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {
				String make = myRs.getString("make");
				String model = myRs.getString("model");
				String engineCapacity = myRs.getString("engine_capacity");
				String color = myRs.getString("color");
				String doors = myRs.getString("doors");
				String suspension = myRs.getString("suspension");
				int price = myRs.getInt("price");
				String description = myRs.getString("description");

				// use the carId during construction
				theCar = new Car(carId, make, model, engineCapacity, color, doors, suspension, price, description);
			} else {
				throw new Exception("Could not find car id: " + carId);
			}

			return theCar;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateCar(Car theCar) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update car "
					+ "set make=?, model=?, engine_capacity=?, color=?, doors=?, suspension=?, price=?, description=? "
					+ "where car_id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theCar.getMake());
			myStmt.setString(2, theCar.getModel());
			myStmt.setString(3, theCar.getEnginecapacity());
			myStmt.setString(4, theCar.getColor());
			myStmt.setString(5, theCar.getDoors());
			myStmt.setString(6, theCar.getSuspension());
			myStmt.setInt(7, theCar.getPrice());
			myStmt.setString(8, theCar.getDescription());
			myStmt.setInt(9, theCar.getId());

			// execute SQL statement
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteCar(String theCarId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// convert car id to int
			int carId = Integer.parseInt(theCarId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to delete car
			String sql = "delete from car where car_id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, carId);

			// execute sql statement
			myStmt.execute();
		} finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}
	}
}
