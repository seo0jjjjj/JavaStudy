package starcraftGame.Tribes;


import starcraftGame.Tribe;

public class Protoss extends Tribe {
    public Protoss() {
        attackName = "활 공격";
        name = "Protoss";
        emoji = "\uD83C\uDFF9 ";
        r_MAX = 5;
        r_MIN = 1;
    }

    @Override
    public int makeEnergy(int energy) {
        int result = energy; // (자원)
        for (int i = 0; i < energy - 1; i++) {
            result *= energy;
        }

        return result;
    }
}