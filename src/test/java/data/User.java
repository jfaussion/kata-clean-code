package data;

import lombok.Getter;

@Getter
public class User {

    public final String objectName = "This is a user";

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

    public String displayUser() {
        return firstName + " " + lastName + ", " + getJobToDisplay();
    }

    private String getJobToDisplay() {
        if (company != null) {
            return company.getName();
        } else {
            return "unemployed";
        }
    }
}
