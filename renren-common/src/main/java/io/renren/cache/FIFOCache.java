package io.renren.cache;

import java.util.LinkedHashMap;

public class FIFOCache<K,V> extends LinkedHashMap<K, V>{

    private static final long serialVersionUID = 436014030358073695L;

    private final int SIZE;

    public FIFOCache(int size) {
        super();//调用父类无参构造，不启用LRU规则
        SIZE = size;
    }

    //重写淘汰机制
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > SIZE;  //如果缓存存储达到最大值删除最后一个
    }


}