package net.enxolist.application.dto.failure;

public record FailureResponse(
        String msg,
        Throwable cause
) {
}
