package com.tutorial.tutorialCrudProject.users;

import com.tutorial.tutorialCrudProject.posts.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String contact;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String birth;

    @CreationTimestamp
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<Post> posts;

    private enum Gender {
        MALE, FEMALE, NONE;
    }

//    public User(String firstName, String lastName, String email, String password, String contact, String address, String birth) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.contact = contact;
//        this.address = address;
//        this.birth = birth;
//    }
}