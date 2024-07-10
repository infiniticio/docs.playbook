package io.infinitic.playbook.kotlin.asyncMethod

import io.infinitic.clients.InfiniticClient
import io.infinitic.playbook.kotlin.asyncMethod.workflows.AsyncMethodWorkflow

fun main() {
    InfiniticClient.fromConfigResource("/infinitic.yml").use { client ->
        // cancelling the existing PromisesWorkflow instances with the "asyncMethod" tag
        val instance = client.getWorkflowByTag(AsyncMethodWorkflow::class.java, asyncMethodTag)
        client.cancel(instance)
    }
}
