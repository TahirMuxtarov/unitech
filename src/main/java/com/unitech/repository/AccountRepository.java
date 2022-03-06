package com.unitech.repository;

import com.unitech.model.AccountStatus;
import com.unitech.model.entity.Accounts;
import com.unitech.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

    Optional<List<Accounts>> findAllByUser(User user);

    Optional<Accounts> findByAccountNo(String accountNo);

    List<Accounts> findAllByAccountStatus(AccountStatus status);
}
