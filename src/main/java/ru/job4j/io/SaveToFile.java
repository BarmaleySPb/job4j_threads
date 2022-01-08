package ru.job4j.io;

import java.io.*;


public final class SaveToFile {
    private final File file;

    public SaveToFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (OutputStream outputStream =
                     new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                outputStream.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}