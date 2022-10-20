package binarymash.cloud.shopper.domain.ports.output.repository;

import binarymash.cloud.shopper.order.domain.entity.Supplier;

import java.util.Optional;

public interface SupplierRepository {

    Optional<Supplier> findSupplier(Supplier supplier);
}
