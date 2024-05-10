package company.rental;

public record PriceFactor(Double priceFactor) {

    public PriceFactor {
        if (priceFactor < 0) {
            throw new RuntimeException("Factor cannot be less than 0");
        }
    }
}
