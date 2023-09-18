package com.orange.securedfootballapi.controller;

import com.orange.securedfootballapi.entity.Manager;
import com.orange.securedfootballapi.request.ManagerRequest;
import com.orange.securedfootballapi.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    private ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public List<Manager> getAllManagers() {
        return managerService.getAllManagers();
    }

    @GetMapping("/{id}")
    public Optional<Manager> getManager(@PathVariable int id) {
        return managerService.getManager(id);
    }

    @PostMapping
    public ResponseEntity<Manager> createManager(@RequestBody ManagerRequest request) throws ChangeSetPersister.NotFoundException {
        Manager response = managerService.createManager(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@PathVariable int id, @RequestBody ManagerRequest request) throws ChangeSetPersister.NotFoundException {
        Manager response = managerService.updateManager(id, request);
        return ResponseEntity.ok(response);
    }
}
