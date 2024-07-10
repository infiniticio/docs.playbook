package io.infinitic.playbook.java.scheduler.workflows;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import io.infinitic.annotations.Ignore;
import io.infinitic.workflows.Workflow;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Optional;

@SuppressWarnings("unused")
public class RecurringWorkflowSchedulerImpl extends Workflow implements RecurringWorkflowScheduler {

    @Ignore
    private final CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX));

    @Override
    public void schedule(String cronExpr, RecurringWorkflowInput input) {
        // parse cron expression
        Cron myCron = parser.parse(cronExpr);
        // get current time, must be inlined
        ZonedDateTime now = inline(() -> ZonedDateTime.now(Clock.systemUTC()));
        // get next execution date
        Optional<ZonedDateTime> nextExecution = ExecutionTime.forCron(myCron).nextExecution(now);

        if (nextExecution.isPresent()) {
            // wait up to the next occurrence
            timer(nextExecution.get().toInstant()).await();
            // dispatch the recurring workflow
            dispatchRecurring(input);
            // restart to wait for the next occurrence
            selfDispatch(cronExpr, input);
        }
    }

    private void dispatchRecurring(RecurringWorkflowInput input) {
        RecurringWorkflow recurringWorkflow = newWorkflow(RecurringWorkflow.class);
        dispatchVoid(recurringWorkflow::run, input);
    }

    private void selfDispatch(String cronExpr, RecurringWorkflowInput input) {
        // workflowId is part of the workflow's context
        RecurringWorkflowScheduler self = getWorkflowById(RecurringWorkflowScheduler.class, getWorkflowId());
        dispatchVoid(self::schedule, cronExpr, input);
    }
}
