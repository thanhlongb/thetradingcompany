package model;

import javax.persistence.*;

@Entity
@Table(name = "provider")
public class Provider {
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
    private String fax;
    @Column
    private String email;
    @Column
    private String contact_person;
}
