package company.rental;

public record RidesAmount(int amount) {

    public RidesAmount {
        if (amount<0) {
            throw new RuntimeException("Rides amount cant be negative");
        }
    }
}
