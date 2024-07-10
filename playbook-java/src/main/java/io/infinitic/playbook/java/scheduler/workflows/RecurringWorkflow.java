package io.infinitic.playbook.java.scheduler.workflows;

import io.infinitic.annotations.Name;

@Name(name = "RecurringWorkflow")
public interface RecurringWorkflow {
    void run(RecurringWorkflowInput input);
}
