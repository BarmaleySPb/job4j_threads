package ru.job4j.cache;

import org.junit.Test;

import static org.junit.Assert.*;


public class CacheTest {

    @Test
    public void whenAdd() {
        Cache cache = new Cache();
        Base first = new Base(1, 1);
        first.setName("First");
        assertTrue(cache.add(first));
        assertEquals(cache.get(1), first);
    }

    @Test
    public void whenDelete() {
        Cache cache = new Cache();
        Base first = new Base(1, 1);
        first.setName("First");
        cache.add(first);
        assertEquals(cache.get(1), first);
        cache.delete(first);
        assertNull(cache.get(1));
    }

    @Test
    public void whenUpdate() {
        Cache cache = new Cache();
        Base first = new Base(1, 1);
        first.setName("First");
        cache.add(first);
        first.setName("Second");
        assertTrue(cache.update(first));
        assertEquals(cache.get(1).getVersion(), 2);
        assertEquals(cache.get(1).getName(), "Second");
    }

    @Test(expected = OptimisticException.class)
    public void whenException() {
        Base first = new Base(1, 1);
        first.setName("First");
        Base second = new Base(1, 2);
        second.setName("Second");
        Cache cache = new Cache();
        cache.add(first);
        cache.update(second);
    }
}