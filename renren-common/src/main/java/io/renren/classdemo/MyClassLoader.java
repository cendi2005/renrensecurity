package io.renren.classdemo;


/**
 * 自定义一个类加载器，用于将字节码转换为class对象
 * @author louluan
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> defineMyClass( byte[] b, int off, int len)
    {
        return super.defineClass(b, off, len);
    }

}
