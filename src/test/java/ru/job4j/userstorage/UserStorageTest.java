package ru.job4j.userstorage;

import org.junit.Assert;
import org.junit.Test;


public class UserStorageTest {

    @Test
    public void whenAddUser() {
        UserStorage userStorage = new UserStorage();
        User firstUser = new User(1, 60);
        Assert.assertTrue(userStorage.add(firstUser));
        Assert.assertEquals(userStorage.get(1), firstUser);
    }

    @Test
    public void whenUpdateUser() {
        UserStorage userStorage = new UserStorage();
        User firstUser = new User(1, 60);
        User secondUser = new User(2, 100);
        userStorage.add(firstUser);
        Assert.assertTrue(userStorage.update(new User(1, 100)));
        Assert.assertFalse(userStorage.update(secondUser));
        Assert.assertEquals(userStorage.get(1).getAmount(), 100);
    }

    @Test
    public void whenDeleteUser() {
        UserStorage userStorage = new UserStorage();
        User firstUser = new User(1, 60);
        User secondUser = new User(2, 100);
        userStorage.add(firstUser);
        Assert.assertEquals(userStorage.get(1), firstUser);
        Assert.assertTrue(userStorage.delete(firstUser));
        Assert.assertFalse(userStorage.delete(secondUser));
        Assert.assertNull(userStorage.get(1));
    }

    @Test
    public void whenTransfer() {
        UserStorage userStorage = new UserStorage();
        User firstUser = new User(1, 60);
        User secondUser = new User(2, 100);
        userStorage.add(firstUser);
        userStorage.add(secondUser);
        Assert.assertFalse(userStorage.transfer(2, 1, 150));
        Assert.assertTrue(userStorage.transfer(1, 2, 50));
        Assert.assertEquals(userStorage.get(1).getAmount(), 10);
        Assert.assertEquals(userStorage.get(2).getAmount(), 150);
    }
}