package model;

import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String email;
}
