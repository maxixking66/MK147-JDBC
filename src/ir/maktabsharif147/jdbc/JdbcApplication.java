package ir.maktabsharif147.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcApplication {

    static void main() throws SQLException {
//        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=mk_147";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "123456789";

        Connection connection = DriverManager.getConnection(url, user, password);
//        Connection connection = DriverManager.getConnection(
//                "jdbc:postgresql://localhost:5432/postgres",
//                "postgres",
//                "123456789"
//        );
        System.out.println("connected to database");

        final String schemaName = "mk_147_jdbc";

        try (Statement statement = connection.createStatement()) {
            String query = "CREATE SCHEMA IF NOT EXISTS " + schemaName;
//            statement.execute(query);
            statement.execute("CREATE SCHEMA IF NOT EXISTS " + schemaName);
        }
        connection.setSchema(schemaName);

//        createSchema(connection, schemaName);

    }

    private static void createSchema(Connection connection, String schemaName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = "CREATE SCHEMA IF NOT EXISTS " + schemaName;
            statement.execute(query);
        }
        connection.setSchema(schemaName);
    }
}
