package Tribes;

public class Terran extends Tribe {
    public Terran() {
        attackName = "불 공격";
        emoji = "\uD83D\uDD25 ";
        name = "Terran";
        r_MAX = 5;
        r_MIN = 1;
    }

    @Override
    public int makeEnergy(int energy) {
        return fact(energy);
    }// (자원)!

    // 팩토리얼 함수
    private int fact(int num) {
        if (num <= 1)
            return num;
        else
            return fact(num - 1) * num;
    }
}