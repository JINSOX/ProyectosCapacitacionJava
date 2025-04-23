package org.acme.deserializers;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.records.Post3;

public class Post3Deserializer extends ObjectMapperDeserializer<Post3> {
    public Post3Deserializer() {
        super(Post3.class);
    }
}