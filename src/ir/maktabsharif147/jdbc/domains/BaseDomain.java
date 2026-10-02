package ir.maktabsharif147.jdbc.domains;

public abstract class BaseDomain<ID extends Number> {

    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
