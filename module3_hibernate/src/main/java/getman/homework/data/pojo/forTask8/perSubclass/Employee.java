package getman.homework.data.pojo.forTask8.perSubclass;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EMPLOYEE_SUB")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee implements Serializable {
    @Id
    @GenericGenerator(strategy = "uuid", name = "person_uuid")
    @GeneratedValue(generator = "person_uuid")
    @Column(name = "EMPLOYEE_ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surName;

    public Employee(String id, String name, String surName) {
        this.id = id;
        this.name = name;
        this.surName = surName;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
