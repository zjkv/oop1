package company.rental.rentGroup;

public record RentGroupId(Long id) {
    public RentGroupId {
        if (id == null) {
            throw new RuntimeException("Id cant be null");
        }
    }
}
