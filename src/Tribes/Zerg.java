package Tribes;

public class Zerg extends Tribe {
    public Zerg() {
        attackName = "도끼 공격";
        name = "Zerg";
        emoji = "\uD83E\uDE93";
        r_MAX = 10;
        r_MIN = 1;
    }
    // 자원의 제곱
    @Override
    public int makeEnergy(int energy) {
        return energy * energy;
    }
}