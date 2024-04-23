package com.unibank.unitech.model.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    @NotBlank
    private String sourceAccount;
    @NotBlank
    private String destinationAccount;
    @NotNull
    private BigDecimal amount;
}
