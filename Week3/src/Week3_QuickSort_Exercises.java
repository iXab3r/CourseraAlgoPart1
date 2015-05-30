import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Week3_QuickSort_Exercises {
    public static void main(String[] args) {

        Q1();
        Q2();
    }

    private static void Q1() {
        Comparable[] _input = ParseArray("41 16 13 56 66 98 18 50 48 54 35 78");
        System.out.printf("Q1 2-way Input: %s\n\n", Arrays.toString(_input).replace(',',' '));
        QuickSort.partition(_input,0,_input.length-1);
        System.out.printf("Q1 2-way Result: %s\n\n", Arrays.toString(_input).replace(',',' '));

    }

    private static void Q2() {
        Comparable[] _input = ParseArray("A A A A B A A A B B A A ");
        System.out.printf("Q1 3-way Input: %s\n\n", Arrays.toString(_input).replace(',',' '));
        QuickSort.partition(_input, 0, _input.length - 1);
        System.out.printf("Q1 3-way Result: %s\n\n", Arrays.toString(_input).replace(',',' '));

    }

    private static Comparable[] ParseArray(String _s)
    {
        _s = _s.trim();
        String[] splitted = _s.split(" ");



        Comparable[] result = new Comparable[splitted.length];
        for (int i = 0; i < splitted.length; i++)
        {
            splitted[i] = splitted[i].trim();
            try {
                result[i] = Integer.parseInt(splitted[i]);
            }catch (Exception ex)
            {
                result[i] = splitted[i];
            }
        }
        return result;
    }

}


