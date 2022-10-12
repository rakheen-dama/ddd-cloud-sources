package binarymash.cloud.shopper.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ErrorDto {
    private final String code;
    private final String message;
}
