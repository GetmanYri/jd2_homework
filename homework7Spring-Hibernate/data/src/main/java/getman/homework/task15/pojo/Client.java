package getman.homework.task15.pojo;

import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "T_CLIENT")
public class Client {
    @Id
    @GenericGenerator(strategy = "uuid", name = "client-uuid")
    @GeneratedValue(generator = "client-uuid")
    @Column(name = "CLIENT_ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public Client() {
    }

    public Client(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;


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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
