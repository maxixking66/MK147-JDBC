package ir.maktabsharif147.jdbc.dtos;

// T, N, U, S, V, K
public class GenericBox<T> {

    private T data;

    public void setData(T mohsen) {
        this.data = mohsen;
    }

    public T getData() {
        return data;
    }

}
