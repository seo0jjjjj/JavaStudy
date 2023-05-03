# Chapter 27: 함수형 인터페이스

## 함수형 인터페이스

- 추상 메소드가 딱 하나만 존재하는 인터페이스

<aside>
💡 메소드 몸체가 **둘 이상의 문장**으로 이뤄져 있거나, **매개변수의 수가 둘 이상인 경우**에는 각각 중괄호와 소괄호의 생략이 불가능하다.

</aside>

```java
@FunctionalInterface 
interface Printable {
   void print(String s);  // 매개변수 하나, 반환형 void
}

class OneParamNoReturn {
   public static void main(String[] args) {
      Printable p;
      p = (String s) -> { System.out.println(s); };    // 줄임 없는 표현
      p.print("Lambda exp one.");
}
```

### 어노테이션

`@FunctionalInterface` : 함수형 인터페이스의 조건을 갖추었는지에 대한 검사를 컴파일러에게 요청

## 27-2. 정의되어 있는 함수형 인터페이스

| 클래스 | 함수 | 설명 | 매개변수 | 반환 |
| --- | --- | --- | --- | --- |
| Predicate<T> | boolean test(T t) | 전달 인자를 근거로 참 또는 거짓을 반환 | T | bool |
| Supplier<T> | T get() | 메소드 호출 시 무엇인가를 제공함 |  | T |
| Consumer<T> | void accept(T t) | 무엇인자를 받아 들이기만 함 | T |  |
| Function<T, R> | R apply(T t) | 입출력 출력이 있음(수학적으로는 함수) | T | R |

## 1. Predicate<T>

- 전달 인자를 근거로 참 또는 거짓을 반환

```java
public static int sum(Predicate<Integer> p, List<Integer> lst) {
   int s = 0;
   for(int n : lst) {
      ***if(p.test(n))***
         s += n;
   }
   return s;
}

public static void main(String[] args) {
   List<Integer> list = Arrays.asList(1, 5, 7, 9, 11, 12);
   int s;

   s = sum(***n -> n%2 == 0***, list);
   System.out.println("짝수 합: " + s);

   s = sum(n -> n%2 != 0, list);
   System.out.println("홀수 합: " + s);
}
```

### Predicate<T>를 구체화하고 다양화 한 인터페이스들

- 박싱, 언박싱 과정이 필요 없어짐

| IntPredicate | boolean test(int value) |
| --- | --- |
| LongPredicate | boolean test(long value) |
| DoublePredicate | test(double value) |
| BiPredicate<T, U> | boolean test(T t, U u) |

## 2. Supplier<T>

- 메소드 호출 시 무엇인가를 제공함

```java
public static List<Integer> makeIntList(**Supplier<Integer>** s, int n) {
   List<Integer> list = new ArrayList<>();
   for(int i = 0; i < n; i++)
      list.add(s.get());   // 난수를 생성해 담는다.
   return list;
}

public static void main(String[] args) {
   **Supplier<Integer> spr = () -> {
      Random rand = new Random();
      return rand.nextInt(50);
   };**
   
   List<Integer> list = makeIntList(spr, 5);
   System.out.println(list);

   list = makeIntList(spr, 10);
   System.out.println(list);
}
```

### Supplier<T>를 구체화하고 다양화 한 인터페이스들

| IntSupplier | int getAsInt() |
| --- | --- |
| LongSupplier | long getAsLong() |
| DoubleSupplier | double getAsDouble() |
| BooleanSupplier | boolean getAsBoolean() |

## 3. Consumer<T>

- 무엇인자를 받아 들이기만 함.
    
    ```java
    class ConsumerDemo {
       public static void main(String[] args) {
          Consumer<String> c = s -> System.out.println(s);
          c.accept("Pineapple");       // 출력이라는 결과를 보임
          c.accept("Strawberry");
       }
    }
    ```
    

### Consumer<T>를 구체화하고 다양화 한 인터페이스들

| IntConsumer | void accept(int value) |
| --- | --- |
| ObjIntConsumer<T> | void accept(T t, int value) |
| LongConsumer | void accept(long value) |
| ObjLongConsumer<T> | void accept(T t, long value) |
| DoubleConsumer | void accept(double value) |
| ObjDoubleConsumer<T> | void accept(T t, double value) |
| BiConsumer<T, U> | void accept(T t, U u) |

## 4. Function<T, R>

```java
class FunctionDemo {
   public static void main(String[] args) {
      Function<String, Integer> f = s -> s.length();
      System.out.println(f.apply("Robot"));
      System.out.println(f.apply("System"));
   }
}
```

| IntToDoubleFunction | double applyAsDouble(int value) |
| --- | --- |
| DoubleToIntFunction | int applyAsInt(double value) |
| IntUnaryOperator | int applyAsInt(int operand) |
| DoubleUnaryOperator | double applyAsDouble(double operand) |
| BiFunction<T, U, R> | R apply(T t, U u) |
| IntFunction<R> | R apply(int value) |
| DoubleFunction<R> | R apply(double value) |
| ToIntFunction<T> | int applyAsInt(T value) |
| ToDoubleFunction<T> | double applyAsDouble(T value) |
| ToIntBiFunction<T, U> | int applyAsInt(T t, U u) |
| ToDoubleBiFunction<T, U> | double applyAsDouble(T t, U u) |

## 기타 함수형 인터페이스

| Function<T, R> | R apply(T t) |
| --- | --- |
| BiFunction<T, U, R> | R apply(T t, U u) |
| 위의 함수는 반환/매개변수가 같기 때문에  | 아래로 대체가 가능함. |
| UnaryOperator<T> | T apply(T t) |
| BinaryOperator<T> | T apply(T t1, T t2) |

## removeIf (Collection<E>의 인터페이스 디폴트 메서드)

- 실제 Predicate가 사용되는 예임.

```java
Collection<E> 인터페이스의 디폴트 메소드
default boolean removeIf(Predicate<? super E> filter)

ArrayList<Integer> 인스턴스의 removeIf
public boolean removeIf(Predicate<? super Integer> filter예제예
```

### 예제

```java
public static void main(String[] args) {
   List<Integer> ls1 = Arrays.asList(1, -2, 3, -4, 5);
   ls1 = new ArrayList<>(ls1);

   List<Double> ls2 = Arrays.asList(-1.1, 2.2, 3.3, -4.4, 5.5);
   ls2 = new ArrayList<>(ls2);

   Predicate<Number> p = n -> n.doubleValue() < 0.0;    // 삭제의 조건
   ls1.removeIf(p);    // List<Integer> 인스턴스에 전달
   ls2.removeIf(p);    // List<Double> 인스턴스에 전달

   System.out.println(ls1);
   System.out.println(ls2);
}
```
