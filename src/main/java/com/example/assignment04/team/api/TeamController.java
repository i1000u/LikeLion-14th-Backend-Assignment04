package com.example.assignment04.team.api;


import com.example.assignment04.common.response.code.SuccessCode;
import com.example.assignment04.common.template.ApiResTemplate;
import com.example.assignment04.player.api.dto.response.PlayerListResponseDto;
import com.example.assignment04.player.application.PlayerService;
import com.example.assignment04.team.api.dto.request.TeamSaveRequestDto;
import com.example.assignment04.team.api.dto.request.TeamUpdateRequesetDto;
import com.example.assignment04.team.api.dto.response.TeamInfoResponseDto;
import com.example.assignment04.team.application.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
@Tag(name = "팀 API", description = "팀관리 API")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    @PostMapping
    @Operation(summary = "팀 정보 저장",description = "팀 정보 post")
    public ApiResTemplate<Void> teamSave(@RequestBody @Valid TeamSaveRequestDto teamSaveRequestDto) {
        teamService.teamSave(teamSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.TEAM_SAVE_SUCCESS);
    }

    @Operation(summary = "팀 1개 조회",description = "팀 아이디 통해 조회")
    @GetMapping("/{teamId}")
    public ApiResTemplate<TeamInfoResponseDto> oneTeamFind(@PathVariable("teamId") Long teamId) {
        TeamInfoResponseDto teamInfoResponseDto = teamService.teamFindOne(teamId);
        return ApiResTemplate.successWithContent(SuccessCode.TEAM_FIND_SUCCESS, teamInfoResponseDto);
    }

    @GetMapping
    @Operation(summary = "팀 전체 조회",description = "팀 전체 조회")

    public ApiResTemplate<Page<TeamInfoResponseDto>> teamFind(
        @ParameterObject
        @PageableDefault(
        size = 10,
        sort ="teamId",
        direction = Sort.Direction.ASC)
        Pageable pageable)
    {
        Page<TeamInfoResponseDto> teamPage = teamService.teamFindAll(pageable);
        return ApiResTemplate.successWithContent(SuccessCode.TEAM_FIND_ALL_SUCCESS,teamPage);
    }

    @GetMapping("/{teamId}/players")
    @Operation(summary = "팀 내 소속 선수 조회",description = "팀 내 소속 선수 조회(소속 정보는 안뜨게 수정)")
    public ApiResTemplate<PlayerListResponseDto> findPlayersByTeam(@PathVariable("teamId") Long teamId) {
        PlayerListResponseDto playerListResponseDto = playerService.playerFindAllByTeam(teamId);
        return ApiResTemplate.successWithContent(SuccessCode.TEAM_FIND_PLAYERLIST, playerListResponseDto);

    }

    @PatchMapping("/{teamId}")
    @Operation(summary = "팀 정보 수정",description = "아이디 통한, 정보 수정")
    public ApiResTemplate<Void> teamUpdate(
            @PathVariable("teamId") Long teamId, @RequestBody @Valid TeamUpdateRequesetDto teamUpdateRequesetDto) {
        teamService.teamUpdate(teamId, teamUpdateRequesetDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.TEAM_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{teamId}")
    @Operation(summary = "팀 정보 삭제",description = "삭제")
    public ApiResTemplate<Void> teamDelete(
            @PathVariable("teamId") Long teamId){
        teamService.teamDelete(teamId);
        return ApiResTemplate.successWithNoContent(SuccessCode.TEAM_DELETE_SUCCESS);
        }

}


