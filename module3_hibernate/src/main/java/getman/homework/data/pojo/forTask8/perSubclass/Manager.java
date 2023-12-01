package getman.homework.data.pojo.forTask8.perSubclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "MANAGER")
@PrimaryKeyJoinColumn(name = "EMPLOYEE_ID")
public class Manager extends Employee implements Serializable {
    @Column(name = "PLAN")
    private long salesPlan;
    @Column(name = "DEPARTMENT")
    private String department;

    public Manager(String id, String name, String surName, long salesPlan, String department) {
        super(id, name, surName);
        this.salesPlan = salesPlan;
        this.department = department;
    }

    public Manager() {
    }

    public long getSalesPlan() {
        return salesPlan;
    }

    public void setSalesPlan(long salesPlan) {
        this.salesPlan = salesPlan;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
