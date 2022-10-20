package binarymash.cloud.shopper.domain.ports.output.repository;

import binarymash.cloud.shopper.domain.dto.track.TrackOrderQuery;
import binarymash.cloud.shopper.domain.valueobject.OrderId;
import binarymash.cloud.shopper.order.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackOrderQuery trackingId);
}
