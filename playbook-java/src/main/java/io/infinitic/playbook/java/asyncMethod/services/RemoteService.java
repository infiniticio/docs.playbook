package io.infinitic.playbook.java.asyncMethod.services;

import io.infinitic.annotations.Name;

@Name(name = "AsyncMethodRemoteService")
public interface RemoteService {
    void run(String id, long input);
}
