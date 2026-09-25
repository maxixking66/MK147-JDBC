package ir.maktabsharif147.jdbc.domains;

public class City extends BaseDomain {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
               "id=" + getId() +
               ", name='" + name + '\'' +
               '}';
    }
}
