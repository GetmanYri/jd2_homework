package getman.homework.data.pojo.forTask2_3;

import getman.homework.data.pojo.forTask7.Address;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable {
    @Id
    @GenericGenerator(strategy = "uuid", name = "person_uuid")
    @GeneratedValue(generator = "person_uuid")
    @Column(name = "PERSON_ID")
    private String id;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person",cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts=new ArrayList<>();
    @Column(name = "ACCESS_LEVEL")
    private Integer accessLevel;

    private Address address;

    public Person() {
    }

    public Person(String id, Integer age, String name, String surname) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", bankAccounts=" + bankAccounts +
                ", accessLevel=" + accessLevel +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!Objects.equals(id, person.id)) return false;
        if (!Objects.equals(age, person.age)) return false;
        if (!Objects.equals(name, person.name)) return false;
        if (!Objects.equals(surname, person.surname)) return false;
        if (!Objects.equals(bankAccounts, person.bankAccounts))
            return false;
        if (!Objects.equals(accessLevel, person.accessLevel)) return false;
        return Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (bankAccounts != null ? bankAccounts.hashCode() : 0);
        result = 31 * result + (accessLevel != null ? accessLevel.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
