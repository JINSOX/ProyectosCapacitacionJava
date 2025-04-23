package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.records.Post1;
import org.acme.records.Post2;
import org.acme.records.Post3;
import org.acme.records.PostCombinado;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class KafkaConsumer {
    private final AtomicReference<Post1> post1Ref = new AtomicReference<>();
    private final AtomicReference<Post2> post2Ref = new AtomicReference<>();
    private final AtomicReference<Post3> post3Ref = new AtomicReference<>();

    @Incoming("post1-in")
    public void receivePost1(Post1 post1) {
        post1Ref.set(post1);
        checkAndCombine();
    }

    @Incoming("post2-in")
    public void receivePost2(Post2 post2) {
        post2Ref.set(post2);
        checkAndCombine();
    }

    @Incoming("post3-in")
    public void receivePost3(Post3 post3) {
        post3Ref.set(post3);
        checkAndCombine();
    }

    private void checkAndCombine() {
        if (post1Ref.get() != null && post2Ref.get() != null && post3Ref.get() != null) {
            PostCombinado combinado = new PostCombinado(
                    post1Ref.get().title(),
                    post2Ref.get().body(),
                    post3Ref.get().author()
            );

            System.out.println("✅ PostCombinado: " + combinado);

            // limpiar referencias para nueva combinación
            post1Ref.set(null);
            post2Ref.set(null);
            post3Ref.set(null);
        }
    }
}