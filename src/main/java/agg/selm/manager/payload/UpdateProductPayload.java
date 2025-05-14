package agg.selm.manager.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductPayload(
        @NotNull
        @Size(min = 3, max = 30)
        String name,
        @Size(max = 300)
        String details) {
}
