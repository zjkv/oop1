package company.rental;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record RentPrice(Double rentPrice) {
    public RentPrice {
        if (rentPrice < 0) {
            throw new RuntimeException("Price cannot be les than 0");
        }
    }

    public BigDecimal getPrice() {
        return new BigDecimal(rentPrice).setScale(2, RoundingMode.UP);
    }
}
