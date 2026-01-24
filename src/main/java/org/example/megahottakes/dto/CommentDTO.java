package org.example.megahottakes.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String content;
    private LocalDateTime createdDate;
    private Long hotTakeId;
    private Long authorId;
    private String authorName;
}
