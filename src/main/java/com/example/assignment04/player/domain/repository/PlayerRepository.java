package com.example.assignment04.player.domain.repository;

import com.example.assignment04.player.domain.Player;
import com.example.assignment04.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeam(Team team);
}
