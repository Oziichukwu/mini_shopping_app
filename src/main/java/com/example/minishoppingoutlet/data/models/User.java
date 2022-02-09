package com.example.minishoppingoutlet.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private String userId;

    @NotBlank(message = "firstName cannot be blank")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name ="LAST_NAME")
    @NotBlank(message = "lastName cannot be blank")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @Column(unique = true)
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    private String password;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private LocalDateTime publishedDate;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private LocalDateTime updatedDate;

    private String cartId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
}
