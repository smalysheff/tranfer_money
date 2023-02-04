package ru.smal.transfer_money.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.smal.transfer_money.controller.request_dto.TransferRequest;
import ru.smal.transfer_money.entity.Account;
import ru.smal.transfer_money.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private TransferService service;

    @Test
    void transferMoneySuccessTest() {
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2);
        receiver.setAmount(new BigDecimal(1000));

        given(repository.findById(1L)).willReturn(Optional.of(sender));
        given(repository.findById(2L)).willReturn(Optional.of(receiver));

        TransferRequest request = buildRequest();

        service.transferMoney(request);

        then(repository)
                .should()
                .changeAmount(1, new BigDecimal(900));
    }

    @Test
    void transferMoneyFailTest() {
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        given(repository.findById(1L)).willReturn(Optional.of(sender));
        given(repository.findById(2L)).willReturn(Optional.empty());

        try {
            service.transferMoney(new TransferRequest());
            fail("Account not found id: 1");
        } catch (RuntimeException ignored) {
        }

        then(repository)
                .should(never())
                .changeAmount(anyLong(), any());
    }

    private static TransferRequest buildRequest() {
        TransferRequest request = new TransferRequest();

        request.setIdSender(1L);
        request.setIdReceiver(2L);
        request.setAmount(new BigDecimal(100));

        return request;
    }
}