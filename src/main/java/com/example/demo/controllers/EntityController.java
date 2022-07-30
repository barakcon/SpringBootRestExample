package com.example.demo.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.fetchers.Database;
import com.example.demo.models.Entity;
import com.example.demo.models.EntityToCreate;
import com.example.demo.models.SearchRequest;
import com.example.demo.fetchers.EntitiesMemoryDatabase;
import com.example.demo.metrics.MyMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.xml.crypto.Data;

@RestController
public class EntityController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private Database<Entity, EntityToCreate> database;

    @Autowired
    private MyMetrics myMetrics;

    @PostMapping("/entities")
    public Entity createEntity(@Valid @RequestBody EntityToCreate entity) {
        Entity created = database.create(entity);
        if (created == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return created;
    }

    @GetMapping("/entities")
    public List<Entity> getAll() {
        return database.getAll();
    }



    ////////////////////

    @PostMapping("/test")
    public Entity hello(@Valid @RequestBody SearchRequest searchRequest) {
        return new Entity(counter.incrementAndGet(), searchRequest.toString());
    }

    @GetMapping("/something")
    public Entity entity(@RequestParam(value = "name", defaultValue = "World") String name) {
        MyMetrics.counterA.increment();
        MyMetrics.counterB.increment(2);
        myMetrics.doSomeInc();
        return new Entity(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/test")
    public Entity testGetHello() {
        return new Entity(counter.incrementAndGet(), "Hello!");
    }

//    @GetMapping("/test-database")
//    public Entity testDatabase(@Valid @RequestParam(value = "n") int n) {
//        return new Entity(counter.incrementAndGet(), "Hello num " + (database.fetch(n)));
//    }


}