package net.alexander.backdata.test;

import net.alexander.backdata.log.LoggerManager;
import net.alexander.backdata.service.ServiceManager;

public class Test extends Thread {

    LoggerManager loggerManager = ServiceManager.getService(LoggerManager.class);
    private boolean running;

    public Test() {
        running = true;
        setDaemon(true);
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 21) {
            loggerManager.log("Na " + i);
            i++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
