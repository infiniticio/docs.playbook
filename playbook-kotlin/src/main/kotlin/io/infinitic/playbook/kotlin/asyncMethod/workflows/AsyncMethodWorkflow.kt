package io.infinitic.playbook.kotlin.asyncMethod.workflows

import io.infinitic.annotations.Name

@Name("AsyncMethodWorkflow")
interface AsyncMethodWorkflow {
    fun run()

    fun remoteServiceRun(id: String, input: Long)
}