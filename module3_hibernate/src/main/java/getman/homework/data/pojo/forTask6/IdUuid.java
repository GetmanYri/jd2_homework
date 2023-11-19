package getman.homework.data.pojo.forTask6;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "ID_UUID")
public class IdUuid {
    @Id
    @GeneratedValue(generator = "uuid_id")
    @GenericGenerator(
            name = "uuid_id",
            strategy = "uuid"
    )
    private String id;
    private String name;

    public IdUuid(String name) {
        this.name = name;
    }

    public IdUuid() {
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
}
