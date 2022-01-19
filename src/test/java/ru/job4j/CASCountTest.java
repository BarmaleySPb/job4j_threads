package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;


public class CASCountTest {

    @Test
    public void whenIncrementFiveTimesInTwoThreads() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(
                () -> {
                    count.increment();
                    count.increment();
                    count.increment();
                }
        );
        Thread second = new Thread(
                () -> {
                    count.increment();
                    count.increment();
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();

        assertEquals(count.get(), 5);
    }

    @Test
    public void whenIncrementTwelveKTimesInThreeThreads() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 5000; i++) {
                        count.increment();
                    }
                });
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 3000; i++) {
                        count.increment();
                    }
                });
        Thread third = new Thread(
                () -> {
                    for (int i = 0; i < 4000; i++) {
                        count.increment();
                    }
                });
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();

        assertEquals(count.get(), 12000);
    }
}