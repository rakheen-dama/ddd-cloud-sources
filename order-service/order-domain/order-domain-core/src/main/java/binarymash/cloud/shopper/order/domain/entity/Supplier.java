package binarymash.cloud.shopper.order.domain.entity;

import binarymash.cloud.shopper.domain.entity.AggregateRoot;
import binarymash.cloud.shopper.domain.valueobject.SupplierId;

import java.util.List;

public class Supplier extends AggregateRoot<SupplierId> {

    private final List<Product> products;
    private boolean active;

    private Supplier(Builder builder) {
        super.setId(builder.supplierId);
        products = builder.products;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isActive() {
        return active;
    }

    public static final class Builder {
        private SupplierId supplierId;
        private List<Product> products;
        private boolean active;

        private Builder() {
        }

        public Builder restaurantId(SupplierId val) {
            supplierId = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Supplier build() {
            return new Supplier(this);
        }
    }
}
