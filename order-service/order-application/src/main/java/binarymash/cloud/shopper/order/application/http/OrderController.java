package binarymash.cloud.shopper.order.application.http;

import binarymash.cloud.shopper.domain.dto.create.CreateOrderCommand;
import binarymash.cloud.shopper.domain.dto.create.CreateOrderResponse;
import binarymash.cloud.shopper.domain.ports.input.service.OrderApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(name = "/orders", produces = "application/vnd.api.v1+json")
@AllArgsConstructor
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        log.info("Creating order for customer: {} from supplier: {}", createOrderCommand.customerId(),
                createOrderCommand.supplierId());
        var createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        log.info("Order created with tracking id: {}", createOrderResponse.orderTrackingId());
        return ResponseEntity.ok(createOrderResponse);
    }
}
