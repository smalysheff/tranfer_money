package ru.smal.transfer_money.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.smal.transfer_money.controller.request_dto.AccountRequest;
import ru.smal.transfer_money.controller.request_dto.TransferRequest;
import ru.smal.transfer_money.entity.Account;
import ru.smal.transfer_money.service.TransferService;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final TransferService service;

    @GetMapping
    public Iterable<Account> getAccounts() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable long id)  {
        return service.findOne(id);
    }

    @PostMapping
    public Account create(@RequestBody AccountRequest request) {
        return service.create(request);
    }

    @PutMapping
    public void transferMoney(@RequestBody TransferRequest request) {
        service.transferMoney(request);
    }
}
