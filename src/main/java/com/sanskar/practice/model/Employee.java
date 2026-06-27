package com.sanskar.practice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name="employee")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String role;

    private double salary;

    private String dept;
}
