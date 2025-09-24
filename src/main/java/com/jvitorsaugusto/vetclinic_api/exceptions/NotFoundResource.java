package com.jvitorsaugusto.vetclinic_api.exceptions;

public class NotFoundResource extends RuntimeException{

    public NotFoundResource(String message) {
        super(message);
    }
}
