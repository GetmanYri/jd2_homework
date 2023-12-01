package getman.homework.data.pojo.forTask8.perSubclass;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "EMPLOYEE_ID")
@Table(name = "ENGINEER")
public class Engineer extends Employee implements Serializable {
    @Column(name = "COST_PER_HOUR")
    private int costPerHour;
    @Column(name = "SPECIALIZATION")
    private String specialization;

    public Engineer(String id, String name, String surName, int costPerHour, String specialization) {
        super(id, name, surName);
        this.costPerHour = costPerHour;
        this.specialization = specialization;
    }

    public Engineer() {
    }

    public int getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(int costPerHour) {
        this.costPerHour = costPerHour;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
