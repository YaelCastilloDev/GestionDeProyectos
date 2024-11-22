package DBConeccion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConeccion {
    private static Connection connection;

    public static void initializeConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String protocol = "jdbc:mysql";
            String user = "root";
            String password = "123456";
            String host = "localhost";
            String port = "3306";
            String database = "GestionDeProyectos";
            //String url = protocol + "://" + host + ":" + port + "/" + database;
            String url = "jdbc:mysql://localhost:3306/GestionDeProyectos";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connection established.");
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not initialized. Call initializeConnection() first.");
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close the connection: " + e.getMessage());
            }
        }
    }

    public static void tryConnection(){
        try {
            SQLConeccion.initializeConnection();

        } catch (Exception e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Error connecting to the database. Please check your configuration.",
                    "Database Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
