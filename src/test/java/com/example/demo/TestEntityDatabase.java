package com.example.demo;

import com.example.demo.fetchers.Database;
import com.example.demo.models.Entity;
import com.example.demo.models.EntityToCreate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class TestEntityDatabase implements Database<Entity, EntityToCreate> {

    @Override
    public Entity create(EntityToCreate input) {
        return null;
    }

    @Override
    public List<Entity> getAll() {
        return null;
    }

    @Override
    public Entity get(Long id) {
        return null;
    }
}
