package data;

import lombok.Getter;

@Getter
public class User {

    private String firstName;
    private String lastName;
    private Address address;
    private Company company;

    public User(String firstName, String lastName, Address address, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.company = company;
    }
}
