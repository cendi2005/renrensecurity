package io.renren.lifecycle;

import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

@Component

public class LifeCycleDemo implements Lifecycle {
    @Override
    public void start() {
        System.out.println("start");
    }

    @Override
    public void stop() {
        System.out.println("stop");

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
