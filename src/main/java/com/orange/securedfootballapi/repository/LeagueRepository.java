package com.orange.securedfootballapi.repository;

import com.orange.securedfootballapi.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Integer> {
}
