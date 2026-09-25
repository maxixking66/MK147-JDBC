package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.City;

public interface CityRepository extends BaseRepository {

    City findByName(String name);
}
