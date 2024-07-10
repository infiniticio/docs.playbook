package io.infinitic.playbook.kotlin.asyncMethod.services

import io.infinitic.playbook.kotlin.formatter
import io.infinitic.tasks.Task
import java.time.LocalDateTime

class RemoteServiceImpl: RemoteService {
    override fun run(id: String, input: Long) {
        log("start processing $id")
        Thread.sleep(input*1000)
        log("stop processing $id")
    }

    private fun log(msg: String) =
        println("${LocalDateTime.now().format(formatter)} - Service  ${Task.taskId} - $msg")
}