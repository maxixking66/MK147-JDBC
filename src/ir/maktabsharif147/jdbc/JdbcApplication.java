package ir.maktabsharif147.jdbc;

import ir.maktabsharif147.jdbc.domains.City;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class JdbcApplication {

    static void main() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        final String user = "postgres";
        final String password = "123456789";
        final String schemaName = "mk_147_jdbc";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("connected to database");
            createSchema(connection, schemaName);
//            createCityTable(connection);
//            insertCityInstanceIfCountIsZero(connection);
//            selectAllCitiesAndPrintThem(connection);
//            City city = new City();
//            city.setId(1L);
//            city.setName("Tehran");
//            updateCity(connection, city);
//            deleteCity(connection, 3L);


//            printCityById(connection);

//            insertBatchByStatement(connection);
//            insertBatchByPreparedStatement(connection);
            City byId = findById(connection, 1L);
            System.out.println(byId);
            System.out.println(findById(connection, 10L));

        }

    }

    private static void insertBatchByStatement(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.addBatch("INSERT INTO TB_CITY (ID, NAME) VALUES (3, '3')");
            statement.addBatch("INSERT INTO TB_CITY (ID, NAME) VALUES (4, '4')");
            statement.addBatch("INSERT INTO TB_CITY (ID, NAME) VALUES (5, '5')");
            int[] ints = statement.executeBatch();
            System.out.println(Arrays.toString(ints));
        }
    }

    private static void insertBatchByPreparedStatement(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO TB_CITY (ID, NAME) VALUES (?, ?)";


        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
//            INSERT INTO TB_CITY (ID, NAME) VALUES (?, ?)
            statement.setLong(1, 6L);
//            INSERT INTO TB_CITY (ID, NAME) VALUES (6, ?)
            statement.setString(2, "6");
//            insert into tb_city (id, name) values (6, '6');
            statement.addBatch();

//            INSERT INTO TB_CITY (ID, NAME) VALUES (?, ?)
            statement.setLong(1, 7L);
//            INSERT INTO TB_CITY (ID, NAME) VALUES (7, ?)
            statement.setString(2, "7");
//            insert into tb_city (id, name) values (7, '7');
            statement.addBatch();

            statement.setLong(1, 8L);
            statement.setString(2, "8");
            statement.addBatch();

            statement.setLong(1, 9L);
            statement.setString(2, "9");
            statement.addBatch();

            int[] ints = statement.executeBatch();
            System.out.println(Arrays.toString(ints));
        }
    }

    private static void printCityById(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String param = scanner.nextLine();
//        printCityByIdByStatement(connection, param);
        printCityByIdByPreparedStatement(connection, param);
    }

    private static void printCityByIdByStatement(Connection connection, String param) throws SQLException {
        String sql = "SELECT * FROM TB_CITY WHERE id = " + param;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
        }
    }

    private static void printCityByIdByPreparedStatement(Connection connection, String param) throws SQLException {
        String sql = "SELECT * FROM TB_CITY WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, param);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
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

    private static void updateCity(Connection connection, City city) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String updateQuery = "UPDATE TB_CITY SET NAME = '" + city.getName() + "' WHERE ID = " + city.getId();
            statement.executeUpdate(updateQuery);
        }
    }

    private static void deleteCity(Connection connection, Long cityId) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String deleteQuery = "DELETE FROM TB_CITY WHERE ID = " + cityId;
            statement.executeUpdate(deleteQuery);
        }
    }

    private static City findById(Connection connection, Long id) throws SQLException {
        City city = null;
        String sql = "select * from tb_city where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = new City();
                city.setId(resultSet.getLong(1));
                city.setName(resultSet.getString(2));
            }
        }
        return city;
    }
}
