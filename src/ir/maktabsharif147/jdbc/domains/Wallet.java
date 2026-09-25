package ir.maktabsharif147.jdbc.domains;

public class Wallet extends BaseDomain {

    private Long cash;
    private Long credit;

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
               "id=" + getId() +
               ", cash=" + cash +
               ", credit=" + credit +
               '}';
    }
}
