import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class RemoveEf {
    /**
     * Prediate<T> 가 실제로 쓰이는 예제
     */
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-1,2,-3,4);
        list = new ArrayList<>(list);
        List<Double> dlist = Arrays.asList(0.0,1.1,2.2,-1.0);
        dlist = new ArrayList<>(dlist);

        Predicate<Number> p = n -> n.doubleValue() < 0.0;
        list.removeIf(p);
        dlist.removeIf(p);

        System.out.println(list);
        System.out.println(dlist);

        // Predicate<? super T> 이기 때문에, Predicate를 Integer,Double이 모두 상속하는 Number로 설정함.
        System.out.println(list.get(0) instanceof Number);
        System.out.println(dlist.get(0) instanceof Number);
    }



}
