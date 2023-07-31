package com.example.jspnew;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/CarControllerServlet")
public class CarControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CarDbUtil carDbUtil;

    @Resource(name = "jdbc/web_student_tracker")
    private DataSource dataSource;

    public CarControllerServlet() {
        try {
            init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();

        // create our car db util ... and pass in the conn pool / datasource
        try {
            carDbUtil = new CarDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");

            // if the command is missing, then default to listing cars
            if (theCommand == null) {
                theCommand = "LIST";
            }

            // route to the appropriate method
            switch (theCommand) {

                case "LIST":
                    listCars(request, response);
                    break;

                case "ADD":
                    addCar(request, response);
                    break;

                case "LOAD":
                    loadCar(request, response);
                    break;

                case "UPDATE":
                    updateCar(request, response);
                    break;

                case "DELETE":
                    deleteCar(request, response);
                    break;

                default:
                    listCars(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read car id from form data
        String theCarId = request.getParameter("car_id");

        // delete car from database
        carDbUtil.deleteCar(theCarId);

        // send them back to "list cars" page
        listCars(request, response);
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read car info from form data
        int id = Integer.parseInt(request.getParameter("car_id"));
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        String engineCapacity = request.getParameter("enginecapacity");
        String color = request.getParameter("color");
        String doors = request.getParameter("doors");
        String suspension = request.getParameter("suspension");
        int price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");

        // create a new car object
        Car theCar = new Car(id, make, model, engineCapacity, color, doors, suspension, price, description);

        // perform update on database
        carDbUtil.updateCar(theCar);

        // send them back to the "list cars" page
        listCars(request, response);
    }

    private void loadCar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read car id from form data
        String theCarId = request.getParameter("car_id");

        // get car from database (db util)
        Car theCar = carDbUtil.getCar(theCarId);

        // place car in the request attribute
        request.setAttribute("THE_CAR", theCar);

        // send to jsp page: update-car-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-car-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addCar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read car info from form data
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        String engineCapacity = request.getParameter("enginecapacity");
        String color = request.getParameter("color");
        String doors = request.getParameter("doors");
        String suspension = request.getParameter("suspension");
        int price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");

        // create a new car object
        Car theCar = new Car(make, model, engineCapacity, color, doors, suspension, price, description);

        // add the car to the database
        carDbUtil.addCar(theCar);

        // send back to main page (the car list)
        listCars(request, response);
    }

    private void listCars(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // get cars from db util
        List<Car> Cars = carDbUtil.getCars();

        // add cars to the request
        request.setAttribute("CAR_LIST", Cars);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-cars.jsp");
        dispatcher.forward(request, response);
    }

}
