package com.orange.securedfootballapi.controller;

import com.orange.securedfootballapi.entity.Match;
import com.orange.securedfootballapi.request.MatchRequest;
import com.orange.securedfootballapi.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public Optional<Match> getMatch(@PathVariable int id) {
        return matchService.getMatch(id);
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody MatchRequest request) throws ChangeSetPersister.NotFoundException {
        Match response = matchService.createMatch(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @PutMapping("/matches/{id}")
//    public ResponseEntity<MatchResponse> updateMatch(@PathVariable int id, @RequestBody MatchRequest request) throws ChangeSetPersister.NotFoundException {
//        MatchResponse response = matchService.updateMatch(id, request);
//        return ResponseEntity.ok(response);
//    }
}
