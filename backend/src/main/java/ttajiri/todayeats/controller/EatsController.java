package ttajiri.todayeats.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.service.*;

@RestController
@CrossOrigin
@RequestMapping(value = "today-eats")
public class EatsController {
    private EatsService service;

    public EatsController(EatsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<TodayEats> retrieveTodayEats() {
        return ResponseEntity.ok(service.retrieveTodayEats());
    }
}
