package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class KafkaConsumer {
    @Incoming("post-in")
    public Post receive(Post post) {
        System.out.println("Se recibio el post: " + post.toString());

        return post;
    }
}