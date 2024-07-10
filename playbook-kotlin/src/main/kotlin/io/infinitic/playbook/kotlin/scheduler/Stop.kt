package io.infinitic.playbook.kotlin.scheduler

import io.infinitic.clients.InfiniticClient
import io.infinitic.playbook.kotlin.scheduler.workflows.RecurringWorkflowScheduler

fun main() {
    InfiniticClient.fromConfigResource("/configs/infinitic.yml").use { client ->
        // cancelling the existing RecurringWorkflowScheduler instances with the scheduler tag
        val scheduler = client.getWorkflowByTag(RecurringWorkflowScheduler::class.java, schedulerTag)
        client.cancel(scheduler)
    }
}
