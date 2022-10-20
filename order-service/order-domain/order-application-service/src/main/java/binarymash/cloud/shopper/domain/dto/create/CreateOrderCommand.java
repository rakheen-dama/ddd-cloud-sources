package binarymash.cloud.shopper.domain.dto.create;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record CreateOrderCommand(@NotNull UUID customerId, @NotNull UUID supplierId, @NotNull BigDecimal price,
                                 @NotNull List<OrderItem> items, @NotNull OrderAddress orderAddress) { }
