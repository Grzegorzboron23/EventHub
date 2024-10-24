package com.example.demo.model;

import com.example.demo.valueobject.UserPrivileges;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Column(unique = true)
    private String userName;

    @NotNull
    private String hashedPassword;

    @NotNull
    @Column(unique = true)
    @Email
    private String email;

    @Pattern(regexp = "\\d{11}")
    @Column(unique = true)
    private String phoneNumber;

    private UserPrivileges userPrivileges;

    private Integer points;

}
