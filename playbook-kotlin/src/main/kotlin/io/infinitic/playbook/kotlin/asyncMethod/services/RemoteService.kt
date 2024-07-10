package io.infinitic.playbook.kotlin.asyncMethod.services

import io.infinitic.annotations.Name

@Name("AsyncMethodRemoteService")
interface RemoteService {
    fun run(id: String, input: Long)
}