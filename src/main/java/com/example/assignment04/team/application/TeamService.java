package com.example.assignment04.team.application;

import com.example.assignment04.common.exception.BusinessException;
import com.example.assignment04.common.response.code.ErrorCode;
import com.example.assignment04.team.api.dto.request.TeamSaveRequestDto;
import com.example.assignment04.team.api.dto.request.TeamUpdateRequesetDto;
import com.example.assignment04.team.api.dto.response.TeamInfoResponseDto;
import com.example.assignment04.team.api.dto.response.TeamListResponseDto;
import com.example.assignment04.team.domain.Team;
import com.example.assignment04.team.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional( readOnly = true)
public class TeamService {
    private final TeamRepository teamRepository;

    @Transactional
    public void teamSave(TeamSaveRequestDto teamSaveRequestDto) {
        Team team = Team.builder()
                .location(teamSaveRequestDto.location())
                .name(teamSaveRequestDto.name())
                .build();
        teamRepository.save(team);
    }

    public TeamInfoResponseDto teamFindOne(Long teamId) {
        Team team = teamRepository.findById(teamId)
            .orElseThrow(() -> new BusinessException(
                ErrorCode.TEAM_NOT_FOUND_EXCEPTION,
                ErrorCode.TEAM_NOT_FOUND_EXCEPTION.getMessage() + " teamId: " + teamId
            ));
        return TeamInfoResponseDto.from(team);
    }

    public Page<TeamInfoResponseDto> teamFindAll(Pageable pageable) {
        Page<Team> teams = teamRepository.findAll(pageable);
        if (teams.isEmpty()) {
            throw new BusinessException(ErrorCode.TEAM_NOT_FOUND_EXCEPTION, "등록된 팀이 없습니다.") ;
        }
        List<TeamInfoResponseDto> teamInfoResponseDtoList = teams.stream()
                .map(TeamInfoResponseDto::from)
                .toList();
    return teams.map(TeamInfoResponseDto:: from);

    }

    @Transactional
    public void teamUpdate(Long teamId, TeamUpdateRequesetDto teamUpdateRequesetDto) {
     Team team = teamRepository.findById(teamId).orElseThrow(() -> new BusinessException(
         ErrorCode.TEAM_NOT_FOUND_EXCEPTION,
         ErrorCode.TEAM_NOT_FOUND_EXCEPTION.getMessage() + " teamId: " + teamId
     ));
    team.update(teamUpdateRequesetDto);
    }

    @Transactional
    public void teamDelete(Long teamId) {
    Team team = teamRepository.findById(teamId).orElseThrow(() -> new BusinessException(
        ErrorCode.TEAM_NOT_FOUND_EXCEPTION,
        ErrorCode.TEAM_NOT_FOUND_EXCEPTION.getMessage() + " teamId: " + teamId
    ));
    teamRepository.delete(team);
    }



}
