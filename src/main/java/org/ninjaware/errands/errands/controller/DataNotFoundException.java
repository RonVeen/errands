package org.ninjaware.errands.errands.controller;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String type, String id) {
        super(String.format("Could not find %s with id=%s", type, id));
    }
}
