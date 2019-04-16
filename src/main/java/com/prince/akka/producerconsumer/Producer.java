package com.prince.akka.producerconsumer;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

public class Producer extends AbstractLoggingActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Message.class, this::onProduce).build();
    }

    private void onProduce(Message message) {

    }

    static Props props() {
        return Props.create(Producer.class);
    }
}
