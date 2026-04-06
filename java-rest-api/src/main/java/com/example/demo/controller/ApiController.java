package com.example.demo.controller;

import com.example.demo.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/items")
public class ApiController {

    private List<Item> items = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(items);
    }

    // ✅ AJUSTADO COM VALIDAÇÃO (REGRA DE NEGÓCIO)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> createItem(@RequestBody Item item) {

        if (item.getName() == null || item.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (item.getDescription() == null || item.getDescription().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        item.setId(counter.incrementAndGet());
        items.add(item);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return items.stream()
                .filter(item -> id.equals(item.getId()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ CORRIGIDO (endpoint certo)
    @GetMapping("/name/{name}")
    public ResponseEntity<Item> getItemByName(@PathVariable String name) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ CORRIGIDO (endpoint certo)
    @GetMapping("/count")
    public int countItems() {
        return items.size();
    }
}