package com.connectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* <p> This class used for database connectivity, which connects my application into the database server.
* when the connection is null or closed, the getConnection functions starts to connect with database 
* and the implementation can be execute. when closeConnection function start to close the connectivity.</p>
*
*/
public class ConnectionManager {
    static Connection connection = null;
    static String url = "jdbc:mysql://localhost:3306/ems";
    static String userName = "root";
    static String password = "";
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, userName, password);    
            }
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class couldn't load.");
        } catch (Exception e) {
            System.out.println("Something wrong");
        } 
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}