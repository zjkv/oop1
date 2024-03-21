package model;

public record ClientId(Long id) {
    public ClientId {
        if (id == null) {
            throw new RuntimeException("Id cant be null");
        }
    }
}
