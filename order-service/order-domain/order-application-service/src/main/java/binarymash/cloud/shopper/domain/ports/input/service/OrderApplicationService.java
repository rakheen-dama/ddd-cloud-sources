package binarymash.cloud.shopper.domain.ports.input.service;

import binarymash.cloud.shopper.domain.dto.create.CreateOrderCommand;
import binarymash.cloud.shopper.domain.dto.create.CreateOrderResponse;
import binarymash.cloud.shopper.domain.dto.track.TrackOrderQuery;
import binarymash.cloud.shopper.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
