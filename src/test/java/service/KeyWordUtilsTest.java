package service;

import data.Address;
import data.Company;
import data.User;
import org.junit.Before;
import org.junit.Test;
import utils.KeyWordService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class KeyWordUtilsTest {

    private User user;

    @Before
    public void init() {
        Address userAddress = new Address("Toulouse", "rue St Rôme", "Capitole", 42);
        Address companyAddress = new Address("Lyon", "av de la République", "Bourse du travail", 123);
        Company userCompany = new Company("Klanik", companyAddress);
        user = new User("Test", "C", userAddress, userCompany);
    }

    @Test
    public void testWhitPublicAttribut() {
        var result = KeyWordService.getObjectFromPath(user, "objectName", User.class);
        assertEquals("This is a user", result);
    }

    @Test
    public void testWhitPrivateAttribut() {
        var result = KeyWordService.getObjectFromPath(user, "firstName", User.class);
        assertNull(result);
    }

    @Test
    public void testWithPublicMethods() {
        var result = KeyWordService.getObjectFromPath(user, "getAddress().getFullAddress()", User.class);
        assertEquals("42 rue St Rôme, Capitole, Toulouse", result);
    }

    @Test
    public void testWithPrivateMethods() {
        var result = KeyWordService.getObjectFromPath(user, "getJobToDisplay()", User.class);
        assertNull(result);
    }

    @Test
    public void testWithMethodsAndAttributs() {
        var result = KeyWordService.getObjectFromPath(user, "getCompany().objectName", User.class);
        assertEquals("This is a company", result);
    }

    @Test
    public void testFalsePath() {
        var result = KeyWordService.getObjectFromPath(user, "fakeAttribut.name", User.class);
        assertNull(result);
    }
}
