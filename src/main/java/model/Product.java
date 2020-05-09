package model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String model;
    @Column
    private String brand;
    @Column
    private String company;
    @Column
    private String description;
    @ManyToOne
    private Category category;
    @Column
    private Float price;

}
