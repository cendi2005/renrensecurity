package org.tio.utils.prop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.utils.lock.MapWithLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;


public class MapWithLockPropSupport implements IPropSupport {
    private static Logger log = LoggerFactory.getLogger(MapWithLockPropSupport.class);

    private MapWithLock<String,Object> props = null;

    public MapWithLockPropSupport(){
    }


    private void initProps(){
        if(props==null){
            synchronized (this){
                if(props==null){
                    props = new MapWithLock<>(new HashMap<String, Object>());
                }
            }
        }
    }



    @Override
    public void clearAttribute() {



    }

    @Override
    public Object getAttribute(String key) {
        return null;
    }

    @Override
    public void removeAttribute(String key) {

        initProps();
        Lock lock = props.getLock().writeLock();
        Map<String,Object> m = props.getObj();
        try {
            lock.lock();
            m.remove(key);
        } catch (Exception e) {
            throw e;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void setAttribute(String key, Object value) {
        initProps();
        Lock lock = props.getLock().writeLock();
        Map<String, Object> m = props.getObj();
        try {
            lock.lock();
            m.put(key, value);
        } catch (Exception e) {
            throw e;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        StackTraceElement elements[] = Thread.currentThread().getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            StackTraceElement stackTraceElement=elements[i];
            String className=stackTraceElement.getClassName();
            String methodName=stackTraceElement.getMethodName();
            String fileName=stackTraceElement.getFileName();
            int lineNumber=stackTraceElement.getLineNumber();
            System.out.println("StackTraceElement数组下标 i="+i+",fileName="
                    +fileName+",className="+className+",methodName="+methodName+",lineNumber="+lineNumber);
        }
    }

}
