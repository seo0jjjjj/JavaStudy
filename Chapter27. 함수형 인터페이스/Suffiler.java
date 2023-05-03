import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Suffiler {

    /*
    Suffiler는
     */
    public static void main(String[] args){
        IntSupplier spr = ()-> {
            Random random = new Random();
            return random.nextInt(50);
        };

        List<Integer> list = makeIntList(spr, 5);
        System.out.println(list);

        List<Integer> list2 = makeIntList(spr, 10);
        System.out.println(list2);
    }

    private static List<Integer> makeIntList(IntSupplier spr, int i) {
        List<Integer> list = new ArrayList<>();
        for(int n = 0; n < i; n++) {
            list.add(spr.getAsInt());
        }
        return list;
    }
}
