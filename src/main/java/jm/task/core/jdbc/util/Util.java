package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pp.1.1.4";
    private static final String DB_USERNAME = "Utkativ";
    private static final String DB_PASSWORD = "root";


    public static Connection getConnectionBD() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            return connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
            //throw new RuntimeException();
        }
        // реализуйте настройку соеденения с БД
        return connection;
    }

}

