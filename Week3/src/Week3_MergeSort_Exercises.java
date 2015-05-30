import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

public class Week3_MergeSort_Exercises {
    public static void main(String[] args) {

        Q1();
        Q2BU();
    }

    private static void Q1() {
        Integer[] _input = ParseArray("81 51 95 91 47 28 69 19 57 99 53 56 ");
        System.out.printf("Q1 TD Input: %s\n\n", Arrays.toString(_input).replace(',',' '));
        MergeSort.sort(_input);
    }

    private static void Q2BU()
    {
        Integer[] _input = ParseArray("19 41 16 34 66 48 73 28 97 98 ");
        System.out.printf("Q2BU Input: %s\n\n", Arrays.toString(_input).replace(',',' '));
        MergeBUSort.sort(_input);
    }

    private static void Q2() {
        DumpSortedTable("link   hash   link   link   link   hash   \n" +
                "    type   link   miss   type   miss   left   \n" +
                "    miss   load   type   miss   time   link   \n" +
                "    time   miss   hash   time   type   load   \n" +
                "    load   time   load   hash   hash   miss   \n" +
                "    hash   type   time   load   left   next   \n" +
                "    tree   left   tree   left   load   push   \n" +
                "    left   sink   left   tree   tree   sink   \n" +
                "    sink   tree   sink   sink   next   size   \n" +
                "    next   next   next   next   push   time   \n" +
                "    push   push   push   push   sink   tree   \n" +
                "    size   size   size   size   size   type");
    }

    private static Integer[] ParseArray(String _s)
    {
        _s = _s.trim();
        String[] splitted = _s.split(" ");

        Integer[] result = new Integer[splitted.length];
        for (int i = 0; i < splitted.length; i++)
        {
            result[i] = Integer.parseInt(splitted[i]);
        }
        return result;
    }

    private static void DumpSortedTable(String _s)
    {
        _s = _s.trim();
        _s = _s.replace("\t","");
        String[] splittedByCr = _s.split("\n");

        for (int i = 0; i < splittedByCr.length; i++)
        {
            splittedByCr[i] = splittedByCr[i].replace("\t","").trim();

            while (splittedByCr[i].indexOf("  ") >= 0)
            {
                splittedByCr[i] = splittedByCr[i].replace("  ", " ");
            }
        }

        String[][] wordsByString = new String[splittedByCr.length][];
        for (int i = 0; i < splittedByCr.length; i++)
        {
            wordsByString[i] = splittedByCr[i].split(" ");
        }

        HashMap<String,String> replaceMap = new HashMap<String, String>();
        for (Integer i = 0; i < wordsByString.length; i++)
        {
            String word = wordsByString[i][wordsByString[i].length-1];
            replaceMap.put(word, i.toString());
        }

        for (int i = 0; i < wordsByString.length; i++)
        {
            for (int j = 0; j < wordsByString[i].length; j++)
            {
                for (String key : replaceMap.keySet())
                {
                    wordsByString[i][j] = wordsByString[i][j].replace(key, replaceMap.get(key));
                }
            }

        }

        for (int i = 0; i < wordsByString.length; i++)
        {
            for (int j = 0; j < wordsByString[i].length; j++)
            {
                System.out.printf("%s",StringUtils.leftPad(wordsByString[i][j],5));
            }
            System.out.printf("\n");
        }

    }
}
