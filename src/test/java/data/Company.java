package data;

import lombok.Getter;

@Getter
public class Company {

    private String name;
    private Address address;

    public Company(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
