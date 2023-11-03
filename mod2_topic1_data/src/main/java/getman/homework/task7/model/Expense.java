package getman.homework.task7.model;

import java.io.Serializable;

public class Expense implements Serializable {
    private long id;
    private String date;
    private long receiverId;
    private double value;
    private Receiver receiver;


    public Expense(long id,String date, long receiverId, double value) {
        this.id = id;
        this.date = date;
        this.receiverId = receiverId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", receiverId=" + receiverId +
                ", value=" + value +
                ", receiver=" + receiver +
                '}';
    }
}
