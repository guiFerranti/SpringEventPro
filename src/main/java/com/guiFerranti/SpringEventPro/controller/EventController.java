package com.guiFerranti.SpringEventPro.controller;

import com.guiFerranti.SpringEventPro.domain.event.EventData;
import com.guiFerranti.SpringEventPro.domain.event.EventUpdateData;
import com.guiFerranti.SpringEventPro.service.AuthService;
import com.guiFerranti.SpringEventPro.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {


    @Autowired
    private EventService eventService;

    @Autowired
    private AuthService authService;


    @PostMapping
    public ResponseEntity createEvent(@Valid @RequestBody EventData dados) {

        eventService.createEvent(dados);

        return ResponseEntity.ok(dados);
    }

    @GetMapping
    public ResponseEntity listEvents() {

        return ResponseEntity.ok(eventService.listEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity eventDetails(@PathVariable long id) {

        return ResponseEntity.ok(eventService.eventDetails(id));
    }

    @PutMapping
    public ResponseEntity eventUpdate(@Valid @RequestBody EventUpdateData dados) {

        return ResponseEntity.ok(eventService.eventUpdate(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eventDelete(@PathVariable long id) {
        eventService.eventDelete(id);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/{id}/register")
    public ResponseEntity eventRegisterUser(@PathVariable long id, HttpServletRequest request) {
        eventService.subscribeEvent(id, request);
        return ResponseEntity.ok("User registrado");
    }

    @GetMapping("/{id}/registrations")
    public ResponseEntity eventUserRegistrations(@PathVariable long id, HttpServletRequest request) {
        authService.ownerOrAdminOnly(id, request);

        return ResponseEntity.ok(eventService.getRegistrations(id));
    }

}
