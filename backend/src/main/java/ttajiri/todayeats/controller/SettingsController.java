package ttajiri.todayeats.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.service.*;

import java.net.*;
import java.util.*;

@RestController
@CrossOrigin(exposedHeaders = { "location" })
@RequestMapping(value = "/settings")
public class SettingsController {
    private SettingsService service;

    public SettingsController(SettingsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> retrieveCategories() {
        return ResponseEntity.ok(service.retrieveCategories());
    }

    @GetMapping(path = "/mine")
    public ResponseEntity<MyCategory> retrieveMyCategory() {
        return ResponseEntity.ok(service.retrieveMyCategory());
    }

    @PostMapping
    public ResponseEntity<URI> registerCategory(@RequestBody MyCategory category) {
        service.registerCategory(category);

        // @formatter:off
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                  .path("/{id}")
                                                  .buildAndExpand(category.getId())
                                                  .toUri();
        // @formatter:on
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/eats")
    public ResponseEntity<List<TodayEats>> retrieveEats() {
        return ResponseEntity.ok(service.retrieveEats());
    }

    @PostMapping(path = "/eats")
    public ResponseEntity<URI> registerEats(@RequestBody TodayEats eats) {
        var id = service.registerEats(eats);

        // @formatter:off
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                  .path("/{id}")
                                                  .buildAndExpand(id)
                                                  .toUri();
        // @formatter:on

        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/eats")
    public ResponseEntity<URI> updateEats(@RequestBody TodayEats eats) {
        service.updateEats(eats);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/eats/{id}")
    public ResponseEntity<URI> deleteEats(@PathVariable Integer id) {
        service.deleteEats(id);

        return ResponseEntity.noContent().build();
    }
}
