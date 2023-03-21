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
                                        // ^ .select li가 된다 <li> 안에 <a> 들을 element에 저장한다 
                                        
        Elements elements = document.select("#NM_FAVORITE > div.group_nav > ul.list_nav.NM_FAVORITE_LIST > li > a");
        System.out.println(elements.size());

                                        // ^ 개발자 코드가 console에 출력 
        for(Element element:elements){
            // ^ Crawling :다른 사람이 만든 웹 페이지에서 필요한 정보를 가져온다 
            // ! 유명한 URL의 특정 태그와 옵션을 입력해주면 그곳의 필요한 정보를 가져다 준다 
            System.out.println(element.attr("href"));
                // ?https://dict.naver.com/
                // ?https://news.naver.com/
                // ?https://finance.naver.com/
                // ?https://land.naver.com/
                // ?https://map.naver.com/
                // ?https://vibe.naver.com/?from=naver_main&utm_source=naver_main&utm_medium=naver_main_pcweb&utm_campaign=naver_main_redirect
                // ?https://search.shopping.naver.com/book/home
                // ?https://comic.naver.com/
                // ! <li> 안의 <a> 안의 href attributeKey가 가지고 있는 것을 sysout 해준다 이런 결과가 나온다 
        }

    }
}
