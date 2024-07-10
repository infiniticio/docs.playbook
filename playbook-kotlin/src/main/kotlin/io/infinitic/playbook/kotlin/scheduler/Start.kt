package io.infinitic.playbook.kotlin.scheduler

import io.infinitic.clients.InfiniticClient
import io.infinitic.playbook.kotlin.scheduler.workflows.RecurringWorkflowInput
import io.infinitic.playbook.kotlin.scheduler.workflows.RecurringWorkflowScheduler
import java.time.format.DateTimeFormatter

internal const val schedulerTag = "scheduler"

fun main() {
    InfiniticClient.fromConfigResource("/infinitic.yml").use { client ->
        // create a stub for PromisesWorkflow, with tag "scheduler"
        val scheduler = client.newWorkflow(RecurringWorkflowScheduler::class.java, setOf(schedulerTag))

        // start new workflow
        client.dispatch(scheduler::schedule, "* * * * *", RecurringWorkflowInput())
    }
}
