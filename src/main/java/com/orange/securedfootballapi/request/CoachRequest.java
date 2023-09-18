package com.orange.securedfootballapi.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CoachRequest {
    private String name;
    private String username;
    private String password;
    private int teamId;
}
