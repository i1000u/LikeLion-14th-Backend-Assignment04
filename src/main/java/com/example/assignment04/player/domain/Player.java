package com.example.assignment04.player.domain;

import com.example.assignment04.player.api.dto.request.PlayerUpdateRequestDto;
import com.example.assignment04.team.domain.Team;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long playerId;

    private String name;
    private int uniformNumber;
    private String position;

    @Builder
    public Player(String name, int uniformNumber, String position, Team team) {
        this.name = name;
        this.uniformNumber = uniformNumber;
        this.position = position;
        this.team = team;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public void setTeam(Team team) {
        this.team = team;
    }

    public void update(PlayerUpdateRequestDto playerUpdateRequestDto) {
        this.name = playerUpdateRequestDto.name();
        this.position = playerUpdateRequestDto.position();
        this.uniformNumber = playerUpdateRequestDto.uniformNumber();
    }
}
