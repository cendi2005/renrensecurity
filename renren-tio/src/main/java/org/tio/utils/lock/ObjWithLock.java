package org.tio.utils.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自带读写锁对象
 * */
public class ObjWithLock<T> {
    private T obj = null;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    public void setLock(ReentrantReadWriteLock lock) {
        this.lock = lock;
    }

    private ReentrantReadWriteLock lock = null;

    public ObjWithLock(T obj,ReentrantReadWriteLock lock){
        super();
        this.obj = obj;
        this.lock = lock;
    }


    public ObjWithLock(T obj){
        this(obj,new ReentrantReadWriteLock());
    }


}
