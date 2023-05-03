import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Fredicate {
    /*
    * Predicate 함수형 인터페이스 예제
    * Predicate는 T의 매개변수를 통해, bool형을 반환하는 함수형 인터페이스
     */
    public static void main(String[] args) {
        List list = Arrays.asList(1, 5, 7, 9, 11, 12);
        int s;

        s = sum(n->n%2 == 0, list); // 짝수 제거
        System.out.println(s);

        s = sum(n-> n%2 !=0,list); // 홀수 제거
        System.out.println(s);
    }

    public static int sum(IntPredicate p, List<Integer> lst) {
        int s = 0;
        for (int n : lst) {
            if (p.test(n)) // predicate의 test() 메서드를 통하여 람다형으로 if문구현
                s += n;
        }
        return s;
    }
}