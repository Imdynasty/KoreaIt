package org.koreait.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BoardStat {
   // @Scheduled(fixedRate = 3000)//3초마다
    @Scheduled(cron="0 0 1 * * *")//1시마다 통계측정 //Groupby 가지고 로직 짜기
    public void makeStat(){
        System.out.println("3초마다실행!");
    }
}
