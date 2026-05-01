package com.example.assignment04.player.application;

import com.example.assignment04.common.exception.BusinessException;
import com.example.assignment04.common.response.code.ErrorCode;
import com.example.assignment04.player.api.dto.request.PlayerSaveRequestDto;
import com.example.assignment04.player.api.dto.request.PlayerUpdateRequestDto;
import com.example.assignment04.player.api.dto.response.PlayerDetailResponseDto;
import com.example.assignment04.player.api.dto.response.PlayerInfoResponseDto;
import com.example.assignment04.player.api.dto.response.PlayerListResponseDto;
import com.example.assignment04.player.domain.Player;
import com.example.assignment04.player.domain.repository.PlayerRepository;
import com.example.assignment04.team.domain.Team;
import com.example.assignment04.team.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerService {
        private final PlayerRepository playerRepository;
        private final TeamRepository teamRepository;

        @Transactional
        public void playerSave(PlayerSaveRequestDto playerSaveRequestDto) {
            Team team = teamRepository.findById(playerSaveRequestDto.teamId()).orElseThrow(() -> new BusinessException(
                ErrorCode.TEAM_NOT_FOUND_EXCEPTION, "선수를 등록할 팀이 없습니다. teamId: " + playerSaveRequestDto.teamId()
            ));

            Player player = Player.builder()
                    .name(playerSaveRequestDto.name())
                    .uniformNumber(playerSaveRequestDto.uniformNumber())
                    .position(playerSaveRequestDto.position())
                    .team(team)
                    .build();


            playerRepository.save(player);
        }
        public PlayerDetailResponseDto playerFind(Long playerId) {
            Player player = playerRepository.findById(playerId).orElseThrow(()-> new BusinessException(
                ErrorCode.PLAYER_NOT_FOUND_EXCEPTION,
                ErrorCode.PLAYER_NOT_FOUND_EXCEPTION.getMessage() + " playerId: " + playerId
            ));
            return PlayerDetailResponseDto.from(player);
        }

        public PlayerListResponseDto playerFindAllByTeam(Long teamId) {

            Team team = teamRepository.findById(teamId).orElseThrow(() -> new BusinessException(
                ErrorCode.TEAM_NOT_FOUND_EXCEPTION,
                "해당 팀 id가 잘못되었습니다. teamId: " + teamId
            ));
            List<Player> players = playerRepository.findByTeam(team);

            List<PlayerInfoResponseDto> playerInfoResponseDtos = players.stream()
                    .map(PlayerInfoResponseDto::from)
                    .toList();

            return PlayerListResponseDto.from(team.getName(), playerInfoResponseDtos);
        }


        @Transactional
        public void playerUpdate(Long playerId, PlayerUpdateRequestDto playerUpdateRequestDto) {
            Player player = playerRepository.findById(playerId).orElseThrow(() -> new BusinessException(
                ErrorCode.PLAYER_NOT_FOUND_EXCEPTION,
                ErrorCode.PLAYER_NOT_FOUND_EXCEPTION.getMessage() + " playerId: " + playerId
            ));
            Team team = teamRepository.findById(playerUpdateRequestDto.teamId()).orElseThrow(() -> new BusinessException(
                ErrorCode.TEAM_NOT_FOUND_EXCEPTION,
                "선수를 이동할 팀이 없습니다. teamId: " + playerUpdateRequestDto.teamId()
            ));
            player.update(playerUpdateRequestDto);
            player.setTeam(team);
        }

        @Transactional
        public void playerDelete(Long playerId) {
            Player player = playerRepository.findById(playerId).orElseThrow(()-> new BusinessException(
                ErrorCode.PLAYER_NOT_FOUND_EXCEPTION,
                ErrorCode.PLAYER_NOT_FOUND_EXCEPTION.getMessage() + " playerId: " + playerId
            ));
            playerRepository.delete(player);
        }


    }
