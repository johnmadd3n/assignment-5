import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimesMain {
    List<Integer> list = new ArrayList<>();
    boolean isPrime(Integer number) {
        return IntStream.rangeClosed(2, number / 2).noneMatch(i -> number % i == 0);
    }
    List<Integer> findNPrimes(Integer n) {
        return Stream.iterate(2, e -> e + 1)
                .filter(this::isPrime)
                .limit(n)
                .toList();
    }




    public static void main(String[] args) {
        PrimesMainTest.isPrimeTest( );
        PrimesMainTest.findNPrimesTest();

    }
}
