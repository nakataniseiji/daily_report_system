package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follower")
@NamedQueries({
    @NamedQuery(
            name = "getAllFollower",
        query = "SELECT f FROM follow AS f ORDER BY f.id DESC"
        ),
    @NamedQuery(
            name = "getFollowerCount",
        query = "SELECT COUNT(f) FROM Follow AS f"
             ),
})
@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee_id;

    @ManyToOne
    @JoinColumn(name = "employee_name", nullable = false)
    private Employee employee_name;

    public void setId(Integer id) {
            this.id = id;
    }

    public Integer getId() {
            return id;
    }

    public void setEmployee_id(Employee employee_id) {
            this.employee_id = employee_id;
    }

    public Employee getEmployee_id() {
            return employee_id;
    }

    public void setEmployee_name(Employee employee_name) {
            this.employee_name = employee_name;
    }

    public Employee getEmployee_name(Employee employee_name) {
            return employee_name;
    }

}