package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (key, value) -> {
            int cachedBaseVersion = value.getVersion();
            if (cachedBaseVersion != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base outputBase = new Base(value.getId(), cachedBaseVersion + 1);
            outputBase.setName(model.getName());
            return outputBase;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base get(int id) {
        return memory.get(id);
    }
}