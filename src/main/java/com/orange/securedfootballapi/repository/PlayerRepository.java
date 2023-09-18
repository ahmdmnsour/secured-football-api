package com.orange.securedfootballapi.repository;

import com.orange.securedfootballapi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
