package io.infinitic.playbook.java.asyncMethod.workflows;

import io.infinitic.annotations.Name;

@Name(name = "AsyncMethodWorkflow")
public interface AsyncMethodWorkflow {
    void run();

    void remoteServiceRun(String id, long input);
}
