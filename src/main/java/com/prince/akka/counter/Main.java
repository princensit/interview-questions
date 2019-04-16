package com.prince.akka.counter;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import scala.io.StdIn;

public class Main {

    static class Counter extends AbstractLoggingActor {

        // protocol
        static class Message {
        }

        private int counter = 0;

        @Override
        public Receive createReceive() {
            return receiveBuilder().match(Message.class, this::onMessage).build();
        }

        private void onMessage(Message msg) {
            counter++;
            log().info("Increased counter: " + counter);
        }

        static Props props() {
            return Props.create(Counter.class);
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
        final ActorRef counter = system.actorOf(Counter.props(), "counter");

        for (int i = 0; i < 5; i++)
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    counter.tell(new Counter.Message(), ActorRef.noSender());
                }
            }).start();

        system.terminate();
    }
}
