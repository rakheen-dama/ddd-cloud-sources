package binarymash.cloud.shopper.domain;

import binarymash.cloud.shopper.domain.dto.create.CreateOrderCommand;
import binarymash.cloud.shopper.domain.dto.create.CreateOrderResponse;
import binarymash.cloud.shopper.domain.dto.track.TrackOrderQuery;
import binarymash.cloud.shopper.domain.dto.track.TrackOrderResponse;
import binarymash.cloud.shopper.domain.ports.input.service.OrderApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OrderApplicationServiceImpl implements OrderApplicationService {

    @Override
    public CreateOrderResponse createOrder(final CreateOrderCommand createOrderCommand) {

        return null;
    }

    @Override
    public TrackOrderResponse trackOrder(final TrackOrderQuery trackOrderQuery) {
        return null;
    }
}
