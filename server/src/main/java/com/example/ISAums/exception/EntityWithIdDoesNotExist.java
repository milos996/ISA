package com.example.ISAums.exception;

import java.util.UUID;

public class EntityWithIdDoesNotExist extends CustomException {
    public EntityWithIdDoesNotExist(String entity, UUID id) {
        super("Entity " + entity + " with this id " + id + " does not exist!");
    }
}
