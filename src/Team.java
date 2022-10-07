import java.util.ArrayList;
import java.util.Random;

public class Team {
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private String name;
    private int size, energy, resourse;
    private Random random;

    public Team(String name, int resourse) {
        this.name = name;
        this.resourse = resourse;
        random = new Random();
    }

    public static Player randomPlayer(Team enemy) {
        int num = (int) (Math.random() * 100) % enemy.playerList.size();
        return enemy.getPlayer(num);
    }

    //getter
    public int getSize() {
        return this.playerList.size();
    }

    public int getEnergy() {
        return energy;
    }

    public int getResourse() {
        return resourse;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getplayerList() {
        return playerList;
    }

    public Player getPlayer(int num) {
        return playerList.get(num);
    }

    //setter
    public void setEnergy(int e) {
        energy = e;
    }

    public void setResourse(int r) {
        resourse = r;
    }

    //선수 결정
    public void choosePlayer(int pid, ArrayList<String> nameList) {
        int num = random.nextInt(nameList.size()); // 이름 중 랜덤으로 번호를 고름.

        String name = nameList.get(num); // 고른 이름
        Player player = new Player(this, pid, name); // 플레이어 생성

        playerList.add(player); // 팀원 목록 리스트에 추가
        nameList.remove(num); // 뽑힌 사람은 전체 이름 리스트에서 삭제(중복방지)
    }

    //공격
    public void attack(Team Enemy) {
        // 출력
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("%40s\n", getName() + "팀의 공격 \uD83E\uDD4A");
        System.out.printf("%13s %13s %15s %13s %10s", "공격자", "피해자", "소모한 자원", "생산한 에너지", "팀 자원(" + resourse + ")");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");

        // 플레어에게 적을 주고 공격 명령
        for (Player p : playerList) p.attack(Enemy);

        // 결과 출력
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%50s %10d\n", "팀의 총 생성한 에너지 :", energy);
        System.out.println("----------------------------------------------------------------------------------");

    }

    // 플레이어 자원 초기화 함수
    public void divideResources() {
        int minimum_resourse = resourse / playerList.size(); // 최소 분배량 : n분의1개
        int remain_resourse = resourse - (minimum_resourse * playerList.size()); // 최소분배량을 제외한 나머지 랜덤 분배량
        for (Player p : playerList) { // 모든 플레이어에게 기본값 부여
            p.addResourse(minimum_resourse);
        }

        while (remain_resourse != 0) { // 모든 남은 분배량을 소모할 때까지
            for (Player p : playerList) { // 모든 플레이어에게
                int random_resourse = random.nextInt(remain_resourse + 1); //랜덤 갯수만큼 배분
                p.addResourse(random_resourse); // 추가로 부여할 랜덤 값
                remain_resourse -= random_resourse;
            }
        }
        print();
    }


    private void print() {
        int i = 0;
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%40s\n", getName() + "팀 맴버목록");
        System.out.printf("%5s %20s %10s %15s %10s", "번호", "PID", "이름", "종족", "자원(" + resourse + ")");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for (Player p : playerList) {
            System.out.format("%5s %20s %10s %15s %10s",
                    String.valueOf(i++), p.getId(), p.getName(), p.getTribeName(), String.valueOf(p.getResourse()));
            System.out.println();
        }

    }
}

class Player {
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
            TEAM.setResourse(team_resourse - resourseToUse);
            TEAM.setEnergy(team_energy + product_energy);

            System.out.printf("%6s(%7s) %6s(%7s) %10s(%4s) %10s(%4s) %10s\n",
                    NAME, TRIBE.name,
                    target.getName(), target.TRIBE.name,
                    resourseToUse, resourse,
                    product_energy, TEAM.getEnergy(),
                    TEAM.getResourse());
            resourse -= resourseToUse;

        }


    }
}


