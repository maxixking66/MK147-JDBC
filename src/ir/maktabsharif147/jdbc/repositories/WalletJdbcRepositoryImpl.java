package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.BaseDomain;
import ir.maktabsharif147.jdbc.domains.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//   CURD Wallet
public class WalletJdbcRepositoryImpl extends AbstractJdbcBaseRepository implements WalletRepository {

    public WalletJdbcRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getInsertParamString() {
        return "?,?,?";
    }

    @Override
    protected void fillInsertQuerySpecificParams(PreparedStatement statement, BaseDomain baseDomain) {
        try {
            statement.setLong(2, ((Wallet) baseDomain).getCash());
            statement.setLong(3, ((Wallet) baseDomain).getCredit());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
    public List<Wallet> findAllByCashIsGreaterThanOrEqual(Long cash) {
        List<Wallet> walletList = new ArrayList<>();
        String sql = "select * from " + getTableName() + " where cash >= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cash);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                walletList.add(
                        getEntityInstance(resultSet)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return walletList;
    }
}
