package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;


public class SimpleBlockingQueueTest {

    @Test
    public void whenFourthPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(5);
        Thread first = new Thread(
                () -> {
                    try {
                        simpleBlockingQueue.offer(1);
                        simpleBlockingQueue.offer(2);
                        simpleBlockingQueue.offer(3);
                        simpleBlockingQueue.offer(4);
                        simpleBlockingQueue.offer(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        Thread second = new Thread(
                () -> {
                    try {
                        simpleBlockingQueue.poll();
                        simpleBlockingQueue.poll();
                        simpleBlockingQueue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(simpleBlockingQueue.poll(), (Integer) 4);
    }
}