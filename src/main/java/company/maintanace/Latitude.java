package company.maintanace;

public record Latitude(Float latitude) {
    public Latitude {
        if (latitude == null) {
            throw new RuntimeException("latitude cant be null");
        }
    }
}
