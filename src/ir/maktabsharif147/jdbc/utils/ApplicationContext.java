package ir.maktabsharif147.jdbc.utils;

import ir.maktabsharif147.jdbc.repositories.CityRepositoryImpl;
import ir.maktabsharif147.jdbc.repositories.WalletRepositoryImpl;

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

    private CityRepositoryImpl cityRepository;
    private WalletRepositoryImpl walletRepository;

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

    public CityRepositoryImpl getCityRepository() {
        if (Objects.isNull(cityRepository)) {
            this.cityRepository = new CityRepositoryImpl(
                    this.getConnection()
            );
        }
        return cityRepository;
    }

    public WalletRepositoryImpl getWalletRepository() {
        if (Objects.isNull(walletRepository)) {
            this.walletRepository = new WalletRepositoryImpl(
                    this.getConnection()
            );
        }
        return walletRepository;
    }
}
