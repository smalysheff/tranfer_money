package ru.smal.transfer_money.mapping;

import org.springframework.stereotype.Component;
import ru.smal.transfer_money.controller.request_dto.AccountRequest;
import ru.smal.transfer_money.entity.Account;

@Component
public class AccountMapper implements BaseMapping<AccountRequest, Account> {

    @Override
    public Account map(AccountRequest request) {
        if (request == null) {
            return null;
        }

        Account account = new Account();

        account.setName(request.getName());
        account.setAmount(request.getAmount());

        return account;
    }
}
