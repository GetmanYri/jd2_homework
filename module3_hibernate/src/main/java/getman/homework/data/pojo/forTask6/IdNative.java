package getman.homework.data.pojo.forTask6;

import javax.persistence.*;

@Entity
@Table(name = "ID_INCREMENT")
public class IdNative {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    public IdNative(String name) {
        this.name = name;
    }

    public IdNative() {
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
