package com.example.cinema.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


//u shtuan validimet per moshen dhe emailin
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Emri nuk duhet te jete bosh")
    private String name;

    @Email(message = "Email-i duhet te jete ne format te sakte")
    @NotBlank(message = "Email-i nuk duhet te jete bosh")
    private String email;

    @Min(value = 13, message = "Mosha minimale duhet te jete 13 vjeç")
    @Max(value = 120, message = "Mosha maksimale lejohet deri ne 120 vjeç")
    private int age;

    public User() {}

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
