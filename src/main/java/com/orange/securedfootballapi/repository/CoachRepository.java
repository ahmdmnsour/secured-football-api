package com.orange.securedfootballapi.repository;

import com.orange.securedfootballapi.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
}
