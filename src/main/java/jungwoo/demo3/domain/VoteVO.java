package jungwoo.demo3.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteVO {
    private Long id;
    private String title;
    private String description;
    private String creator;
    private LocalDate createdDate;
    private boolean completed;
}
