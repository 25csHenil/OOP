package com.minibank.repository;
import com.minibank.model.annotation.Id;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Repository<T> {
    private final Map<String, T> storage = new ConcurrentHashMap<>();

    public T save(T entity) {
        try {
            String id = extractId(entity);
            storage.put(id, entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Error persisting entity: " + e.getMessage(), e);
        }
    }

    public T findById(String id) { return storage.get(id); }
    public List<T> findAll() { return new ArrayList<>(storage.values()); }
    public boolean delete(String id) { return storage.remove(id) != null; }
    public int count() { return storage.size(); }

    @SafeVarargs
    public final void saveAll(T... items) {
        for (T item : items) save(item);
    }

    private String extractId(T entity) throws IllegalAccessException {
        Class<?> clazz = entity.getClass();
        while (clazz != null && clazz != Object.class) {
            for (Field f : clazz.getDeclaredFields()) {
                if (f.isAnnotationPresent(Id.class)) {
                    f.setAccessible(true);
                    Object val = f.get(entity);
                    if (val != null) return val.toString();
                }
            }
            clazz = clazz.getSuperclass();
        }
        throw new IllegalArgumentException("No field annotated with @Id in " + entity.getClass().getSimpleName());
    }
}
