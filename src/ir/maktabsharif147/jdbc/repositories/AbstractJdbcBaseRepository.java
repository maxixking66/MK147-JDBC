package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.BaseDomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJdbcBaseRepository implements BaseRepository {

    protected final Connection connection;

    protected AbstractJdbcBaseRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public BaseDomain findById(Long id) {
        BaseDomain entity = null;
        String sql = "select * from " + getTableName() + " where id = ?";
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

    @Override
    public List<BaseDomain> findAll() {
        List<BaseDomain> domains = new ArrayList<>();
        String sql = "select * from " + getTableName();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                domains.add(getEntityInstance(resultSet));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return domains;
    }

    protected abstract String getTableName();

    protected abstract BaseDomain getEntityInstance(ResultSet resultSet);
}
