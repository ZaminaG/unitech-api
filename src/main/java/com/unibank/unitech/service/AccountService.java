package com.unibank.unitech.service;

import com.unibank.unitech.dao.repository.AccountRepository;
import com.unibank.unitech.mapper.AccountMapper;
import com.unibank.unitech.model.dto.AccountDto;
import com.unibank.unitech.model.enums.AccountStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserService userService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountDto> getUserActiveAccounts() {
        var currentUser = userService.getCurrentUser();
        var activeAccounts = accountRepository.findAllByUserAndStatus(currentUser, AccountStatus.ACTIVE);
        return accountMapper.toAccountList(activeAccounts);
    }
}
