package org.tio.utils.lock;

import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MapWithLock<K,V> extends ObjWithLock<Map<K,V>> {
    public MapWithLock(Map<K,V> map){
        super(map);
    }
    public MapWithLock(Map<K,V> map, ReentrantReadWriteLock lock){
        super(map,lock);
    }
}
