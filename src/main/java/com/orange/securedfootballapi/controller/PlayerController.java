package com.orange.securedfootballapi.controller;

import com.orange.securedfootballapi.entity.Player;
import com.orange.securedfootballapi.request.PlayerRequest;
import com.orange.securedfootballapi.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Optional<Player> getPlayer(@PathVariable int id) {
        return playerService.getPlayer(id);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody PlayerRequest request) throws ChangeSetPersister.NotFoundException {
        Player response = playerService.createPlayer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestBody PlayerRequest request) throws ChangeSetPersister.NotFoundException {
        Player response = playerService.updatePlayer(id, request);
        return ResponseEntity.ok(response);
    }
}
