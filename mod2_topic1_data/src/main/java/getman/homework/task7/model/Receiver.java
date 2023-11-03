package getman.homework.task7.model;

import java.io.Serializable;

public class Receiver implements Serializable {
    private long id;
    private String receiver;

    public Receiver(long id, String receiver) {
        this.id = id;
        this.receiver = receiver;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "id=" + id +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
