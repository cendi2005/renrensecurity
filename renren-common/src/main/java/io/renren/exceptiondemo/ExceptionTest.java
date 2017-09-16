package io.renren.exceptiondemo;

public class ExceptionTest {
    public static void execute(String a) throws MyException{
        System.out.println("execute...");
        if("true".equals(a)){
            throw new MyException("参数不能为空");
        }
    }

    public static void main(String[] args) throws MyException{
        execute("true");
    }
}
