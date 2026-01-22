package org.example.megahottakes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotTakeDTO {
    private Long id;
    private String content;
    private String heatScore;
    private String authorName;
}
