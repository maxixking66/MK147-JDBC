package ir.maktabsharif147.jdbc;

import ir.maktabsharif147.jdbc.domains.City;
import ir.maktabsharif147.jdbc.utils.ApplicationContext;
import ir.maktabsharif147.jdbc.utils.ApplicationProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcApplication {

//    CRUD

    static void main() throws SQLException {

        ApplicationContext context = ApplicationContext.getInstance();

        Connection connection = context.getConnection();

        executeDll(connection);

//        WalletRepository walletRepository = context.getWalletRepository();
//
//        Wallet wallet = new Wallet();
//        wallet.setId(2L);
//        wallet.setCash(20000L);
//        wallet.setCredit(25000L);
//        walletRepository.insert(wallet);

        City city = new City();
        city.setId(11L);
        city.setName("کرمان");
        context.getCityRepository().insert(city);
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
