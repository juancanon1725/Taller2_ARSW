package com.example;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testMean() {
        Calculator mean = data -> {
            double sum = 0.0;
            for (Double d : data) {
                sum += d;
            }
            return Double.toString(sum / data.size());
        };

        LinkedList<Double> data = new LinkedList<>();
        data.add(1.0);
        data.add(2.0);
        data.add(3.0);
        assertEquals("2.0", mean.operation(data));
    }

    @Test
    public void testDeviation() {
        Calculator deviation = data -> {
            double sum = 0.0;
            for (Double d : data) {
                sum += d;
            }
            double mean = sum / data.size();
            double sumOfSquares = 0.0;
            for (Double d : data) {
                sumOfSquares += Math.pow(d - mean, 2);
            }
            return Double.toString(Math.sqrt(sumOfSquares / (data.size() - 1)));
        };

        LinkedList<Double> data = new LinkedList<>();
        data.add(1.0);
        data.add(2.0);
        data.add(3.0);
        assertEquals("1.0", deviation.operation(data));
    }
}
