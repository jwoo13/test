package jungwoo.demo3.domain;

import lombok.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteVO {
    private Long id;
    private String title;
    private List<String> options;

//    private Long creatorUserId;

}