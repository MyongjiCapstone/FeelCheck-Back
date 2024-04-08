package com.capstone.feelcheck.controller;

import com.capstone.feelcheck.dto.RequestMusicDto;
import com.capstone.feelcheck.dto.ResponseDto;
import com.capstone.feelcheck.model.Music;
import com.capstone.feelcheck.service.CrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MusicController {
    private final CrawlingService crawlingService;
    @Autowired
    public MusicController(CrawlingService crawlingService) {
        this.crawlingService = crawlingService;
    }

    @PostMapping("/api/music")
    public ResponseDto getMusicList(@RequestBody RequestMusicDto requestMusicDto) throws IOException {
        List<Music> musicList = crawlingService.getMusicList(requestMusicDto.getSongList());
        return new ResponseDto(HttpStatus.OK.value(), "음악 데이터 목록입니다.", musicList);
    }

}
