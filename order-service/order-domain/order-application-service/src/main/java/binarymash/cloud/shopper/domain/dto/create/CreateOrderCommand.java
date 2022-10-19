package binarymash.cloud.shopper.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public record CreateOrderCommand(@NotNull UUID customerId, @NotNull UUID supplierId, @NotNull BigDecimal price,
                                 @NotNull List<OrderItem> items, @NotNull OrderAddress orderAddress) {

}
