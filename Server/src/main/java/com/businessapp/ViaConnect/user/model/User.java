package com.businessapp.ViaConnect.user.model;

import com.businessapp.ViaConnect.model.Audit;
import com.businessapp.ViaConnect.profile.model.Profile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;



@NoArgsConstructor // No Args Constructor generated by Lombok
@Getter // Getters generated by Lombok
@Setter // Setters generated by Lombok
@Entity // Announce JPA entity
@Table(	name = "users", // Create database table named users
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"), // Username must be unique
                @UniqueConstraint(columnNames = "email") // Email address must be unique
        })
public class User extends Audit implements Serializable {


    @Id // Database table key is the ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Create key value sequence
    private Long id;

    @NotBlank // Username is mandatory
    @Size(max = 25) // Username may consist of a maximum of 25 characters
    private String username;

    @NotBlank // Email is mandatory
    @Size(max = 50) // Email address may consist of a maximum of 50 characters
    @Email // Validate if provided email address is indeed a email address
    private String email;

    @NotBlank // Password is required
    @Size(max = 125) // Password may consist of a maximum of 125 characters
    private String password;

    // ManyToMany with Role
//    @JsonIgnore // Ignore user_roles with JSON request
//    @ManyToMany(fetch = FetchType.LAZY)
//    // A user can have multiple roles, a role can have multiple users. Only fetch when required.
//    @JoinTable(name = "user_roles", // Create table user_roles to join the user ID with a role ID
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();

     //OneToOne with Profile
    @OneToOne(fetch = FetchType.LAZY, // Fetch the related entity lazily (only when needed)
            cascade =  CascadeType.ALL, // Apply all cascading effects to the related entity (update / delete corresponding entity)
            mappedBy = "user") // Entity is not responsible for this relationship and should look for a field named user
    private Profile profile;

    // Table is still null
    // Relation with DBFile.java is working yet
//    @OneToOne
//    private DBFile dbFile;
//
//    @OneToOne
//    private PasswordResetToken passwordResetToken;

    // All Args Constructor
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
