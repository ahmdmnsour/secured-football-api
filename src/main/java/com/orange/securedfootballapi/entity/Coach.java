package com.orange.securedfootballapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@PrimaryKeyJoinColumn(name = "coach_id")
@Table(name = "coaches")
public class Coach extends User {
    @JsonBackReference
    @OneToOne
    @JoinColumn(name="team_id", nullable=false)
    private Team team;
}
