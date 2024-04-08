package com.capstone.feelcheck.dto.openai;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AiSummaryRequestDto {
    private String model;
    private List<Message> messages;
    private Double temperature;

    public AiSummaryRequestDto(String model, List<String> weekDiary, Double temperature) {
        this.model = model;
        this.temperature = temperature;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("system", "너는 일기를 주간 단위로 요약해주는 AI야. 사용자에게서 3~7개의 일기가 입력되면 그 일기를 종합해서 전반적으로 어떤 한주였는지 주요사건을 중심으로 최소 70글자 정도로 요약해줘."));
        for (int i=0; i<weekDiary.size(); i++){
            this.messages.add(new Message("user", weekDiary.get(i)));
        }
    }
}