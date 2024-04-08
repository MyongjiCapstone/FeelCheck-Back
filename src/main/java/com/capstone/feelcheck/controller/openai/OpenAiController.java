package com.capstone.feelcheck.controller.openai;

import com.capstone.feelcheck.dto.ResponseDto;
import com.capstone.feelcheck.dto.openai.AiCommentRequestDto;
import com.capstone.feelcheck.dto.openai.AiMusicRecommendRequestDto;
import com.capstone.feelcheck.dto.openai.AiSummaryRequestDto;
import com.capstone.feelcheck.dto.openai.AiResponseDto;
import com.capstone.feelcheck.model.Music;
import com.capstone.feelcheck.model.MusicGenre;
import com.capstone.feelcheck.service.CrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
public class OpenAiController {
    @Value("${openai.model}")
    private String model;
    @Value("${openai.api.url}")
    private String apiURL;
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/openapi/summary")
    public ResponseDto aiDiarySummary(@RequestBody List<String> weekDiary){
        if (weekDiary.isEmpty()) {
            return new ResponseDto(HttpStatus.NOT_FOUND.value(), "일기를 불러오지 못하였습니다.", null);
        }
        if (weekDiary.size()<3){
            return new ResponseDto(HttpStatus.NOT_FOUND.value(), "최소 3개 이상의 일기가 필요합니다.", null);
        }
        AiSummaryRequestDto request = new AiSummaryRequestDto(model, weekDiary, 0.0);
        AiResponseDto aiResponseDto =  restTemplate.postForObject(apiURL, request, AiResponseDto.class);
        if (aiResponseDto == null || aiResponseDto.getChoices() == null || aiResponseDto.getChoices().isEmpty()) {
            return new ResponseDto(HttpStatus.SERVICE_UNAVAILABLE.value(), "AI에게 답변을 받지 못했습니다.", null);
        }
        return new ResponseDto(HttpStatus.OK.value(), "일기가 성공적으로 요약되었습니다.", aiResponseDto.getChoices().get(0).getMessage().getContent());
    }
    @PostMapping("/openapi/comment")
    public ResponseDto aiDiaryComment(@RequestBody String summary){
        if (summary == null){
            return new ResponseDto(HttpStatus.NOT_FOUND.value(), "요약된 일기가 없습니다.", null);
        }
        AiCommentRequestDto request = new AiCommentRequestDto(model, summary, 0.0);
        AiResponseDto aiResponseDto =  restTemplate.postForObject(apiURL, request, AiResponseDto.class);
        if (aiResponseDto == null || aiResponseDto.getChoices() == null || aiResponseDto.getChoices().isEmpty()) {
            return new ResponseDto(HttpStatus.SERVICE_UNAVAILABLE.value(), "AI에게 답변을 받지 못했습니다.", null);
        }
        return new ResponseDto(HttpStatus.OK.value(), "AI가 한줄평을 남겼습니다.", aiResponseDto.getChoices().get(0).getMessage().getContent());
    }

    @GetMapping("/openapi/musicRecommend")
    public ResponseDto aiMusicRecommend(MusicGenre musicGenre) {
        if (musicGenre == null) {
            return new ResponseDto(HttpStatus.NOT_FOUND.value(), "노래 분위기가 입력되지 않았습니다.", null);
        }
        AiMusicRecommendRequestDto request = new AiMusicRecommendRequestDto(model, musicGenre, 0.0);
        AiResponseDto aiResponseDto =  restTemplate.postForObject(apiURL, request, AiResponseDto.class);
        if (aiResponseDto == null || aiResponseDto.getChoices() == null || aiResponseDto.getChoices().isEmpty()) {
            return new ResponseDto(HttpStatus.SERVICE_UNAVAILABLE.value(), "AI에게 답변을 받지 못했습니다.", null);
        }
        return new ResponseDto(HttpStatus.OK.value(), "AI의 음악 추천 리스트입니다.", aiResponseDto.getChoices().get(0).getMessage().getContent());
    }
}