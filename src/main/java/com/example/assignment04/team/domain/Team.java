package com.example.assignment04.team.domain;

import com.example.assignment04.player.domain.Player;
import com.example.assignment04.team.api.dto.request.TeamUpdateRequesetDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long teamId;

    private String location;
    private String name;

    @Builder
    public Team(String location, String name) {
        this.location = location;
        this.name = name;
    }

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }

public void update(TeamUpdateRequesetDto teamUpdateRequesetDto) {
    this.name = teamUpdateRequesetDto.name();
    this.location = teamUpdateRequesetDto.location();
}




}
