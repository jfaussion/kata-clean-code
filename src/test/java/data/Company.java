package data;

import lombok.Getter;

@Getter
public class Company {

    public final String objectName = "This is a company";

    private String name;
    private Address address;

    public Company(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
