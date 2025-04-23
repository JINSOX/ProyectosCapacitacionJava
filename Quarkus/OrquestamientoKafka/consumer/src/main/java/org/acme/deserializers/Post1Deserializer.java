package org.acme.deserializers;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.records.Post1;

public class Post1Deserializer extends ObjectMapperDeserializer<Post1> {
    public Post1Deserializer() {
        super(Post1.class);
    }
}
