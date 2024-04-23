package com.unibank.unitech.service;

import com.unibank.unitech.dao.entity.AccountEntity;
import com.unibank.unitech.dao.entity.UserEntity;
import com.unibank.unitech.dao.repository.AccountRepository;
import com.unibank.unitech.exception.InvalidInputException;
import com.unibank.unitech.exception.NotFoundException;
import com.unibank.unitech.model.dto.PaymentDto;
import com.unibank.unitech.model.enums.AccountStatus;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AccountRepository accountRepository;
    private final UserService userService;

    public void makePayment(PaymentDto paymentDto) {
        validatePaymentDto(paymentDto);
        UserEntity currentUser = userService.getCurrentUser();
        var sourceAccount = getActiveAccountByIbanAndUser(paymentDto.getSourceAccount(), currentUser);
        var destinationAccount = getActiveAccountByIban(paymentDto.getDestinationAccount());
        validateSufficientBalance(paymentDto.getAmount(), sourceAccount);
        performPayment(sourceAccount, destinationAccount, paymentDto.getAmount());
    }

    private void validatePaymentDto(PaymentDto paymentDto) {
        if (Objects.equals(paymentDto.getSourceAccount(), paymentDto.getDestinationAccount())) {
            throw new InvalidInputException("wrong_account", "Source and destination accounts cannot be the same.");
        }
    }

    private void validateSufficientBalance(BigDecimal amount, AccountEntity sourceAccount) {
        if (amount.compareTo(sourceAccount.getBalance()) > 0) {
            throw new InvalidInputException("insufficient_balance", "Payment failed due to insufficient balance");
        }
    }

    private void performPayment(AccountEntity sourceAccount, AccountEntity destinationAccount, BigDecimal amount) {
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
        accountRepository.saveAll(List.of(sourceAccount, destinationAccount));
    }

    private AccountEntity getActiveAccountByIbanAndUser(String iban, UserEntity user) {
        return accountRepository.findByUserAndIbanAndStatus(user, iban, AccountStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Active source account not found."));
    }

    private AccountEntity getActiveAccountByIban(String iban) {
        return accountRepository.findByIbanAndStatus(iban, AccountStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Active destination account not found."));
    }
}
