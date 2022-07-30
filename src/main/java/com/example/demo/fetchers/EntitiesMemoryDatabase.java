package com.example.demo.fetchers;

import com.example.demo.models.Entity;
import com.example.demo.models.EntityToCreate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class EntitiesMemoryDatabase implements Database<Entity, EntityToCreate> {
    private final ConcurrentMap<Long, Entity> data;
    private final AtomicLong idGenerator;
    private final int num;

    public EntitiesMemoryDatabase() {
        this.num = 1880;
        data = new ConcurrentHashMap<>();
        idGenerator = new AtomicLong();
    }

    // Returns the created entity, or null if it was unsuccessful
    public Entity create(EntityToCreate entityToCreate) {
        Long id = idGenerator.getAndIncrement();
        Entity entity = new Entity(id, entityToCreate.getContent());
        data.put(id, entity);
        return entity;
    }

    public List<Entity> getAll() {
        return new ArrayList<>(this.data.values());
    }

    public Entity get(Long id) {
        return data.get(id);
    }


    // returns the entity we put
//    public Entity put(Entity entity) {
//        Entity previousValue = data.put(entity.getId(), entity);
//        return entity;
//    }
    public int fetch(int mul) {
        return this.num * mul;
    }
}
