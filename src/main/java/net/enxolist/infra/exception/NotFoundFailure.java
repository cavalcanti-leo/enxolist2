package net.enxolist.infra.exception;

public class NotFoundFailure extends RuntimeException {
    public NotFoundFailure(String message) {
        super(message);
    }
}
