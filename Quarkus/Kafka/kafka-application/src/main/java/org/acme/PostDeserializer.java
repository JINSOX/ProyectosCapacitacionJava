package org.acme;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class PostDeserializer extends ObjectMapperDeserializer<Post> {
    public PostDeserializer() {
        super(Post.class);
    }
}