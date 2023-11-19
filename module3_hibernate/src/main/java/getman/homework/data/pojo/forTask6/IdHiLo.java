package getman.homework.data.pojo.forTask6;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "ID_HILO")
public class IdHiLo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hilo_sequence_generator")
    @GenericGenerator(
            name = "hilo_sequence_generator",
            //strategy = "hilo",
            strategy = "sequence",
            parameters = {
                    @Parameter(name = "table", value = "hilo_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "5"),
                    @Parameter(name = "optimizer", value = "hilo")
            }
    )
    private long id;
    private String name;

    public IdHiLo(String name) {
        this.name = name;
    }

    public IdHiLo() {
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


