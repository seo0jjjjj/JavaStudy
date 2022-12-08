package starcraftGame;

import starcraftGame.Tribes.Protoss;
import starcraftGame.Tribes.Terran;
import starcraftGame.Tribes.Zerg;

import java.util.Random;

public class Tribe {
    public String attackName = "";
    public String name;
    protected int r_MIN; // 에너지 사용에 드는 최소 자원
    protected int r_MAX;// 에너지 사용에 드는 최대 자원
    protected int use;
    public String emoji;

    public Tribe() {
        name = "종족";
        emoji = "\uD83D\uDC7D";
        r_MAX = 10;
        r_MIN = 1;
    }

    //종족별 자원 소모량 계산하는 함수 + 자식클래스에서 오버라이딩 불가능
    public final int useResource(int r) {
        Random random = new Random();
        use = r_MIN + random.nextInt(r) % (r_MAX - r_MIN + 1); // 사용한 자원
        return use;
    }

    //자원 R을 사용하여 애너지 생산 계산
    public int makeEnergy(int energy) {
        return energy;
    }

    // 종족을 랜덤으로 정해주는 정적 함수
    public static Tribe randomTribe() {
        int i = (int) ((Math.random() * 10) % 3); // 0~2의 랜덤값.

        return switch (i) {
            case 0 -> new Terran();
            case 1 -> new Zerg();
            case 2 -> new Protoss();
            default -> new Tribe();
        };
    }

}
