package com.unibank.unitech.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Column(name = "pin", unique = true)
    private String pin;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UserEntity that = (UserEntity) o;
        return Objects.equals(getName(), that.getName())
                && Objects.equals(getSurname(), that.getSurname())
                && Objects.equals(getPin(), that.getPin())
                && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getSurname(), getPin(), getEmail());
    }
}
