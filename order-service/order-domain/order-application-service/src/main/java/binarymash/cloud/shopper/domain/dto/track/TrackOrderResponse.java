package binarymash.cloud.shopper.domain.dto.track;

import binarymash.cloud.shopper.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public record TrackOrderResponse(@NotNull UUID orderTrackingId, @NotNull OrderStatus orderStatus,
                                 List<String> failureMessages) {
}
