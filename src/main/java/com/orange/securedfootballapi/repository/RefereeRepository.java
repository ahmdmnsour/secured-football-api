package com.orange.securedfootballapi.repository;

import com.orange.securedfootballapi.entity.Referee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefereeRepository extends JpaRepository<Referee, Integer> {
}
