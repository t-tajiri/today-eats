package ttajiri.todayeats.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.repository.dto.*;
import ttajiri.todayeats.service.*;

import java.net.*;
import java.util.*;

@RestController
@CrossOrigin
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
}
