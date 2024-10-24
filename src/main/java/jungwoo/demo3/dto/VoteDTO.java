package jungwoo.demo3.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Data

@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {

    private Long id;
    private String title;
    private List<String> options;

}