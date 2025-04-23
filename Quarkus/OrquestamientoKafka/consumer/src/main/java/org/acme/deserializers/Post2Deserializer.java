package org.acme.deserializers;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.records.Post2;

public class Post2Deserializer extends ObjectMapperDeserializer<Post2> {
    public Post2Deserializer() {
        super(Post2.class);
    }
}