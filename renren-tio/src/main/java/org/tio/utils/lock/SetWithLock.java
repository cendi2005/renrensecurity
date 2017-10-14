package org.tio.utils.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SetWithLock<T> extends ObjWithLock<Set<T>>{
    private static final Logger log = LoggerFactory.getLogger(SetWithLock.class);

    /**
     * @param set
     * @author tanyaowu
     */
    public SetWithLock(Set<T> set) {
        super(set);
    }

    /**
     * @param set
     * @param lock
     * @author tanyaowu
     */
    public SetWithLock(Set<T> set, ReentrantReadWriteLock lock) {
        super(set, lock);
    }

    public boolean add(T t){
        ReentrantReadWriteLock.WriteLock writeLock = this.getLock().writeLock();
        writeLock.lock();
        try {
            Set<T> set = this.getObj();
            return set.add(t);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
        return false;
    }


    public void clear(){
        ReentrantReadWriteLock.WriteLock writeLock = this.getLock().writeLock();
        writeLock.lock();
        try {
            Set<T> set = this.getObj();
            set.clear();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
    }

    public boolean remove(T t) {
        ReentrantReadWriteLock.WriteLock writeLock = this.getLock().writeLock();
        writeLock.lock();
        try {
            Set<T> set = this.getObj();
            return set.remove(t);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
        return false;
    }


}
