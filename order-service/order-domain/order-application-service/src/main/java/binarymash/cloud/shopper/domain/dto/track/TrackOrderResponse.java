package binarymash.cloud.shopper.domain.dto.track;

import binarymash.cloud.shopper.domain.valueobject.OrderStatus;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Builder
public record TrackOrderResponse(@NotNull UUID orderTrackingId, @NotNull OrderStatus orderStatus,
                                 List<String> failureMessages) {
}
