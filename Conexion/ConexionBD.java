package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private  final String URL = "jdbc:mysql://localhost:3306/proyecto_basesdatos?verifyServerCertificate=false&useSSL=true";
    private  final String USER = "root";
    private  final String PASSWORD = "8403040608";

    public Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

