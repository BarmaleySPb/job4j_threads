package ru.job4j.pools;

import org.junit.Test;
import ru.job4j.userstorage.User;

import static org.junit.Assert.*;


public class ParallelSearchTest {

    @Test
    public void whenString() {
        String[] array = {"asd", "adf", "rtr", "fjj", "dfgdfh", "dfgd"};
        assertEquals(ParallelSearch.search(array, "fjj"), 3);
    }

    @Test
    public void whenInteger() {
        Integer[] array = {1, 2, 3, 4, 5, 7, 9};
        assertEquals(ParallelSearch.search(array, 5), 4);
    }

    @Test
    public void whenUser() {
        User first = new User(1, 100);
        User second = new User(2, 43);
        User third = new User(3, 433);
        User[] users = {first, second, third};
        assertEquals(ParallelSearch.search(users, third), 2);
    }

    @Test
    public void whenNotFound() {
        String[] array = {"asd", "adf", "rtr", "fjj", "dfgdfh", "dfgd"};
        assertEquals(ParallelSearch.search(array, "fj1j"), -1);
    }

    @Test
    public void when() {
        Integer[] array = {1, 2, 3, 4, 5, 7, 9, 14, 22, 32, 47, 69, 87, 65, 46, 13,
                21, 66, 48, 59, 98, 99, 55, 87, 42, 31, 56, 49, 79, 86, 54, 53, 41,
                65, 45, 64, 68, 79, 23, 12};
        assertEquals(ParallelSearch.search(array, 86), 29);
        assertEquals(ParallelSearch.search(array, 65), 33);
        assertEquals(ParallelSearch.search(array, 68), 36);
    }

}