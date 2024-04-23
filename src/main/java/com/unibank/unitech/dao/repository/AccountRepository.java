package com.unibank.unitech.dao.repository;

import com.unibank.unitech.dao.entity.AccountEntity;
import com.unibank.unitech.dao.entity.UserEntity;
import com.unibank.unitech.model.enums.AccountStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findAllByUserAndStatus(UserEntity userEntity, AccountStatus status);

    Optional<AccountEntity> findByUserAndIbanAndStatus(UserEntity userEntity, String iban, AccountStatus status);

    Optional<AccountEntity> findByIbanAndStatus(String iban, AccountStatus status);
}
