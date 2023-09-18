package com.orange.securedfootballapi.repository;

import com.orange.securedfootballapi.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer> {
}
