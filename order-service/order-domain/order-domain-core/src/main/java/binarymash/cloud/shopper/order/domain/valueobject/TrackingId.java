package binarymash.cloud.shopper.order.domain.valueobject;

import binarymash.cloud.shopper.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {

    public TrackingId(UUID value) {
        super(value);
    }
}
