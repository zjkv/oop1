package company.rental;

record Price(float amount) {
    Price {
        if (amount < 0) {
            throw new RuntimeException("Price amount cant be negative");
        }
    }
}
