package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.BaseDomain;

import java.util.List;

public interface BaseRepository<E extends BaseDomain<ID>, ID extends Number> {

    E insert(E baseDomain);

//    E update(E baseDomain);

    E findById(ID id);

    List<E> findAll();

//    long count();

//    void deleteById(ID id);

//    boolean existsById(ID id);
}
