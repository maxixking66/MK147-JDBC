package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityJdbcRepositoryImpl extends AbstractJdbcBaseRepository<City, Integer>
        implements CityRepository {

    public CityJdbcRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getInsertParamString() {
        return "?,?";
    }

    @Override
    protected void fillIdAndFirstIndex(PreparedStatement statement, Integer integer) {
        try {
            statement.setInt(1, integer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String getTableName() {
        return City.TABLE_NAME;
    }

    @Override
    protected City getEntityInstance(ResultSet resultSet) {
        try {
            City city = new City();
            city.setId(resultSet.getInt(1));
            city.setName(resultSet.getString(2));
            return city;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void fillInsertQuerySpecificParams(PreparedStatement statement, City baseDomain) {
        try {
            statement.setString(2, baseDomain.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public City findByName(String name) {
        String sql = "select * from " + getTableName() + " where name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getEntityInstance(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
