package io.infinitic.playbook.kotlin.scheduler.workflows

import io.infinitic.annotations.Name

@Name("RecurringWorkflowScheduler")
interface RecurringWorkflowScheduler {
    fun schedule(cronExpr: String, input: RecurringWorkflowInput)
}