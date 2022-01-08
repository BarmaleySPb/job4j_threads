package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;


@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, User> userStorage = new ConcurrentHashMap<>();

    public synchronized boolean add(User user) {
        return userStorage.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return userStorage.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return userStorage.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User sender = userStorage.get(fromId);
        User recipient = userStorage.get(toId);
        if (sender == null || recipient == null || sender.getAmount() < amount) {
            return false;
        }
        update(new User(sender.getId(), sender.getAmount() - amount));
        update(new User(recipient.getId(), recipient.getAmount() + amount));
        return true;
    }
}
