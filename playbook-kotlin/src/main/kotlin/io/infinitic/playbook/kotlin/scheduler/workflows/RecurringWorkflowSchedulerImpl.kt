package io.infinitic.playbook.kotlin.scheduler.workflows

import com.cronutils.model.CronType
import com.cronutils.model.definition.CronDefinitionBuilder
import com.cronutils.model.time.ExecutionTime
import com.cronutils.parser.CronParser
import io.infinitic.annotations.Ignore
import io.infinitic.workflows.Workflow
import java.time.Clock
import java.time.ZonedDateTime

class RecurringWorkflowSchedulerImpl : Workflow(), RecurringWorkflowScheduler {
    @Ignore
    private val parser = CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX))

    override fun schedule(cronExpr: String, input: RecurringWorkflowInput) {
        // parse cron expression
        val myCron = parser.parse(cronExpr)

        // get current time (inlined because the output is not predictable)
        val now = inline { ZonedDateTime.now(Clock.systemUTC()) }

        // get next execution date
        val nextExecution = ExecutionTime.forCron(myCron).nextExecution(now)

        if (nextExecution.isPresent) {
            // wait up to the next occurrence
            timer(nextExecution.get().toInstant()).await()
            // dispatch recurringWorkflow
            dispatchRecurring(input)
            // restart to wait for the next occurrence
            selfDispatch(cronExpr, input)
        }
    }

    private fun dispatchRecurring(input: RecurringWorkflowInput) {
        val recurringWorkflow = newWorkflow(RecurringWorkflow::class.java)
        dispatch(recurringWorkflow::run, input)
    }

    private fun selfDispatch(cronExpr: String, input: RecurringWorkflowInput) {
        // workflowId is part of the workflow's context
        val self = getWorkflowById(RecurringWorkflowScheduler::class.java, workflowId)
        dispatch(self::schedule, cronExpr, input)
    }
}
