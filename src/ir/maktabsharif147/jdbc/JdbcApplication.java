package ir.maktabsharif147.jdbc;

import ir.maktabsharif147.jdbc.domains.BaseDomain;
import ir.maktabsharif147.jdbc.repositories.CityRepository;
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

        CityRepository cityRepository = context.getCityRepository();
        System.out.println(cityRepository.findById(1L));

        List<BaseDomain> cityList = cityRepository.findAll();
        if (Objects.nonNull(cityList) && !cityList.isEmpty()) {
            for (BaseDomain baseDomain : cityList) {
//                TODO cast
                System.out.println(baseDomain);
            }
        }
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
