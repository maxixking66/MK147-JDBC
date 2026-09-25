package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.BaseDomain;
import ir.maktabsharif147.jdbc.domains.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityJdbcRepositoryImpl extends AbstractJdbcBaseRepository
        implements CityRepository {

    public CityJdbcRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return City.TABLE_NAME;
    }

    @Override
    protected BaseDomain getEntityInstance(ResultSet resultSet) {
        try {
            City city = new City();
            city.setId(resultSet.getLong(1));
            city.setName(resultSet.getString(2));
            return city;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
