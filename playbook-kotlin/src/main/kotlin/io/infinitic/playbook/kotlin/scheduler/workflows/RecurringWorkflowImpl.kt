package io.infinitic.playbook.kotlin.scheduler.workflows

import io.infinitic.playbook.kotlin.formatter
import io.infinitic.workflows.Workflow
import java.time.LocalTime

class RecurringWorkflowImpl : Workflow(), RecurringWorkflow {
    override fun run(input: RecurringWorkflowInput) {
        inline { println("${formatter.format(LocalTime.now())} - Instance: $workflowId") }
    }
}