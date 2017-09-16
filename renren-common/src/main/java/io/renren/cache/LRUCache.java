package io.renren.cache;

import java.util.LinkedHashMap;

public class LRUCache<K,V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 5853563362972200456L;

    private final int SIZE;

    public LRUCache(int size) {
        super(size, 0.75f, true);  //int initialCapacity, float loadFactor, boolean accessOrder这3个分别表示容量，加载因子和是否启用LRU规则
        SIZE = size;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > SIZE;
    }
}