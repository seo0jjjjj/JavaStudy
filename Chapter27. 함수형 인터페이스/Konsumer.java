import java.util.function.Consumer;

public class Konsumer {
    /**
     * Consumer는 반환이 없는 함수형 인터페이스 클래스임.
     * 매개변수 T를 받아 반환값이 없는 람다식을 구현할때 사용.
     * */

    public static void main(String[] args) {
        Consumer<String> c = s -> System.out.println(s);
        c.accept("test");
        c.accept("hello World");
    }
}
