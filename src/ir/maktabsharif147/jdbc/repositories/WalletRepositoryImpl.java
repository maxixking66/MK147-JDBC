package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//   CURD Wallet
public class WalletRepositoryImpl {

    private final Connection connection;

    public WalletRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public Wallet findById(Long id) {
        Wallet entity = null;
        String sql = "select * from tb_wallet where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = getEntityInstance(resultSet);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return entity;
    }

    public List<Wallet> findAll() {
        List<Wallet> walletList = new ArrayList<>();
        String sql = "select * from tb_wallet";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
//                    City city = new City();
//                    city.setId(resultSet.getLong(1));
//                    city.setName(resultSet.getString(2));
//                    walletList.add(city);

                walletList.add(
                        getEntityInstance(resultSet)
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return walletList;
    }

    private Wallet getEntityInstance(ResultSet resultSet) {
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
}
