package ru.smal.transfer_money.mapping;

import ru.smal.transfer_money.entity.Entity;

import java.io.Serializable;

public interface BaseMapping<R extends Serializable, E extends Entity> {

    E map(R request);
}
