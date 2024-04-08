package com.capstone.feelcheck.dto.openai;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AiCommentRequestDto {
    private String model;
    private List<Message> messages;
    private Double temperature;

    public AiCommentRequestDto(String model, String summary, Double temperature) {
        this.model = model;
        this.temperature = temperature;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("system", "너는 한줄평을 남기는 AI야. 사용자에게서 요약된 일기가 입력되면, 존댓말, 상냥한 말투로 30자 정도 대답해줘. 마지막엔 이모티콘 하나도 넣어줘"));
        this.messages.add(new Message("user", summary));
    }
}