package io.infinitic.playbook.java;

import io.infinitic.workers.InfiniticWorker;

import java.time.format.DateTimeFormatter;

public class Worker {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss:SSS");

    public static void main(String[] args) {
        try (InfiniticWorker worker = InfiniticWorker.fromConfigResource("/infinitic.yml")) {
            worker.start();
        }
    }
}
