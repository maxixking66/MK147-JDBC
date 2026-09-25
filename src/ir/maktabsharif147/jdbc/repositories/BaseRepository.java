package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.BaseDomain;

import java.util.List;

public interface BaseRepository {

    BaseDomain insert(BaseDomain baseDomain);

//    BaseDomain update(BaseDomain baseDomain);

    BaseDomain findById(Long id);

    List<BaseDomain> findAll();

//    long count();

//    void deleteById(Long id);

//    boolean existsById(Long id);
}
