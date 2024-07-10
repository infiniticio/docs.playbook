package io.infinitic.playbook.java.asyncMethod;

import io.infinitic.clients.InfiniticClient;
import io.infinitic.playbook.java.asyncMethod.workflows.AsyncMethodWorkflow;

public class Stop {
    public static void main(String[] args) {
        try(InfiniticClient client = InfiniticClient.fromConfigResource("/infinitic.yml")) {
            // cancelling the existing PromisesWorkflow instances with the "promises" tag
            AsyncMethodWorkflow instance = client.getWorkflowByTag(AsyncMethodWorkflow.class, Start.asyncMethodTag);
            client.cancel(instance);
        }
    }
}

