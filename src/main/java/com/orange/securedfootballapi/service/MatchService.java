package com.orange.securedfootballapi.service;

import com.orange.securedfootballapi.entity.Match;
import com.orange.securedfootballapi.entity.Player;
import com.orange.securedfootballapi.entity.Team;
import com.orange.securedfootballapi.repository.MatchRepository;
import com.orange.securedfootballapi.repository.TeamRepository;
import com.orange.securedfootballapi.request.MatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@RepositoryRestController
public class MatchService {
    private MatchRepository matchRepository;
    private TeamRepository teamRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository,
                        TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatch(int id) {
        return matchRepository.findById(id);
    }

    @Transactional
    public Match createMatch(@RequestBody MatchRequest request) throws ChangeSetPersister.NotFoundException {
        if (!teamRepository.existsById(request.getHomeTeamId()) || !teamRepository.existsById(request.getAwayTeamId())) {
            System.out.println(request.getAwayTeamId());
            throw new ChangeSetPersister.NotFoundException();
        }

        Random random = new Random();
        int homeGoals = random.nextInt(5);
        int awayGoals = random.nextInt(5);

        Match match = new Match();
        match.setMatchDate(new Date());
        match.setHomeTeam(teamRepository.findById(request.getHomeTeamId()).get());
        match.setAwayTeam(teamRepository.findById(request.getAwayTeamId()).get());
        match.setHomeTeamGoals(homeGoals);
        match.setAwayTeamGoals(awayGoals);
        match.setScorers(generateGoalScorers(match.getHomeTeam(), match.getAwayTeam(), homeGoals, awayGoals));

        return matchRepository.save(match);
    }

    private List<Player> generateGoalScorers(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals) {
        List<Player> goalScorers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < homeGoals; i++) {
            int playerIndex = random.nextInt(homeTeam.getPlayers().size());
            goalScorers.add(homeTeam.getPlayers().get(playerIndex));
            System.out.println(goalScorers);
        }

        for (int i = 0; i < awayGoals; i++) {
            int playerIndex = random.nextInt(awayTeam.getPlayers().size());
            goalScorers.add(awayTeam.getPlayers().get(playerIndex));
        }

        return goalScorers;
    }
}
