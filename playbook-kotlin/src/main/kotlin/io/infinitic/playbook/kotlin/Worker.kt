package io.infinitic.playbook.kotlin

import io.infinitic.workers.InfiniticWorker
import java.time.format.DateTimeFormatter

internal val formatter = DateTimeFormatter.ofPattern("hh:mm:ss:SSS")

fun main() {
    InfiniticWorker.fromConfigResource("/infinitic.yml").use { worker ->
        worker.start()
    }
}
