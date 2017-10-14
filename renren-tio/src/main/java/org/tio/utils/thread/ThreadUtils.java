package org.tio.utils.thread;

public class ThreadUtils {
    public static String stackTrace() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StringBuilder buf = new StringBuilder();
        for (StackTraceElement element : elements) {
            buf.append("\r\n	").append(element.getClassName()).append(".").append(element.getMethodName()).append("(").append(element.getFileName()).append(":")
                    .append(element.getLineNumber()).append(")");
        }
        return buf.toString();
    }
}
