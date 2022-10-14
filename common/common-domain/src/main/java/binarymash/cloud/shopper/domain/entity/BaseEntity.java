package binarymash.cloud.shopper.domain.entity;

import java.util.Objects;
import java.util.UUID;

public abstract class BaseEntity<ID> {

    public static final String FAILURE_MESSAGE_DELIMITER = ",";

    private ID id;
    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }

    public UUID newUUID() {
        return UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
