package getman.homework.data.pojo.forTask6;

import javax.persistence.*;

@Entity
@Table(name = "ID_IDENTITY")
public class IdIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public IdIdentity(String name) {
        this.name = name;
    }

    public IdIdentity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
