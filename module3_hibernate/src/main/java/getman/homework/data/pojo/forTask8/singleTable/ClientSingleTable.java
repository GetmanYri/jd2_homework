package getman.homework.data.pojo.forTask8.singleTable;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HIERARCHY_SINGLE_TABLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="P_TYPE",discriminatorType=DiscriminatorType.CHAR)
@DiscriminatorValue(value = "C")
public class ClientSingleTable implements Serializable {
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

    public ClientSingleTable(String id, Integer age, String name, String surname) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.surname = surname;
    }


    public ClientSingleTable() {
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

    @Override
    public String toString() {
        return "PersonPerClass{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
