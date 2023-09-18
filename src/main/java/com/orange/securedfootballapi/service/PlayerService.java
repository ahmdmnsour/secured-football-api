package com.orange.securedfootballapi.service;

import com.orange.securedfootballapi.entity.Player;
import com.orange.securedfootballapi.repository.PlayerRepository;
import com.orange.securedfootballapi.repository.TeamRepository;
import com.orange.securedfootballapi.request.PlayerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayer(int id) {
        return playerRepository.findById(id);
    }

    @Transactional
    public Player createPlayer(PlayerRequest request) throws ChangeSetPersister.NotFoundException {
        if (!teamRepository.existsById(request.getTeamId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Player player = new Player();
        player.setName(request.getName());
        player.setTeam(teamRepository.findById(request.getTeamId()).get());
        player.setAuthority("PLAYER");

        return playerRepository.save(player);
    }

    @Transactional
    public Player updatePlayer(int id, PlayerRequest request) throws ChangeSetPersister.NotFoundException {
        if (!playerRepository.existsById(id) || !teamRepository.existsById(request.getTeamId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Player player = playerRepository.findById(id).get();
        player.setName(request.getName());
        player.setTeam(teamRepository.findById(request.getTeamId()).get());
        player.setAuthority("PLAYER");

        return playerRepository.save(player);
    }
}
