import Tribes.Tribe;

public class Player {
    private final int PID; // 고유 번호
    private final String NAME; // 이름
    private final Team TEAM; // 속한 팀
    private int resourse, energy; // 자원과 에너지
    private final Tribe TRIBE; // 종족

    public Player(Team team, int pid, String name) {
        // 초기화
        TEAM = team;
        PID = pid;
        NAME = name;
        resourse = 0;
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
    public int getResourse() {
        return resourse;
    }


    public void addResourse(int resourse) {
        this.resourse += resourse;
    }

    public void attack(Team enemy) {
        // 출력
        int resourseToUse, product_energy, team_resourse, team_energy;
        if (resourse == 0) return; // 할당을 못받았을 경우
        // 공격 과정
        while (resourse != 0) {
            String name = new String(NAME);
            resourseToUse = TRIBE.useResourse(resourse); // 사용할 자원양
            product_energy = TRIBE.makeEnergy(resourseToUse); // 에너지 생성
            team_resourse = TEAM.getResourse();
            team_energy = TEAM.getEnergy();

            Player target = Team.randomPlayer(enemy); // 타겟 적

            energy += product_energy;

            TEAM.setEnergy(team_energy + product_energy);

            System.out.printf("%6s(%7s)\t%2s\t%6s(%7s) %10s %12s %10s(%4s) \n",
                    NAME, TRIBE.name,TRIBE.emoji,
                    target.getName(), target.TRIBE.name,
                    TEAM.getResourse(), resourseToUse,
                    product_energy, TEAM.getEnergy());

            resourse -= resourseToUse;
            TEAM.setResourse(team_resourse - resourseToUse);
        }


    }
}
