package com.orange.securedfootballapi.service;

import com.orange.securedfootballapi.entity.Coach;
import com.orange.securedfootballapi.repository.CoachRepository;
import com.orange.securedfootballapi.repository.TeamRepository;
import com.orange.securedfootballapi.request.CoachRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {
    private CoachRepository coachRepository;
    private TeamRepository teamRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository, TeamRepository teamRepository) {
        this.coachRepository = coachRepository;
        this.teamRepository = teamRepository;
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Optional<Coach> getCoach(int id) {
        return coachRepository.findById(id);
    }

    @Transactional
    public Coach createCoach(CoachRequest request) throws ChangeSetPersister.NotFoundException {
        if (!teamRepository.existsById(request.getTeamId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Coach coach = new Coach();
        coach.setName(request.getName());
        coach.setTeam(teamRepository.findById(request.getTeamId()).get());
        coach.setAuthority("COACH");

        return coachRepository.save(coach);
    }

    @Transactional
    public Coach updateCoach(int id, CoachRequest request) throws ChangeSetPersister.NotFoundException {
        if (!coachRepository.existsById(id) || !teamRepository.existsById(request.getTeamId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Coach coach = coachRepository.findById(id).get();
        coach.setName(request.getName());
        coach.setTeam(teamRepository.findById(request.getTeamId()).get());
        coach.setAuthority("COACH");

        return coachRepository.save(coach);
    }
}
