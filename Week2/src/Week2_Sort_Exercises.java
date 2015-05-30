public class Week2_Sort_Exercises {
    public static void main(String[] args) {

        Q1();
        Q2();

    }

    private static void Q2L() {
        String[] _input = new String[]{ "M", "O", "L", "E", "E", "X", "A", "S", "P", "R", "T"};
        ShellSort.sort(_input, true);
    }


    private static void Q2() {
        Integer[] _input = new Integer[]{46, 56, 96, 54, 25, 74, 62, 24, 83, 13  };
        ShellSort.sort(_input, true);
    }

    private static void Q1() {
        Integer[] _input = new Integer[]{37, 47, 70, 76, 34, 68, 27, 22, 38, 67  };
        SelectionSort.sort(_input, true);
    }
}
