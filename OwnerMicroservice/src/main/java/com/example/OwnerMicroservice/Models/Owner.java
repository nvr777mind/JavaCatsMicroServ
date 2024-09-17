package com.example.OwnerMicroservice.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "owners")
public class Owner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String name;
    private Date birthDate;
    
    private String password;
    private String roles; 
    
    @OneToMany
    @JoinColumn(name = "owner_id")
    private List<Cat> cats;
}
