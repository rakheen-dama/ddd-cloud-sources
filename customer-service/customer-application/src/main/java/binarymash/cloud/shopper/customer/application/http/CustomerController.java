package binarymash.cloud.shopper.customer.application.http;

import binarymash.cloud.shopper.customer.domain.create.CreateCustomerCommand;
import binarymash.cloud.shopper.customer.domain.create.CreateCustomerResponse;
import binarymash.cloud.shopper.customer.domain.ports.input.CustomerApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/customers", produces = "application/vnd.api.v1+json")
public class CustomerController {

    private final CustomerApplicationService customerApplicationService;

    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerCommand createCustomerCommand) {
        log.info("Creating customer with username: {}", createCustomerCommand.getUsername());
        return ResponseEntity.ok(customerApplicationService.createCustomer(createCustomerCommand));
    }
}
