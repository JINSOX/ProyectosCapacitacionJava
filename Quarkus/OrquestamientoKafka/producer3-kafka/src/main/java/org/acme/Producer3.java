package org.acme;

import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

public class Producer3 {
    @Inject
    @Channel("post3-out")
    Emitter<Post> emitter;

    //Logging
    public void sendPost(Post post) {
        emitter.send(post);
    }
}