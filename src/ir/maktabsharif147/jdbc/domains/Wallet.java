package ir.maktabsharif147.jdbc.domains;

public class Wallet {

    private Long id;
    private Long cash;
    private Long credit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCash() {
        return cash;
    }

    public void setCash(Long cash) {
        this.cash = cash;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Wallet{" +
               "id=" + id +
               ", cash=" + cash +
               ", credit=" + credit +
               '}';
    }
}
