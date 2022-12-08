package starcraftGame;

public class Player {
    private final int PID; // 고유 번호
    private final String NAME; // 이름
    private final Team TEAM; // 속한 팀
    private int resource, energy; // 자원과 에너지
    private final Tribe TRIBE; // 종족

    public Player(Team team, int pid, String name) {
        // 초기화
        TEAM = team;
        PID = pid;
        NAME = name;
        resource = 0;
        energy = 0;
        // 랜덤한 종족 부여
        TRIBE = Tribe.randomTribe();
    }

    //getter
    public int getId() {
        return PID;
    }
    public String getName() {
        return NAME;
    }
    public String getTribeName() {
        return TRIBE.name;
    }
    public String getTribeEmoji() {
        return TRIBE.emoji;
    }
    public int getResource() {
        return resource;
    }


    public void addResource(int resource) {
        this.resource += resource;
    }

    public void attack(Team enemy) {
        // 출력
        int resourceToUse, product_energy, team_resource, team_energy;
        if (resource == 0) return; // 할당을 못받았을 경우
        // 공격 과정
        while (resource != 0) {
            resourceToUse = TRIBE.useResource(resource); // 사용할 자원양
            product_energy = TRIBE.makeEnergy(resourceToUse); // 에너지 생성
            team_resource = TEAM.getResource();
            team_energy = TEAM.getEnergy();
            energy += product_energy; // 생산한 에너지를 플레이어 총 에너지에 더함.

            Player target = Team.randomPlayer(enemy); // 타겟 적 설정
            TEAM.setEnergy(team_energy + product_energy); // 팀 총 에너지에 추가

            System.out.printf("%6s(%7s)\t%2s\t%6s(%7s) %10s %12s %10s(%4s) \n",
                    NAME, TRIBE.name,TRIBE.emoji,
                    target.getName(), target.TRIBE.name,
                    TEAM.getResource(), resourceToUse,
                    product_energy, TEAM.getEnergy());

            resource -= resourceToUse; // 사용한 자원 삭제
            TEAM.setResourse(team_resource - resourceToUse); // 사용한 팀 자원 삭제
        }


    }
}
