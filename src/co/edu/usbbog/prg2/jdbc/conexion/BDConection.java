package co.edu.usbbog.prg2.jdbc.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConection {

    /**
     * Parametros de conexion
     */
    static String bd = "distribuidos";
    static String login = "postgres";
    static String password = "root";
    static String url = "jdbc:postgresql://localhost:5432/" + bd;

    Connection connection;

    /**
     * Constructor
     */
    public BDConection() {
        connection = null;

        try {
            // obtenemos el driver de para mysql
            // Class.forName("com.mysql.jdbc.Driver");
            // obtenemos el driver para postgresql
            Class.forName("org.postgresql.Driver");
            // obtenemos la conexión
            connection = DriverManager.getConnection(url, login, password);

            /* if (connection != null) {
                //System.out.println("Conexión a base de datos " + bd + " OK\n");
            } */
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Permite retornar la conexión
     */
    public Connection getConnection() {
        return connection;
    }

    public void desconectar() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
