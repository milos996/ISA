package com.example.ISAums.exception;

public class EntityAlreadyExistsException extends CustomException{
    public EntityAlreadyExistsException(String name) {
        super("Entity with this name " + name + " already exist");
    }
}
