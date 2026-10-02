package ir.maktabsharif147.jdbc;

import ir.maktabsharif147.jdbc.domains.Wallet;
import ir.maktabsharif147.jdbc.dtos.GenericBox;

public class JdbcApplication {

    static void main() {
        GenericBox<String> stringBox = new GenericBox<>();
        stringBox.setData("mat");
        String data1 = stringBox.getData();

        GenericBox<Wallet> walletBox = new GenericBox<>();
        walletBox.setData(new Wallet());
        Wallet data = walletBox.getData();
    }
}
