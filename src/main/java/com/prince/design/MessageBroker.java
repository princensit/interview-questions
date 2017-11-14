package com.prince.design;

/**
 * @author Prince Raj
 */
public class MessageBroker {

    public static void main(String[] args) {
        // NOTE: https://logz.io/blog/kafka-vs-redis/

        printRabbitMQ();

        printRedis();

        printKafka();
    }

    private static void printRabbitMQ() {
        System.out.println("=============> RabbitMQ");
        System.out.println("RabbitMQ is written in Erlang.");
    }

    private static void printRedis() {
        System.out.println("=============> Redis");
        System.out.println("It is networked, in-memory, and stores keys with optional durability.");
    }

    private static void printKafka() {
        System.out.println("=============> Kafka");
        System.out
                .println("Kafka offers a high guarantee that the service will be available and non-blocking under any circumstances. In addition, messages can easily be replicated for higher data availability.");
        System.out
                .println("What is so special about Kafka is the architecture, it stores the messages in flat files and consumers ask messages based on an offset.");
        System.out
                .println("Old messages can be retained on a time base (like expire_logs_days) and/or on a storage usage base.");
        System.out
                .println("The Kafka server uses Zookeeper for cluster membership and routing while the consumers can also use Zookeeper or something else for synchronization.");
        System.out.println("Zookeeper is a highly-available synchronous distributed storage system.");
        System.out
                .println("Both Kafka brokers & consumers use Zookeeper to reliably maintain their state across a cluster.");
        System.out.println("Publishing speed of 165k messages/s over a single thread.");
        System.out
                .println("The basic messaging terms that Kafka uses are:\n"
                        + "Topic: These are the categories in which messages are published.\n"
                        + "Producer: This is the process of publishing messages into Kafka’s topics.\n"
                        + "Consumer: This process subscribes to topics and processes the messages. Consumers are part of a consumer group which is composed of many consumer instances for scalability and fault tolerance.\n"
                        + "Broker: Each server in a Kafka cluster is called a broker.");
    }
}
