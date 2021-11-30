package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-05-many-to-many?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "root1";
        try {
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Successful connection");

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
