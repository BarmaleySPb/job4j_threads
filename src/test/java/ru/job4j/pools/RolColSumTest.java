package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;


public class RolColSumTest {

    @Test
    public void whenSum() {
        int[][] matrix = {
                {1, 2, 3},
                {3, 2, 3},
                {5, 2, 3}
        };
        RolColSum.Sums[] result = RolColSum.sum(matrix);
        assertEquals(result[0].getRowSum(), 6);
        assertEquals(result[1].getRowSum(), 8);
        assertEquals(result[2].getRowSum(), 10);
        assertEquals(result[0].getColSum(), 9);
        assertEquals(result[1].getColSum(), 6);
        assertEquals(result[2].getColSum(), 9);
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3, 6},
                {3, 2, 3, 8},
                {5, 2, 3, 2},
                {3, 2, 1, 1}
        };
        RolColSum.Sums[] result = RolColSum.asyncSum(matrix);
        assertEquals(result[0].getRowSum(), 12);
        assertEquals(result[1].getRowSum(), 16);
        assertEquals(result[2].getRowSum(), 12);
        assertEquals(result[3].getRowSum(), 7);
        assertEquals(result[0].getColSum(), 12);
        assertEquals(result[1].getColSum(), 8);
        assertEquals(result[2].getColSum(), 10);
        assertEquals(result[3].getColSum(), 17);
    }
}