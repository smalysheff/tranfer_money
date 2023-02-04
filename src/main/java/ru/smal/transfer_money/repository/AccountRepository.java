package ru.smal.transfer_money.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.smal.transfer_money.entity.Account;

import java.math.BigDecimal;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Modifying
    @Query("UPDATE account SET amount = :amount WHERE id = :id")
    void changeAmount(long id, BigDecimal amount);
}
