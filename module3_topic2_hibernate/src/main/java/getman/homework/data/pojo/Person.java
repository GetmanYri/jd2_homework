package getman.homework.data.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Person implements Serializable {
    @Id
    @GenericGenerator(strategy = "uuid", name = "person-uuid")
    @GeneratedValue(generator = "person-uuid")
    @Column(name = "PERSON_ID")
    private String id;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;

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

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
