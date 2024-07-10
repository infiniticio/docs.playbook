package io.infinitic.playbook.java.scheduler.workflows;

import io.infinitic.annotations.Ignore;
import io.infinitic.workflows.Workflow;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
public class RecurringWorkflowImpl extends Workflow implements RecurringWorkflow {

    @Ignore
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void run(RecurringWorkflowInput input) {
        // printing is a side effect, so we "inline" it
        inlineVoid(() -> System.out.println(dtf.format(LocalTime.now()) + " - Instance: " + getWorkflowId()));
    }
}
