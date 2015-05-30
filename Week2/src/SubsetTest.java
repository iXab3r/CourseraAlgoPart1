/*
* Prerequisites
* Apache Collections - http://commons.apache.org/proper/commons-collections/download_collections.cgi
* JUnit - https://github.com/junit-team/junit/wiki/Download-and-Install
* */

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.TreeBag;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class SubsetTest {

    @Test
    public void RandomnessSubset10Test() throws Exception
    {

        HashMap<Character, Integer> charsMap = new HashMap<>();
        for (char c = 'A'; c <= 'J'; c++) {
            charsMap.put(c, 100);
        }
        List<String> charsToTest = GenerateString(charsMap);

        PerformRandomnessTest(charsToTest, 4, 250, 0.001);
        PerformRandomnessTest(charsToTest, 1, 1000, 0.001);
    }

    @Test
    public void RandomnessSubset6Test() throws Exception
    {
        HashMap<Character, Integer> charsMap = new HashMap<>();
        for (char c = 'A'; c <= 'F'; c++) {
            charsMap.put(c, 100);
        }
        List<String> charsToTest = GenerateString(charsMap);

        PerformRandomnessTest(charsToTest, 1, 600, 0.001);
        PerformRandomnessTest(charsToTest, 2, 300, 0.001);
    }

    @Test
    public void RandomnessSubset8() throws Exception
    {

        HashMap<Character, Integer> charsMap = new HashMap<>();
        charsMap.putIfAbsent('A', 200);
        charsMap.putIfAbsent('B', 100);
        charsMap.putIfAbsent('C', 400);
        charsMap.putIfAbsent('D', 100);

        List<String> charsToTest =GenerateString(charsMap);

        PerformRandomnessTest(charsToTest, 1, 800, 0.001);
        PerformRandomnessTest(charsToTest, 5, 160, 0.001);
    }

    private List<String> GenerateString(HashMap<Character, Integer> _charsMap)
    {
        List<String> charsToTest = new ArrayList<>();
        for (Character c : _charsMap.keySet())
        {
            int charsCount = _charsMap.get(c);

            for (int i = 0; i < charsCount; i++) {
                charsToTest.add(String.valueOf(c));
            }
        }
        return charsToTest;
    }


    public void PerformRandomnessTest(List<String> _inputStrings, Integer _k, Integer _testsCount, double _minPValue) throws Exception {
        System.out.printf("K: %d\r\n", _k);
        System.out.printf("TestsCount: %d\r\n", _testsCount);

        Bag<String> observedElements = new TreeBag<>();

        // fill bags with characters
        Bag<String> expectedElements = new TreeBag<>();
        for (String element : _inputStrings) {
            expectedElements.add(element);
        }



        // prepare input data bytes
        String inputString = String.join(" ", _inputStrings);
        byte[] inputData = inputString.getBytes();
        System.out.printf("Input data length: %d\r\n", inputData.length);


        // remember std out
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(outputStream));

            for (int i = 0; i < _testsCount; i++) {
                System.setIn(new ByteArrayInputStream(inputData));

                // resync StdIn's Scanner through reflection
                ResyncStdIn();
                ResyncStdOut();

                //Act
                Subset.main(new String[]{_k.toString()});

                //terminate last string with \r\n
                StdOut.println();
            }

        } finally {
            // restore std out
            System.setOut(oldOut);
        }

        String output = outputStream.toString();
        for (String element : output.split("\r\n")) {
            observedElements.add(element);
        }

        double criticalValue = 0;
        Set<String> uniqueElements = expectedElements.uniqueSet();

        int degreesOfFreedom = uniqueElements.size() - 1;
        System.out.printf("DegreesOfFreedom: %d\r\n", degreesOfFreedom);


        for (String element : uniqueElements) {
            int observed = observedElements.getCount(element);
            int expected = expectedElements.getCount(element);

            double deviation2DivExpected = Math.pow(observed - expected, 2) / expected;
            criticalValue += deviation2DivExpected;
            System.out.printf("%s: O\\E = %d\\%d (deviation^2 \\ expected = %f) \r\n", element, observed, expected, deviation2DivExpected);
        }

        System.out.printf("CriticalValue: %f\r\n", criticalValue);

        double pValue = CalcChiSqrPValue(degreesOfFreedom, criticalValue);
        System.out.printf("pValue: %f\r\n", pValue);
        System.out.printf("Randomness test completed\r\n\r\n");

        assertTrue(pValue > _minPValue);
    }

    private void ResyncStdIn() throws Exception{
        Method resyncMethod = StdIn.class.getDeclaredMethod("resync");
        resyncMethod.setAccessible(true);
        resyncMethod.invoke(null);
    }

    private void ResyncStdOut ()throws Exception
    {
        Field outField = StdOut.class.getDeclaredField("out");
        outField.setAccessible(true);
        outField.set(null, new PrintWriter(new OutputStreamWriter(System.out), true));
    }


    double CalcChiSqrPValue(int Dof, double Cv) {
        if (Cv < 0 || Dof < 1) {
            return 0.0;
        }
        double K = ((double) Dof) * 0.5;
        double X = Cv * 0.5;
        if (Dof == 2) {
            return Math.exp(-1.0 * X);
        }

        double PValue = IncompleteGammaFunction(K, X);
        if (PValue == Double.NaN || Double.isInfinite(PValue) || PValue <= 1e-8) {
            return 1e-14;
        }

        PValue /= ApproxGamma(K);

        return (1.0 - PValue);
    }

    double ApproxGamma(double Z) {
        double RECIP_E = 0.36787944117144232159552377016147;  // RECIP_E = (E^-1) = (1.0 / E)
        double TWOPI = 6.283185307179586476925286766559;  // TWOPI = 2.0 * PI

        double D = 1.0 / (10.0 * Z);
        D = 1.0 / ((12 * Z) - D);
        D = (D + Z) * RECIP_E;
        D = Math.pow(D, Z);
        D *= Math.sqrt(TWOPI / Z);

        return D;
    }

    static double IncompleteGammaFunction(double S, double Z) {
        if (Z < 0.0) {
            return 0.0;
        }
        double Sc = (1.0 / S);
        Sc *= Math.pow(Z, S);
        Sc *= Math.exp(-Z);

        double Sum = 1.0;
        double Nom = 1.0;
        double Denom = 1.0;

        for (int I = 0; I < 200; I++) {
            Nom *= Z;
            S++;
            Denom *= S;
            Sum += (Nom / Denom);
        }

        return Sum * Sc;
    }
}