package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.BaseDomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJdbcBaseRepository<E extends BaseDomain<ID>, ID extends Number>
        implements BaseRepository<E, ID> {

    protected final Connection connection;

    protected AbstractJdbcBaseRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public E insert(E baseDomain) {
        String sql = getInsertSqlQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            fillInsertQueryParams(statement, baseDomain);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return baseDomain;
    }

    private String getInsertSqlQuery() {
        return "insert into " + getTableName() + " values (" + getInsertParamString() + ")";
    }

    protected abstract String getInsertParamString();

    protected void fillInsertQueryParams(PreparedStatement statement, E baseDomain) {
        fillIdAndFirstIndex(statement, baseDomain.getId());
        fillInsertQuerySpecificParams(statement, baseDomain);
    }

    protected abstract void fillIdAndFirstIndex(PreparedStatement statement, ID id);

    protected abstract void fillInsertQuerySpecificParams(PreparedStatement statement, E baseDomain);

    @Override
    public E findById(ID id) {
        E entity = null;
        String sql = "select * from " + getTableName() + " where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            fillIdAndFirstIndex(statement, id);
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
    public List<E> findAll() {
        List<E> domains = new ArrayList<>();
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

    protected abstract E getEntityInstance(ResultSet resultSet);
}
