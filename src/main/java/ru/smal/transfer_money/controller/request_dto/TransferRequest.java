package ru.smal.transfer_money.controller.request_dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest implements BaseRequest {

    private long idSender;
    private long idReceiver;
    private BigDecimal amount;
}
