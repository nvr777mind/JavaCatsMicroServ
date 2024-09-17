package com.example.OwnerMicroservice.Models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "cats")
public class Cat {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private Date birthDate;
    private String breed;

    @Enumerated (EnumType.STRING)
    private CatsColor color;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "friendship",
            joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private List<Cat> catsFriends;
    
    
    @Transactional
    public void addFriend(Cat friend) {
        this.catsFriends.add(friend);
    }
}
