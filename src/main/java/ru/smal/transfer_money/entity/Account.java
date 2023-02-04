package ru.smal.transfer_money.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Setter
@Table
public class Account implements Entity{

    @Id
    private long id;

    private String name;
    private BigDecimal amount;
}
