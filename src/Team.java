import java.util.ArrayList;
import java.util.Random;

public class Team {
    private ArrayList<Player> playerList = new ArrayList<>();
    private final String name;
    private int energy, resource;
    private Random random;

    public Team(String name, int resource) {
        this.name = name;
        this.resource = resource;
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
    public int getResource() {return resource;}
    public String getName() {return name;}
    public ArrayList<Player> getplayerList() {
        return playerList;
    }
    public Player getPlayer(int num) {
        return playerList.get(num);
    }

    //setter
    public void setEnergy(int e) { energy = e;}
    public void setResourse(int r) {resource = r;}

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
        System.out.printf("%13s \t%2s %13s %13s %10s %10s", "공격자","무기", "피해자", "팀 자원(" + resource + ")","소모한 자원", "생산한 에너지") ;
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
        int minimum_resource = resource / playerList.size(); // 최소 분배량 : n분의1개
        int remain_resource = resource - (minimum_resource * playerList.size()); // 최소분배량을 제외한 나머지 랜덤 분배량
        for (Player p : playerList) { // 모든 플레이어에게 기본값 부여
            p.addResource(minimum_resource);
        }

        while (remain_resource != 0) { // 모든 남은 분배량을 소모할 때까지
            for (int i=0; i<playerList.size(); i++) { // 모든 플레이어에게
                random = new Random();
                int random_resource = random.nextInt(remain_resource + 1); //랜덤 갯수만큼 배분
                int j = random.nextInt(playerList.size()-1);
                playerList.get(j).addResource(random_resource); // 추가로 부여할 랜덤 값
                remain_resource -= random_resource;
            }
        }
        print();
    }
    //팀원과 자원 분배량 출력
    private void print() {
        int i = 0;
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%40s\n", getName() + "팀 맴버목록");
        System.out.printf("%5s %20s %10s %15s %10s", "번호", "PID", "이름", "종족", "자원(" + resource + ")");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for (Player p : playerList) {
            System.out.format("%5s %20s %10s %15s %10s",
                    i++, p.getId(), p.getName(), p.getTribeName(), String.valueOf(p.getResource()));
            System.out.println();
        }
        System.out.println();
    }
}
