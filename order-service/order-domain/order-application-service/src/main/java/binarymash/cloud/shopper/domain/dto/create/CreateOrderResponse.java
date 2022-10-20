package binarymash.cloud.shopper.domain.dto.create;

import binarymash.cloud.shopper.domain.valueobject.OrderStatus;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
public record CreateOrderResponse(@NotNull UUID orderTrackingId, @NotNull OrderStatus orderStatus,
                                  @NotNull String message) {
}
