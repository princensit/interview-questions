package com.prince.akka.producerconsumer;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

public class Consumer extends AbstractLoggingActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Message.class, this::onConsume).build();
    }

    private void onConsume(Message msg) {
        log().info("Received value: " + msg.getValue());
    }

    static Props props() {
        return Props.create(Consumer.class);
    }
}
