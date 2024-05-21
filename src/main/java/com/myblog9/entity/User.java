package com.myblog9.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(
        name = "users", uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"}),
                @UniqueConstraint(columnNames = {"email"})
}
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // first many is for user and second one is for role
    @JoinTable(name = "user_roles",   //3rd table name
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), // user_id is the foreign key in 3rd table which is the reference to the primary key of the parent table User
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")// role_id is the foreign key in 3rd table which is the reference to the primary key of the parent table Role
    )
    private Set<Role> roles; //we use set bcause we dont want duplicate value
}

// Constructors, getters, and setters
