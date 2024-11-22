package me.prexorjustin.trellobridge.exceptions;

public class NotAuthorizedException extends BadRequestException {

    public NotAuthorizedException() {
        super("API call error: Unauthorized access.");
    }
}
