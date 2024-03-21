package model;

public record ScooterId(Long id) {
    public ScooterId {
        if (id == null) {
            throw new RuntimeException("Id cant be null");
        }
    }
}
