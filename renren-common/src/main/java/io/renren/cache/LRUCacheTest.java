package io.renren.cache;

public class LRUCacheTest {
    public static void main(String[] args) {
//      FIFOCache<Integer, Integer> map = new FIFOCache<Integer, Integer>(10);//设置容量为10

        LRUCache<Integer, Integer> map = new LRUCache<Integer, Integer>(10);

        for (int i = 0; i++ < 10; ) {
            map.put(i, i);   //放入1-10总10个数据
        }
        System.out.println("起始存储情况："+map.toString());//打印起始存储情况

        map.get(7);
        System.out.println("命中一个已存在的数据："+map.toString());//打印命中之后的情况

        map.put(8, 8+1);  //存入一个已存在的数据，也就是命中一次缓存中的数据
        System.out.println("覆盖一个已存在的数据："+map.toString());//打印命中之后的情况

        map.put(11, 11); //又存入缓存之外的数据
        System.out.println("新增一个数据后："+map.toString());//打印又存储一个数据之后的情况
    }

}

//输出结果
//起始存储情况：{1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9, 10=10}
//命中一个已存在的数据：{1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 8=8, 9=9, 10=10, 7=7}
//覆盖一个已存在的数据：{1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 9=9, 10=10, 7=7, 8=9}
//新增一个数据后：{2=2, 3=3, 4=4, 5=5, 6=6, 9=9, 10=10, 7=7, 8=9, 11=11}
/**
 * 从测试类的结果可以看出，当某一个数据被访问命中就会按照LRU规则放到队列最前面。
 * 如果新增一个不存在缓存的数据，会把该数据放到最前面，同时移除最早访问过的数据。
 * */

