package ru.job4j.concurrent;


public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        int count = 0;
        char[] symbols = {'\\', '|', '/', '-'};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\rLoading... " + symbols[count++]);
                Thread.sleep(300);
                count = count % 4;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }
}


