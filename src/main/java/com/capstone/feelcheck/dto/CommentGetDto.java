package com.capstone.feelcheck.dto;

import com.capstone.feelcheck.model.Emotion;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
public class CommentGetDto {
    private Emotion emotion;
    private int page;
}
