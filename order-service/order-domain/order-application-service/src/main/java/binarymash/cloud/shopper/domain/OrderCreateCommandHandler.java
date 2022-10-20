package binarymash.cloud.shopper.domain;

import binarymash.cloud.shopper.domain.dto.create.CreateOrderCommand;
import binarymash.cloud.shopper.domain.dto.create.CreateOrderResponse;
import binarymash.cloud.shopper.domain.mapper.OrderDataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateCommandHandler {

    private OrderCreateHelper orderCreateHelper;
    private OrderDataMapper orderDataMapper;

    private CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        orderCreateHelper.persistOrder(createOrderCommand);
    }

}
