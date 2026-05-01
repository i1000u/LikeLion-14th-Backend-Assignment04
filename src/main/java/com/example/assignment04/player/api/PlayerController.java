package com.example.assignment04.player.api;

import com.example.assignment04.common.response.code.SuccessCode;
import com.example.assignment04.common.template.ApiResTemplate;
import com.example.assignment04.player.api.dto.request.PlayerSaveRequestDto;
import com.example.assignment04.player.api.dto.request.PlayerUpdateRequestDto;
import com.example.assignment04.player.api.dto.response.PlayerDetailResponseDto;
import com.example.assignment04.player.application.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
@Tag(name = "선수 API", description = "선수관리 API")
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    @Operation(summary = "선수 정보 저장",description = "")
    public ApiResTemplate<Void> playerSave(@RequestBody @Valid PlayerSaveRequestDto playerSaveRequestDto) {
        playerService.playerSave(playerSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.PLAYER_SAVE_SUCCESS);
    }

    @GetMapping("/{playerId}")
    @Operation(summary = "선수 1명 조회",description = "")
    public ApiResTemplate<PlayerDetailResponseDto> playerFind(@PathVariable("playerId") Long playerId) {
        PlayerDetailResponseDto playerDetailResponseDto = playerService.playerFind(playerId);
        return ApiResTemplate.successWithContent(SuccessCode.PLAYER_FIND_SUCCESS,playerDetailResponseDto);
    }


    @PatchMapping("/{playerId}")
    @Operation(summary = "선수 정보 수정",description = "")
    public ApiResTemplate<Void> playerUpdate(
            @PathVariable("playerId") Long playerId, @RequestBody @Valid PlayerUpdateRequestDto playerUpdateRequestDto){
        playerService.playerUpdate(playerId, playerUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.PLAYER_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{playerId}")
    @Operation(summary = "선수 정보 삭제",description = "")
    public ApiResTemplate<Void>playerDelete(@PathVariable("playerId") Long playerId) {
        playerService.playerDelete(playerId);
        return ApiResTemplate.successWithNoContent(SuccessCode.PLAYER_DELETE_SUCCESS);
    }
}
