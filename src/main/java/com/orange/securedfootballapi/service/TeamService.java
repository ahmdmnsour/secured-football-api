package com.orange.securedfootballapi.service;

import com.orange.securedfootballapi.entity.Team;
import com.orange.securedfootballapi.repository.LeagueRepository;
import com.orange.securedfootballapi.repository.TeamRepository;
import com.orange.securedfootballapi.request.TeamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    private LeagueRepository leagueRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, LeagueRepository leagueRepository) {
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeam(int id) {
        return teamRepository.findById(id);
    }

    @Transactional
    public Team createTeam(TeamRequest request) throws ChangeSetPersister.NotFoundException {
        if (!leagueRepository.existsById(request.getLeagueId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Team team = new Team();
        team.setName(request.getName());
        team.setLeague(leagueRepository.findById(request.getLeagueId()).get());

        return teamRepository.save(team);
    }

    @Transactional
    public Team updateTeam(int id, TeamRequest request) throws ChangeSetPersister.NotFoundException {
        if (!teamRepository.existsById(id) || !leagueRepository.existsById(request.getLeagueId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Team team = teamRepository.findById(id).get();
        team.setName(request.getName());
        team.setLeague(leagueRepository.findById(request.getLeagueId()).get());

        return teamRepository.save(team);
    }
}
