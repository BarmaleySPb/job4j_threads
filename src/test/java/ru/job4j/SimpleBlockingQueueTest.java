package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;


public class SimpleBlockingQueueTest {

    @Test
    public void whenFourthPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>();
        Thread first = new Thread(
                () -> {
                    simpleBlockingQueue.offer(1);
                    simpleBlockingQueue.offer(2);
                    simpleBlockingQueue.offer(3);
                    simpleBlockingQueue.offer(4);
                    simpleBlockingQueue.offer(5);
                });
        Thread second = new Thread(
                () -> {
                    simpleBlockingQueue.poll();
                    simpleBlockingQueue.poll();
                    simpleBlockingQueue.poll();
                });
        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(simpleBlockingQueue.poll(), (Integer) 4);
    }
}