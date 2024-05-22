package company.maintanace;

public record Longitude(Float longitude) {
    public Longitude {
        if (longitude == null) {
            throw new RuntimeException("longitude cant be null");
        }
    }
}
