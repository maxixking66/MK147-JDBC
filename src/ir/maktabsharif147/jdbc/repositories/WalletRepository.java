package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.Wallet;

import java.util.List;

public interface WalletRepository extends BaseRepository<Wallet, Long> {

    List<Wallet> findAllByCashIsGreaterThanOrEqual(Long cash);

    @Override
    Wallet insert(Wallet baseDomain);

    @Override
    Wallet findById(Long aLong);

    @Override
    List<Wallet> findAll();
}
