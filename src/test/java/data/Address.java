package data;

import lombok.Getter;

@Getter
public class Address {
    private String cityName;
    private String streetName;
    private String buildingName;
    private Integer streetNumber;

    public Address(String cityName, String streetName, String buildingName, Integer streetNumber) {
        this.cityName = cityName;
        this.streetName = streetName;
        this.buildingName = buildingName;
        this.streetNumber = streetNumber;
    }

    public String getFullAddress() {
        return streetNumber + " " + streetName + ", " + buildingName + ", " + cityName;
    }
}