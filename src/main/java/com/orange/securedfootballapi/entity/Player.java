package com.orange.securedfootballapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Setter
@Getter
@ToString
@Entity
@PrimaryKeyJoinColumn(name = "player_id")
@Table(name = "players")
public class Player extends User {
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    @JsonIgnore
    @ManyToMany(mappedBy = "scorers")
    private Collection<Match> matches;
}
