package com.capstone.feelcheck.dto;

import com.capstone.feelcheck.model.Emotion;
import lombok.Data;

@Data
public class CommentDto {
    private Emotion emotion;
    private String nickname;
    private String comment;
}
