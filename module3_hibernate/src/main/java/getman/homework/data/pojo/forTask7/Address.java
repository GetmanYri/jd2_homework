package getman.homework.data.pojo.forTask7;

import javax.persistence.Embeddable;


// The test for task 7 is completed in:
// module3_hibernate/src/test/java/getman/homework/data/dao/DaoForTask2_3/PersonDaoImpTest.java
//public void testSavePersonWithAddress()


@Embeddable
public class Address {
    private String city;
    private String country;
    private String street;

    public Address(String city, String country, String street) {
        this.city = city;
        this.country = country;
        this.street = street;
    }

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
