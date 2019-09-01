package ttajiri.todayeats.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.service.*;

@RestController
@RequestMapping(value = "today-eats")
public class HomeController {
    private HomeService service;

    public HomeController(HomeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<TodayEats> retrieveTodayEats() {
        return ResponseEntity.ok(service.retrieveTodayEats());
    }
}
