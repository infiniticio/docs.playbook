package io.infinitic.playbook.java.asyncMethod.services;

import io.infinitic.playbook.java.Worker;
import io.infinitic.tasks.Task;
import java.time.LocalDateTime;

public class RemoteServiceImpl implements RemoteService {
    @Override
    public void run(String id, long input)  {
        log("start processing " + id);
        try {
            Thread.sleep(input*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("stop processing " + id);

    }

    private void log(String msg) {
        System.out.println(
                LocalDateTime.now().format(Worker.formatter) + " - Service  " + Task.getTaskId() + " - " + msg
        );
    }
}
