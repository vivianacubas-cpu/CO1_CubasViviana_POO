package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL  = "jdbc:mysql://localhost:3307/dbValleTech";
    private static final String USER = "root";
    private static final String PASS = "12345";
    public static Connection getConexion() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Error conexión: " + e.getMessage());
        }
        return cn;
    }
}
