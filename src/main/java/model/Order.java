package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
    @Column
    private Provider provider;
    @Column
    private Staff staff;
    @Column
    private Date created_at;
    @Column
    private Date updated_at;
    @PrePersist
    protected void onCreate() {
        this.created_at = new Date();
        this.updated_at = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updated_at = new Date();
    }

}
