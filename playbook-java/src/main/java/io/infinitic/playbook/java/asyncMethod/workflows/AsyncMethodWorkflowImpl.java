package io.infinitic.playbook.java.asyncMethod.workflows;

import io.infinitic.playbook.java.Worker;
import io.infinitic.playbook.java.asyncMethod.services.RemoteService;
import io.infinitic.workflows.Deferred;
import io.infinitic.workflows.Workflow;

import java.time.LocalDateTime;
import static io.infinitic.workflows.DeferredKt.and;

public class AsyncMethodWorkflowImpl extends Workflow implements AsyncMethodWorkflow {

    private final AsyncMethodWorkflow self = getWorkflowById(AsyncMethodWorkflow.class, getWorkflowId());
    private final RemoteService remoteService = newService(RemoteService.class);

    @Override
    public void run() {
        Deferred<Void> deferred1 = dispatchVoid(self::remoteServiceRun, "1", 10L);
        Deferred<Void> deferred2 = dispatchVoid(self::remoteServiceRun, "2", 10L);
        Deferred<Void> deferred3 = dispatchVoid(self::remoteServiceRun, "3", 1L);

        and(deferred1, deferred2, deferred3).await();
        log("all completed");
    }

    @Override
    public void remoteServiceRun(String id, long input) {
        log(id + " starting");
        remoteService.run(id, input);
        log(id + " completed");
    }

    private void log(String msg) {
        inlineVoid(() -> System.out.println(
                LocalDateTime.now().format(Worker.formatter) +
                        " - Workflow " + getWorkflowId() + " (method " + getMethodId() + ") - " + msg
        ));
    }
}