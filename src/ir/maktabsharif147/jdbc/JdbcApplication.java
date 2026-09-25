package ir.maktabsharif147.jdbc;

import ir.maktabsharif147.jdbc.domains.City;
import ir.maktabsharif147.jdbc.repositories.CityRepositoryImpl;
import ir.maktabsharif147.jdbc.repositories.WalletRepositoryImpl;
import ir.maktabsharif147.jdbc.utils.ApplicationContext;
import ir.maktabsharif147.jdbc.utils.ApplicationProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class JdbcApplication {

//    CRUD

    static void main() throws SQLException {

        ApplicationContext context = ApplicationContext.getInstance();

        Connection connection = context.getConnection();

        System.out.println("connected to database");

        executeDll(connection);

        CityRepositoryImpl cityRepository = context.getCityRepository();
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

        WalletRepositoryImpl walletRepository = context.getWalletRepository();
        System.out.println("wallet by id 10: " + walletRepository.findById(10L));
    }

    private static void executeDll(Connection connection) {
        createSchema(connection, ApplicationProperties.DATASOURCE_SCHEMA_NAME);
        createCityTable(connection);
        createWalletTable(connection);
    }

    private static void createSchema(Connection connection, String schemaName) {
        try (Statement statement = connection.createStatement()) {
            String query = "CREATE SCHEMA IF NOT EXISTS " + schemaName;
            statement.execute(query);
            connection.setSchema(schemaName);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void createCityTable(Connection connection) {
        String ddl = """
                CREATE TABLE IF NOT EXISTS TB_CITY (
                    ID BIGINT PRIMARY KEY,
                    NAME VARCHAR(100) NOT NULL
                )
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(ddl);
            System.out.println("Table 'City' created (or already exists)");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void createWalletTable(Connection connection) {
        String ddl = """
                CREATE TABLE IF NOT EXISTS TB_WALLET (
                    ID BIGINT PRIMARY KEY,
                    CASH BIGINT NOT NULL,
                    CREDIT BIGINT NOT NULL
                )
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(ddl);
            System.out.println("Table 'Wallet' created (or already exists)");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
