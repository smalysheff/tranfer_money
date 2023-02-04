package ru.smal.transfer_money.controller.request_dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest implements BaseRequest {

    private String name;
    private BigDecimal amount;
}
