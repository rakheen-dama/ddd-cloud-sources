package binarymash.cloud.shopper.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public record CustomerModel(String id, String username, String firstName, String lastName) { }
