package ir.maktabsharif147.jdbc.repositories;

import ir.maktabsharif147.jdbc.domains.Wallet;

import java.util.List;

public interface WalletRepository extends BaseRepository {

    List<Wallet> findAllByCashIsGreaterThanOrEqual(Long cash);
}
