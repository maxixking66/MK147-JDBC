package ir.maktabsharif147.jdbc;

import ir.maktabsharif147.jdbc.domains.City;
import ir.maktabsharif147.jdbc.repositories.CityRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class JdbcApplication {

//    CRUD

    static void main() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        final String user = "postgres";
        final String password = "123456789";
        final String schemaName = "mk_147_jdbc";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("connected to database");
            createSchema(connection, schemaName);
            CityRepositoryImpl cityRepository = new CityRepositoryImpl(connection);
            City byId = cityRepository.findById(1L);
            if (Objects.nonNull(byId)) {
                System.out.println(byId);
            }

            List<City> cityList = cityRepository.findAll();
            if (Objects.nonNull(cityList) && !cityList.isEmpty()) {
                for (City city : cityList) {
                    System.out.println(city);
                }
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
}
