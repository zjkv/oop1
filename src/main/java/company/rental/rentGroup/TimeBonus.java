package company.rental.rentGroup;

public record TimeBonus(Integer minutesBonus) {

    public TimeBonus {
        if (minutesBonus < 0) {
            throw new RuntimeException("Factor cannot be less than 0");
        }
    }
}
