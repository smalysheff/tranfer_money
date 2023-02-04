package ru.smal.transfer_money.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smal.transfer_money.controller.request_dto.AccountRequest;
import ru.smal.transfer_money.controller.request_dto.TransferRequest;
import ru.smal.transfer_money.entity.Account;
import ru.smal.transfer_money.mapping.AccountMapper;
import ru.smal.transfer_money.repository.AccountRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferService {

    private static final String ACCOUNT_NOT_FOUND = "Account not found id: %s";

    private final AccountRepository repository;
    private final AccountMapper mapper;

    public Iterable<Account> findAll() {
        return repository.findAll();
    }

    public Account findOne(long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(ACCOUNT_NOT_FOUND, id)));
    }

    public Account create(AccountRequest request) {
        Account account = mapper.map(request);
        return repository.save(account);
    }

    @Transactional
    public void transferMoney(TransferRequest request) {
        long idSender = request.getIdSender();
        Account sender = repository
                .findById(idSender)
                .orElseThrow(() -> new RuntimeException(String.format(ACCOUNT_NOT_FOUND, idSender)));

        long idReceiver = request.getIdReceiver();
        Account receiver = repository
                .findById(idReceiver)
                .orElseThrow(() -> new RuntimeException(String.format(ACCOUNT_NOT_FOUND, idSender)));

        BigDecimal amount = request.getAmount();
        BigDecimal senderNewAmount = sender
                .getAmount()
                .subtract(amount);

        BigDecimal receiverNewAmount = receiver
                .getAmount()
                .add(amount);

        repository.changeAmount(idSender, senderNewAmount);
        repository.changeAmount(idReceiver, receiverNewAmount);
    }
}
