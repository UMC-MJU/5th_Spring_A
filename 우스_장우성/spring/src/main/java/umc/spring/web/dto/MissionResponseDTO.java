package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addResultDTO{
        Long missionId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionPreViewListDTO {
        List<MissionResponseDTO.MissionPreviewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFist;
        Boolean isLast;

    }
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionPreviewDTO {
        LocalDate deadline;
        Integer minimum_amount;
        Integer reward_point;
        LocalDate createdAt;
    }
}
