package com.jvitorsaugusto.vetclinic_api.exceptions;

public class EmailAlreadyRegistered extends RuntimeException{

    public EmailAlreadyRegistered(String message) {
        super(message);
    }
}
