package me.prexorjustin.trellobridge.exceptions;

public class NotFoundException extends BadRequestException {
    public NotFoundException(String message) {
        super(message);
    }
}
