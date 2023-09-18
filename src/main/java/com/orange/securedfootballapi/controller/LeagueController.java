package com.orange.securedfootballapi.controller;

import com.orange.securedfootballapi.entity.League;
import com.orange.securedfootballapi.request.LeagueRequest;
import com.orange.securedfootballapi.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {
    private LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping
    public List<League> getAllLeagues() {
        return leagueService.getAllLeagues();
    }

    @GetMapping("/{id}")
    public Optional<League> getLeague(@PathVariable int id) {
        return leagueService.getLeague(id);
    }

    @PostMapping
    public ResponseEntity<League> createCoach(@RequestBody LeagueRequest request) {
        League response = leagueService.createLeague(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
