package com.orange.securedfootballapi.repository;

import com.orange.securedfootballapi.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
