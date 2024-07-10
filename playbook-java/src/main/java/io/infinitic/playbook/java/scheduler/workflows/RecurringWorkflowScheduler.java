package io.infinitic.playbook.java.scheduler.workflows;

import io.infinitic.annotations.Name;

@Name(name = "RecurringWorkflowScheduler")
public interface RecurringWorkflowScheduler {
    void schedule(String cronExpr, RecurringWorkflowInput input);
}
