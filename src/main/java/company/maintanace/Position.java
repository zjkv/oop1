package company.maintanace;

public record Position(Latitude latitude, Longitude longitude) {


    public Position {
        // Check if cordinates are from POLAND
        if ((latitude.latitude() < 48 || latitude.latitude() > 56) || (longitude.longitude() < 14 || longitude.longitude() > 24))
            throw new RuntimeException("Coordinates not from Poland");
    }

}
