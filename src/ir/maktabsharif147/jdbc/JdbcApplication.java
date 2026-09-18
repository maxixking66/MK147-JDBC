package ir.maktabsharif147.jdbc;

import java.sql.*;

public class JdbcApplication {

    static void main() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        final String user = "postgres";
        final String password = "123456789";
        final String schemaName = "mk_147_jdbc";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("connected to database");
            createSchema(connection, schemaName);
            createCityTable(connection);
            insertCityInstanceIfCountIsZero(connection);
            selectAllCitiesAndPrintThem(connection);
        }

    }

    private static void createSchema(Connection connection, String schemaName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = "CREATE SCHEMA IF NOT EXISTS " + schemaName;
            statement.execute(query);
        }
        connection.setSchema(schemaName);
    }

    private static void createCityTable(Connection connection) throws SQLException {
        String ddl = """
                CREATE TABLE IF NOT EXISTS TB_CITY (
                    ID BIGINT PRIMARY KEY,
                    NAME VARCHAR(100) NOT NULL
                )
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(ddl);
            System.out.println("Table 'City' created (or already exists)");
        }
    }

    private static void insertCityInstanceIfCountIsZero(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String countQuery = "SELECT COUNT(*) FROM TB_CITY";
            ResultSet rs = statement.executeQuery(countQuery);
            rs.next();
            long count = rs.getLong(1);
            if (count == 0) {
                String insertQuery = "INSERT INTO TB_CITY (ID, NAME) VALUES (1, 'TEHRAN')";
                statement.executeUpdate(insertQuery);
            }
        }
    }

    private static void selectAllCitiesAndPrintThem(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String selectAllQuery = "SELECT * FROM TB_CITY";
            ResultSet rs = statement.executeQuery(selectAllQuery);
//            int row = rs.getRow();
//            System.out.println(row);
            while (rs.next()) {
//                Long id = rs.getLong("id");
//                String name = rs.getString("name");
//                rs.findColumn("id");
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                System.out.printf("city row: %d, id: %d, name: %s", rs.getRow(), id, name);
                System.out.println();
            }
        }
    }
}
