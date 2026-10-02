package ir.maktabsharif147.jdbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcApplication {

    static void main() {
        Collection<Serializable> numbers = new ArrayList<>();
        numbers.add(156D);
        numbers.add(-5D);
        numbers.add(1500D);
//        findMaxAndPrint(numbers);


        add(numbers, 1500L);
    }

    //    lower bound wildcard
    static void add(Collection<? super Number> numbers, Number n) {
        numbers.add(n);
    }
    //    wildcard
//    static <T extends Number> void findMaxAndPrint(Collection<T> numbers) {

    //    bounded wildcard | upper bound wildcard
    static void findMaxAndPrint(Collection<? extends Number> numbers) {
        Double max = Double.MIN_VALUE;
        for (Number number : numbers) {
            double elementDoubleValue = number.doubleValue();
            if (max < elementDoubleValue) {
                max = elementDoubleValue;
            }
        }
        System.out.println(max);
    }
}
