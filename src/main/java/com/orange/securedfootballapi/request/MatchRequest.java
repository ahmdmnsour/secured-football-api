package com.orange.securedfootballapi.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MatchRequest {
    private int homeTeamId;
    private int awayTeamId;
}
