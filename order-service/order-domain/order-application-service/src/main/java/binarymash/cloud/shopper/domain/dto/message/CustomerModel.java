package binarymash.cloud.shopper.domain.dto.message;

import lombok.Builder;

@Builder
public record CustomerModel(String id, String username, String firstName, String lastName) {
}
