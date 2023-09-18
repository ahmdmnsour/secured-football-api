package com.orange.securedfootballapi.repository;

import com.orange.securedfootballapi.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
