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
        Company company = new Company("Klanik", companyAddress);
        user = new User("Test", "C", userAddress, company);
    }

    @Test
    public void testWhitSingleAttribut() {
        var result1 = KeyWordService.getObjectFromPath(user, "firstName");
        assertEquals("Test", result1);
    }

    @Test
    public void testWithAttributAndMethods() {
        var result2 = KeyWordService.getObjectFromPath(user, "address.getFullAddress()");
        assertEquals("42 rue St Rôme, Capitole, Toulouse", result2);
    }

    @Test
    public void testWithMethods() {
        var result3 = KeyWordService.getObjectFromPath(user, "getCompany().getAddress().getStreetNumber()");
        assertEquals(123, result3);
    }

    @Test
    public void testFalsePath() {
        var result4 = KeyWordService.getObjectFromPath(user, "fakeAttribut.name");
        assertNull(result4);
    }
}
