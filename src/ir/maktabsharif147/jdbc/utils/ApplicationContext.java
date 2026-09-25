package ir.maktabsharif147.jdbc.utils;

import ir.maktabsharif147.jdbc.repositories.CityJdbcRepositoryImpl;
import ir.maktabsharif147.jdbc.repositories.CityRepository;
import ir.maktabsharif147.jdbc.repositories.WalletJdbcRepositoryImpl;
import ir.maktabsharif147.jdbc.repositories.WalletRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ApplicationContext {

    private static final ApplicationContext CTX = new ApplicationContext();

    private ApplicationContext() {

    }

    public static ApplicationContext getInstance() {
        return CTX;
    }

    private Connection connection;

    private CityRepository cityRepository;
    private WalletRepository walletRepository;

    public Connection getConnection() {
        if (Objects.isNull(connection)) {
            try {
                this.connection = DriverManager.getConnection(ApplicationProperties.DATASOURCE_URL, ApplicationProperties.DATASOURCE_USER, ApplicationProperties.DATASOURCE_PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public CityRepository getCityRepository() {
        if (Objects.isNull(cityRepository)) {
            this.cityRepository = new CityJdbcRepositoryImpl(
                    this.getConnection()
            );
        }
        return cityRepository;
    }

    public WalletRepository getWalletRepository() {
        if (Objects.isNull(walletRepository)) {
            this.walletRepository = new WalletJdbcRepositoryImpl(
                    this.getConnection()
            );
        }
        return walletRepository;
    }
}
