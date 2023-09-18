package com.orange.securedfootballapi.service;

import com.orange.securedfootballapi.entity.Referee;
import com.orange.securedfootballapi.repository.LeagueRepository;
import com.orange.securedfootballapi.repository.RefereeRepository;
import com.orange.securedfootballapi.request.RefereeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RefereeService {
    private RefereeRepository refereeRepository;
    private LeagueRepository leagueRepository;

    @Autowired
    public RefereeService(RefereeRepository refereeRepository, LeagueRepository leagueRepository) {
        this.refereeRepository = refereeRepository;
        this.leagueRepository = leagueRepository;
    }

    public List<Referee> getAllReferees() {
        return refereeRepository.findAll();
    }

    public Optional<Referee> getReferee(int id) {
        return refereeRepository.findById(id);
    }

    @Transactional
    public Referee createReferee(RefereeRequest request) throws ChangeSetPersister.NotFoundException {
        if (!leagueRepository.existsById(request.getLeagueId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Referee referee = new Referee();
        referee.setName(request.getName());
        referee.setLeague(leagueRepository.findById(request.getLeagueId()).get());
        referee.setAuthority("REFEREE");

        return refereeRepository.save(referee);
    }

    @Transactional
    public Referee updateReferee(int id, RefereeRequest request) throws ChangeSetPersister.NotFoundException {
        if (!refereeRepository.existsById(id) || !leagueRepository.existsById(request.getLeagueId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Referee referee = refereeRepository.findById(id).get();
        referee.setName(request.getName());
        referee.setLeague(leagueRepository.findById(request.getLeagueId()).get());
        referee.setAuthority("REFEREE");

        return refereeRepository.save(referee);    }
}
