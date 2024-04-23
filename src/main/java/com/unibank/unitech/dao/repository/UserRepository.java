package com.unibank.unitech.dao.repository;

import com.unibank.unitech.dao.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPin(String pin);

    boolean existsByPin(String pin);
}
