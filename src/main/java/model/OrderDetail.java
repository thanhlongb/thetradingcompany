package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "orderdetail")
public class OrderDetail { //FIXME: should combine this class with Order?
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JsonIgnore
    private Order order;
    @Column
    private Product product;
    @Column
    private int quantity;
    @Column
    private int price;
}
