package com.capstone.feelcheck.dto.openai;

import com.capstone.feelcheck.model.MusicGenre;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AiMusicRecommendRequestDto {
    private String model;
    private List<Message> messages;
    private Double temperature;

    public AiMusicRecommendRequestDto(String model, MusicGenre musicGenre, Double temperature) {
        this.model = model;
        this.temperature = temperature;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("system", "1. '가수 - 노래' 형식으로 반드시 답변하세요.\n" +
                "2. 사용자가 감정을 입력하면 해당 감정에 알맞는 유명한 노래 혹은 최신곡을 추천하세요.\n" +
                "3. 노래는 반드시 10개를 추천하세요.\n" +
                "\n" +
                "아래는 답변 예시입니다.\n" +
                "\n" +
                "Ailee - 첫눈처럼 너에게 가겠다\n" +
                "BTS - Butterfly\n" +
                "아이유 - 밤편지\n" +
                "폴킴 - 비\n" +
                "태연 - 그대라는 시\n" +
                "헤이즈 - 비가 내리면\n" +
                "AKMU - 얼음들\n" +
                "김동률 - 감사\n" +
                "선미 - 사이렌\n" +
                "Crush - 넌 (none)"));
        this.messages.add(new Message("user", musicGenre.name()));
    }
}