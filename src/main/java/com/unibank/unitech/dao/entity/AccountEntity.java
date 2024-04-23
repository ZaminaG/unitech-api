package com.unibank.unitech.dao.entity;

import com.unibank.unitech.model.enums.AccountStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @NotBlank
    @Column(name = "iban", unique = true)
    private String iban;

    @NotNull
    @Column(name = "balance")
    private BigDecimal balance;

    @NotBlank
    @Column(name = "currency")
    private String currency;

    @Column(name = "opening_date", updatable = false)
    private LocalDateTime openingDate;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;


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
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(getIban(), that.getIban())
                && Objects.equals(getBalance(), that.getBalance())
                && Objects.equals(getCurrency(), that.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBalance(), getIban(), getCurrency());
    }
}
