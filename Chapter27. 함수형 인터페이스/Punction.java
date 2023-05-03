import java.util.function.Function;

public class Punction {
    /*
    * Function 은 제너릭 T, R을 받아 매개변수 T 반환형 R 메서드
    * apply를 구현함.
    * 예제에서는 정수값을 통해, 생성된 문자열을 반환
     */
    public static void main(String[] args) {
        Function<Integer,String> function = num -> {
            StringBuffer str = new StringBuffer("시작!");
            for(int i =0; i<num; i++){
                str.append("안녕하세요. \t"+i);
            }
            return str.toString();
        };
        String str;
        str = sayHello(function,10);
        System.out.println(str);
    }

    private static String sayHello(Function<Integer, String> function, int i) {
    return function.apply(i);

    }
}
