package com.orange.securedfootballapi.controller;

import com.orange.securedfootballapi.entity.Team;
import com.orange.securedfootballapi.request.TeamRequest;
import com.orange.securedfootballapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Optional<Team> getTeam(@PathVariable int id) {
        return teamService.getTeam(id);
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody TeamRequest request) throws ChangeSetPersister.NotFoundException {
        Team response = teamService.createTeam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable int id, @RequestBody TeamRequest request) throws ChangeSetPersister.NotFoundException {
        Team response = teamService.updateTeam(id, request);
        return ResponseEntity.ok(response);
    }
}
