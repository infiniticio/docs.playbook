package io.infinitic.playbook.kotlin.asyncMethod

import io.infinitic.clients.InfiniticClient
import io.infinitic.playbook.kotlin.asyncMethod.workflows.AsyncMethodWorkflow

internal const val asyncMethodTag = "asyncMethod"

fun main() {
    InfiniticClient.fromConfigResource("/infinitic.yml").use { client ->
        // create a stub for PromisesWorkflow, with tag "promises"
        val workflow = client.newWorkflow(AsyncMethodWorkflow::class.java, setOf(asyncMethodTag))

        // start new workflow
        client.dispatch(workflow::run)
    }
}
