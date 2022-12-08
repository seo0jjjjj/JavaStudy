package starcraftGame;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        final int MIN_M = 5; // 팀원수 최소 값
        final int MAX_M = 9; // 팀원수 최대 값
        final int MIN_R = 40; // 최소한 자원

        int numOfMembers = MIN_M + random.nextInt(MAX_M - MIN_M+1); // 팀원 맴버 수 결정(최대 9, 최소5)
        int gameResource = MIN_R + random.nextInt(40); // 팀별 자원 결정
        int pid = 20220000; // 플레이어에게 할당될 고유 번호

        // 팀 생성
        Team teamA = new Team("A", gameResource); // 빨강 팀
        Team teamB = new Team("B", gameResource); // 파랑 팀

        // 랜덤 이름 목록
        String[] names = {"임민석", "봉보경", "하정철", "송진경", "문세영",
                "황영희", "손문영", "하연숙", "서하영", "표홍식", "권윤옥", "문희아",
                "하승윤", "홍은용", "윤민정", "고윤재", "예병하", "표남기"}; // 플레이어 이름
        ArrayList<String> nameList = new ArrayList<>(Arrays.asList(names));// names 를 이용해 nameList 생성


        // 팀원 뽑기
        for (int i = 0; i < numOfMembers; i++) {
            teamA.choosePlayer(pid++, nameList);
            teamB.choosePlayer(pid++, nameList);
        }

        // 자원 분배
        teamA.divideResources();
        teamB.divideResources();

        // 자원을 모두 소모할 때까지 공격
        teamA.attack(teamB);
        teamB.attack(teamA);

        //게임 결과
        fightReview(teamA, teamB);
    }

    // 게임 결과
    private static void fightReview(Team teamA, Team teamB) {
        final String PARTY = "\uD83C\uDF89";

        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%40s\n", "게임 결과 안내");
        System.out.println("-----------------------------------------------------------------------------\n\t");

        if (teamA.getEnergy() > teamB.getEnergy()) {
            System.out.printf(PARTY + "  %s 팀이 %s 팀을 상대로 %d의 격차로 우승했습니다.\n", teamA.getName(), teamB.getName(), teamA.getEnergy() - teamB.getEnergy());
        } else if (teamA == teamB) {
            System.out.printf(PARTY + "  %s 팀과 %s 팀이 비겼습니다.\n", teamA.getName(), teamB.getName());
        } else {
            System.out.printf(PARTY + "  %s 팀이 %s 팀을 상대로 %d의 격차로 우승했습니다.\n", teamB.getName(), teamA.getName(), teamB.getEnergy() - teamA.getEnergy());
        }
    }
}
