package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.Wallet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

//   CURD Wallet
public class WalletJdbcRepositoryImpl extends AbstractJdbcBaseRepository implements WalletRepository {

    public WalletJdbcRepositoryImpl(Connection connection) {
        super(connection);
    }

    protected Wallet getEntityInstance(ResultSet resultSet) {
        try {
            Wallet entity = new Wallet();
            entity.setId(resultSet.getLong(1));
            entity.setCash(resultSet.getLong(2));
            entity.setCredit(resultSet.getLong(3));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String getTableName() {
        return Wallet.TABLE_NAME;
    }
}
