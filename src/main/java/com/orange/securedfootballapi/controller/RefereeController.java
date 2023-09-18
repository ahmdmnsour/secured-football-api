package com.orange.securedfootballapi.controller;

import com.orange.securedfootballapi.entity.Referee;
import com.orange.securedfootballapi.request.RefereeRequest;
import com.orange.securedfootballapi.service.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/referees")
public class RefereeController {
    private RefereeService refereeService;

    @Autowired
    public RefereeController(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @GetMapping
    public List<Referee> getAllReferees() {
        return refereeService.getAllReferees();
    }

    @GetMapping("/{id}")
    public Optional<Referee> getReferee(@PathVariable int id) {
        return refereeService.getReferee(id);
    }

    @PostMapping
    public ResponseEntity<Referee> createReferee(@RequestBody RefereeRequest request) throws ChangeSetPersister.NotFoundException {
        Referee response = refereeService.createReferee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Referee> updateReferee(@PathVariable int id, @RequestBody RefereeRequest request) throws ChangeSetPersister.NotFoundException {
        Referee response = refereeService.updateReferee(id, request);
        return ResponseEntity.ok(response);
    }
}
