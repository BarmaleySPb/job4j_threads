package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;


public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    private String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream inputStream =
                     new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = inputStream.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public String getContent() {
        return content(data -> true);
    }

    public String getContentWithoutUnicode() {
        return content(data -> data < 0x80);
    }
}
