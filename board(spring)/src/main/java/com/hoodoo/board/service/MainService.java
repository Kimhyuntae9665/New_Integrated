package com.hoodoo.board.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    
    
    // ^ 1초 마다 반복되는 고정 딜레이 작업 
    // ^ 1000 은 1000ms 를 의미한다 
    // ? 스케줄 작업이 끝나는 시간을 기준으로 실행 
    // @Scheduled(fixedDelay = 1000)
    // public void scheduleFixedDelay(){
    //     System.out.println("고정 딜레이 작업 : "+System.currentTimeMillis()/1000);
    // }

    // ? 스케줄 작업이 시작하는 시간 기준으로 실행 
    // @Scheduled(fixedRate = 1000)
    // public void scheduleFixedRate(){
    //     System.out.println("시작 고정 딜에이 작업 : "+System.currentTimeMillis()/1000);
    // }

    // ! Crons 중요 많이 사용한다 
    // ! @Scheduled(cron = "2 * * * * ?") 는 HH:MM:02 초가 되면 실행된다 
    // ! @Scheduled(cron = "2 22 * * * ?") 는 HH:22:02 가 되면 실행 된다 
    @Scheduled(cron = "2 * * * * *")
    public void scheduleCronJob(){
        try{
            crawling();
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void crawling() throws Exception {

                                        // ^ 원하는 URL에 연결  
        Document document = Jsoup.connect("https://naver.com").get();
        
                                        // ^ 개발자 도구 (F12) 누르고 div class="**" 되있는 곳에서 우클릭 copy selector 누르면 이렇게 복사 된다 
        Elements elements = document.select("#NM_NEWSSTAND_MY_THUMB > div._NM_UI_PAGE_CONTAINER > div > div > div.thumb_area");

                                        // ^ 개발자 코드가 console에 출력 
        for(Element element:elements){
            System.out.println(element.attr("data-pid"));
        }

    }
}
