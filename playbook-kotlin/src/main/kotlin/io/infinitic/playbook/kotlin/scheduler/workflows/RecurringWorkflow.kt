package io.infinitic.playbook.kotlin.scheduler.workflows

import io.infinitic.annotations.Name

@Name(name = "RecurringWorkflow")
interface RecurringWorkflow {
    fun run(input: RecurringWorkflowInput)
}