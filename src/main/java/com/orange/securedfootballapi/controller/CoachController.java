package com.orange.securedfootballapi.controller;

import com.orange.securedfootballapi.entity.Coach;
import com.orange.securedfootballapi.request.CoachRequest;
import com.orange.securedfootballapi.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {
    private CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping
    public ResponseEntity<List<Coach>> getAllCoaches() {
        return ResponseEntity.status(HttpStatus.CREATED).body(coachService.getAllCoaches());
    }

    @GetMapping("/{id}")
    public Optional<Coach> getCoach(@PathVariable int id) {
        return coachService.getCoach(id);
    }

    @PostMapping
    public ResponseEntity<Coach> createCoach(@RequestBody CoachRequest request) throws ChangeSetPersister.NotFoundException {
        Coach response = coachService.createCoach(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable int id, @RequestBody CoachRequest request) throws ChangeSetPersister.NotFoundException {
        Coach response = coachService.updateCoach(id, request);
        return ResponseEntity.ok(response);
    }
}
