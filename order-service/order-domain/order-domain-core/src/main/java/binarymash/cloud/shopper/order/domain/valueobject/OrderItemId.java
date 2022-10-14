package binarymash.cloud.shopper.order.domain.valueobject;

import binarymash.cloud.shopper.domain.valueobject.BaseId;

import java.util.UUID;

public class OrderItemId extends BaseId<Long> {

    public OrderItemId(Long value) {
        super(value);
    }
}
