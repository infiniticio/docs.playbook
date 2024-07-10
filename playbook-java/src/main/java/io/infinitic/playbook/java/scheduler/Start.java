package io.infinitic.playbook.java.scheduler;

import io.infinitic.clients.InfiniticClient;
import io.infinitic.playbook.java.scheduler.workflows.RecurringWorkflowInput;
import io.infinitic.playbook.java.scheduler.workflows.RecurringWorkflowScheduler;

import java.util.Set;

public class Start {
    static final String schedulerTag = "scheduler";

    public static void main(String[] args) {
        try(InfiniticClient client = InfiniticClient.fromConfigResource("/infinitic.yml")) {
            // create a stub for RecurringWorkflowScheduler, with tag "scheduler"
            RecurringWorkflowScheduler workflow = client.newWorkflow(RecurringWorkflowScheduler.class, Set.of(schedulerTag));

            // start new workflow
            client.dispatchVoid(workflow::schedule, "* * * * *", new RecurringWorkflowInput());
        }
    }
}

