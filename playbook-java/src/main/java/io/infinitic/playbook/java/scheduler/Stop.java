package io.infinitic.playbook.java.scheduler;

import io.infinitic.clients.InfiniticClient;
import io.infinitic.playbook.java.scheduler.workflows.RecurringWorkflowScheduler;

import static io.infinitic.playbook.java.scheduler.Start.schedulerTag;

public class Stop {

    public static void main(String[] args) {
        try(InfiniticClient client = InfiniticClient.fromConfigResource("/infinitic.yml")) {
            // cancelling the existing RecurringWorkflowScheduler instances with the "scheduler" tag
            RecurringWorkflowScheduler scheduler = client.getWorkflowByTag(RecurringWorkflowScheduler.class, schedulerTag);
            client.cancel(scheduler);
        }
    }
}

