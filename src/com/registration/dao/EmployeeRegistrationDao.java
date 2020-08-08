package com.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.registration.model.Employee;

public class EmployeeRegistrationDao {

	public void registerEmployee(Employee emp) {
		String insertQuery = "Insert into employees "
				+ "(first_name, last_name, username, password, address, contact_number) "
				+ "values "
				+ "(?, ?, ?, ?, ?, ?)";

		Connection connection = getConnection();

		try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){
			preparedStatement.setString(1, emp.getFirstName());
			preparedStatement.setString(2, emp.getLastName());
			preparedStatement.setString(3, emp.getUsername());
			preparedStatement.setString(4, emp.getPassword());
			preparedStatement.setString(5, emp.getAddress());
			preparedStatement.setInt(6, emp.getContactNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		};
	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String jdbcUrl = "jdbc:mysql://localhost:3306/servlets_practice";
		String username = "root";
		String password = "Boban@Root";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(jdbcUrl, username, password);
		} catch (SQLException e) {
			printSQLException(e);
		};
		System.out.println(connection);
		return connection;
	}
	
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
