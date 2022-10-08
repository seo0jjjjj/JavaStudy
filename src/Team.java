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
    // 랜덤한 플레이어를 뽑는 함수
    public static Player randomPlayer(Team enemy) {
        int num = (int) (Math.random() * 100) % enemy.playerList.size();
        return enemy.getPlayer(num);
    }

    //getter
    public int getSize() { return this.playerList.size(); }
    public int getEnergy() { return energy;}
    public int getResourse() {return resourse;}
    public String getName() {return name;}
    public ArrayList<Player> getplayerList() {
        return playerList;
    }
    public Player getPlayer(int num) {
        return playerList.get(num);
    }

    //setter
    public void setEnergy(int e) { energy = e;}
    public void setResourse(int r) {resourse = r;}

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
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%50s\n", getName() + "팀의 공격 \uD83E\uDD4A");
        System.out.printf("%13s \t%2s %13s %13s %10s %10s", "공격자","무기", "피해자", "팀 자원(" + resourse + ")","소모한 자원", "생산한 에너지") ;
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------");

        // 플레어에게 적을 주고 공격 명령
        for (Player p : playerList) p.attack(Enemy);

        // 결과 출력
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%40s %10d\n", "팀의 총 생성한 에너지 ⚡ :",energy);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
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
    //팀원과 자원 분배량 출력
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
        System.out.println();
    }
}
