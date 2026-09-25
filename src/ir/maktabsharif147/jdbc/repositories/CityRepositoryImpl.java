package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//   CURD city
public class CityRepositoryImpl {

    private final Connection connection;

    public CityRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public City findById(Long id) {
        City city = null;
        String sql = "select * from tb_city where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = getEntityInstance(resultSet);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return city;
    }

    public List<City> findAll() {
        List<City> cityList = new ArrayList<>();
        String sql = "select * from tb_city";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
//                    City city = new City();
//                    city.setId(resultSet.getLong(1));
//                    city.setName(resultSet.getString(2));
//                    cityList.add(city);

                cityList.add(
                        getEntityInstance(resultSet)
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return cityList;
    }

    private City getEntityInstance(ResultSet resultSet) {
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
