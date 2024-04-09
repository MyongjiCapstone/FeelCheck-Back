package com.capstone.feelcheck.service;

import com.capstone.feelcheck.dto.RequestMusicDto;
import com.capstone.feelcheck.model.Music;
import com.jayway.jsonpath.JsonPath;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CrawlingService {
    private final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36";

    public List<Music> getMusicList(String songList) throws IOException {
        List<Music> musicList = new ArrayList<>();
        List<String> recommendList = List.of(songList.split("\\n"));
        for (String recommend:recommendList) {
            Music music = new Music();
            String encodedKeyword = URLEncoder.encode(recommend, "UTF-8");
            String crawlingURL = "https://www.google.com/search?q=" + encodedKeyword + "&tbm=vid";

            Document document = Jsoup.connect(crawlingURL).get();
 /*           Element images = document.selectFirst("#rso > div:nth-child(1) > div > div > div > div > div > div:nth-child(1) > div.iHxmLe > a > div > div > div.uhHOwf.BYbUcd > img");
            Element url = document.selectFirst("#rso > div:nth-child(1) > div > div > div > div > div > div:nth-child(1) > div.xe8e1b > div > div > span > a");
            music.setImage(images.attr("src"));
            music.setTitle(recommend);
            music.setUrl(url.attr("href"));
            musicList.add(music);*/
            Element url = document.selectFirst("#rso > div:nth-child(1) > div > div > div > div > div > div:nth-child(1) > div.xe8e1b > div > div > span > a");
            Elements images = document.select("script");
            for (Element image : images){
                String imageData = image.data();
                if (imageData.contains("data:image/jpeg;base64")){
                    int startIndex = imageData.indexOf("data:image/jpeg;base64");
                    int endIndex = imageData.indexOf("';var");
                    if (startIndex != -1) {
                        String base64Image = imageData.substring(startIndex, endIndex);
                        music.setImage(base64Image);
                        break;
                    }
                }
            }
            music.setTitle(recommend);
            music.setUrl(url.attr("href"));
            musicList.add(music);
/*            String crawlingURL = "https://www.youtube.com/results?search_query=" + encodedKeyword;
            Document document = Jsoup.connect(crawlingURL).userAgent(userAgent).get();
            Elements datas = document.select("script");
            for (Element data : datas) {
                for (DataNode node : data.dataNodes()) {
                    if (node.getWholeData().contains("var ytInitialData = ")) {
                        String nodeData = node.getWholeData();
                        nodeData = nodeData.replace("var ytInitialData = ", "");
                        nodeData = nodeData.replace(nodeData.substring(nodeData.length() - 1), "");
                        //test
                        Object test = JsonPath.read(nodeData, "$.contents.twoColumnSearchResultsRenderer.primaryContents.sectionListRenderer.contents[0].itemSectionRenderer.contents[0].videoRenderer");
                        System.out.println(test);
                        // 썸네일 이미지
                        Object thumbnail = JsonPath.read(nodeData, "$.contents.twoColumnSearchResultsRenderer.primaryContents.sectionListRenderer.contents[0].itemSectionRenderer.contents[0].videoRenderer.thumbnail.thumbnails[0].url");
                        // 유튜브 제목
                        String title = recommend;
                        // 유튜브 링크
                        Object link = "https://www.youtube.com" + JsonPath.read(nodeData, "$.contents.twoColumnSearchResultsRenderer.primaryContents.sectionListRenderer.contents[0].itemSectionRenderer.contents[0].videoRenderer.navigationEndpoint.commandMetadata.webCommandMetadata.url");
                        music.setImage((String)thumbnail);
                        music.setTitle(title);
                        music.setUrl((String)link);
                    }
                }
            }
            musicList.add(music);*/
        }
        return musicList;
    }
}
