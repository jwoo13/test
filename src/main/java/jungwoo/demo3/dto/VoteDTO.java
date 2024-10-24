package jungwoo.demo3.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data

@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {

    private Long id;
    private String title;
    private List<String> options;

}