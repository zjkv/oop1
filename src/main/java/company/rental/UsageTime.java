package company.rental;

public record UsageTime(Integer minutes) {
    public UsageTime {
        if (minutes == null) {
            throw new RuntimeException("Usage time cant be null");
        }

        if (minutes < 0) {
            throw new RuntimeException("Usage time cannot be les than 0");
        }
    }
}
