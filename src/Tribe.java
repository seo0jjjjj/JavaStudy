import java.util.Random;

public class Tribe {
    public String attackName = "";
    protected String name;
    protected int r_MIN; // 에너지 사용에 드는 최대 자원
    protected int r_MAX;// 에너지 사용에 드는 최소 자원
    protected int use;
    protected String emoji;

    public Tribe() {
        name = "종족";
        emoji = "\uD83D\uDC7D";
        r_MAX = 10;
        r_MIN = 1;
    }


    //자원 R을 받아 생산된 에너지를 리턴 + 자식클래스에서 오버라이딩 불가능
    public final int useResourse(int r) {
        Random random = new Random();
        use = r_MIN + random.nextInt(r) % (r_MAX - r_MIN + 1); // 사용한 자원
        return use;
    }

    //자원 R로 애너지 생산 계산
    protected int makeEnergy(int energy) {
        return energy;
    }

    // 종족을 랜덤으로 정해주는 함수
    public static Tribe randomTribe() {
        int i = (int) ((Math.random() * 10) % 3); // 0~2의 랜덤값.

        Tribe tribe = new Tribe();
        switch (i) {
            case 0:
                tribe = new Terran();
                break;
            case 1:
                tribe = new Zerg();
                break;
            case 2:
                tribe = new Protoss();
                break;
        }
        return tribe;
    }

}

class Terran extends Tribe {
    public Terran() {
        attackName = "불 공격";
        emoji = "\uD83E\uDDD1";
        name = "Terran";
        r_MAX = 5;
        r_MIN = 1;
    }

    @Override
    // (자원)!
    protected int makeEnergy(int energy) {
        return fact(energy);
    }


    // 팩토리얼 함수
    private int fact(int num) {
        if (num <= 1)
            return num;
        else
            return fact(num - 1) * num;
    }
}

class Zerg extends Tribe {
    public Zerg() {
        attackName = "도끼 공격";
        name = "Zerg";
        emoji = "\uD83E\uDD82";
        r_MAX = 10;
        r_MIN = 1;
    }

    @Override
    // 자원의 제곱
    protected int makeEnergy(int energy) {
        return energy * energy;
    }
}

class Protoss extends Tribe {
    public Protoss() {
        attackName = "활 공격";
        name = "Protoss";
        emoji = "\uD83D\uDC7D";
        r_MAX = 5;
        r_MIN = 1;
    }

    @Override
    protected int makeEnergy(int energy) {
        int result = energy; // (자원)
        for (int i = 0; i < energy - 1; i++) {
            result *= energy;
        }

        return result;
    }
}