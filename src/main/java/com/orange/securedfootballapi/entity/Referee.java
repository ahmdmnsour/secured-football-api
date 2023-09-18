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
@PrimaryKeyJoinColumn(name = "referee_id")
@Table(name = "referees")
public class Referee extends User {
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="league_id", nullable=false)
    private League league;
}
