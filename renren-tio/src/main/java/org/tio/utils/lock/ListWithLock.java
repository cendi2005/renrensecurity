package org.tio.utils.lock;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
* list 带锁
* **/
public class ListWithLock<T> extends ObjWithLock<List<T>> {

    public ListWithLock(List<T> list){
        super(list);
    }
    public ListWithLock(List<T> list, ReentrantReadWriteLock lock){
        super(list,lock);
    }
}
