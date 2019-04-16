package com.prince.akka.producerconsumer;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Main {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("producer-consumer");
        final ActorRef producer = system.actorOf(Producer.props(), "producer");
        final ActorRef consumer = system.actorOf(Consumer.props(), "consumer");

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    consumer.tell(new Message(finalI * j), ActorRef.noSender());
                }
            }).start();
        }

        system.terminate();
    }
}
