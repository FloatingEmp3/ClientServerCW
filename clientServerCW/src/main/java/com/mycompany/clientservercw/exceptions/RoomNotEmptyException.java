package com.mycompany.clientservercw.exceptions;

//Eduardo Lamasanu w2078922

public class RoomNotEmptyException extends RuntimeException {

    public RoomNotEmptyException(String message) {
        super(message);
    }
}