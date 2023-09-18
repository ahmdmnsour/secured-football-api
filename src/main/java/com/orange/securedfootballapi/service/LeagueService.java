package com.orange.securedfootballapi.service;

import com.orange.securedfootballapi.entity.League;
import com.orange.securedfootballapi.repository.LeagueRepository;
import com.orange.securedfootballapi.request.LeagueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {
    private LeagueRepository leagueRepository;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    public Optional<League> getLeague(int id) {
        return leagueRepository.findById(id);
    }

    @Transactional
    public League createLeague(LeagueRequest request) {
        League league = new League();
        league.setName(request.getName());

        return leagueRepository.save(league);
    }

}
