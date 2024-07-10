package io.infinitic.playbook.kotlin.asyncMethod.workflows

import io.infinitic.playbook.kotlin.formatter
import io.infinitic.playbook.kotlin.asyncMethod.services.RemoteService
import io.infinitic.workflows.and
import io.infinitic.workflows.Workflow
import java.time.LocalDateTime

class AsyncMethodWorkflowImpl : Workflow(), AsyncMethodWorkflow {

    private val self = getWorkflowById(AsyncMethodWorkflow::class.java, workflowId);
    private val remoteService = newService(RemoteService::class.java)

    override fun run() {
        val deferred1 = dispatch(self::remoteServiceRun, "1", 10)
        val deferred2 = dispatch(self::remoteServiceRun, "2", 10)
        val deferred3 = dispatch(self::remoteServiceRun, "3", 1)

        (deferred1 and deferred2 and deferred3).await()
        log("all completed")
    }

    override fun remoteServiceRun(id: String, input: Long) {
        log("$id starting")
        remoteService.run(id, input)
        log("$id completed")
    }

    private fun log(msg: String) = inline {
        println("${LocalDateTime.now().format(formatter)} - Workflow $workflowId (method $methodId) - $msg")
    }
}