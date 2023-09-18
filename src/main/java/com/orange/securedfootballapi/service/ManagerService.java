package com.orange.securedfootballapi.service;

import com.orange.securedfootballapi.entity.Manager;
import com.orange.securedfootballapi.repository.LeagueRepository;
import com.orange.securedfootballapi.repository.ManagerRepository;
import com.orange.securedfootballapi.request.ManagerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {
    private ManagerRepository managerRepository;
    private LeagueRepository leagueRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository, LeagueRepository leagueRepository) {
        this.managerRepository = managerRepository;
        this.leagueRepository = leagueRepository;
    }

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public Optional<Manager> getManager(int id) {
        return managerRepository.findById(id);
    }

    @Transactional
    public Manager createManager(ManagerRequest request) throws ChangeSetPersister.NotFoundException {
        if (!leagueRepository.existsById(request.getLeagueId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Manager manager = new Manager();
        manager.setName(request.getName());
        manager.setLeague(leagueRepository.findById(request.getLeagueId()).get());
        manager.setAuthority("MANAGER");

        return managerRepository.save(manager);
    }

    @Transactional
    public Manager updateManager(int id, ManagerRequest request) throws ChangeSetPersister.NotFoundException {
        if (!managerRepository.existsById(id) || !leagueRepository.existsById(request.getLeagueId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Manager manager = managerRepository.findById(id).get();
        manager.setName(request.getName());
        manager.setLeague(leagueRepository.findById(request.getLeagueId()).get());
        manager.setAuthority("MANAGER");

        return managerRepository.save(manager);
    }
}
