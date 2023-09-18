package com.orange.securedfootballapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private League league;
    @OneToOne(mappedBy = "team")
    private Coach coach;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
    @JsonIgnore
    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeMatches;
    @JsonIgnore
    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayMatches;
}
