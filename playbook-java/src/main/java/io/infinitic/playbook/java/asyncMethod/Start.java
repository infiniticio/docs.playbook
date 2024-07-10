package io.infinitic.playbook.java.asyncMethod;

import io.infinitic.clients.InfiniticClient;
import io.infinitic.playbook.java.asyncMethod.workflows.AsyncMethodWorkflow;

import java.util.Set;

public class Start {
    static final String tag = "promises";

    public static void main(String[] args) {
        try(InfiniticClient client = InfiniticClient.fromConfigResource("/infinitic.yml")) {
            // create a stub for PromisesWorkflow, with tag "promises"
            AsyncMethodWorkflow instance = client.newWorkflow(AsyncMethodWorkflow.class, Set.of(tag));

            // start new workflow
            client.dispatchVoid(instance::run);
        }
    }
}

