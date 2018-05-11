package com.prince.design;

/**
 * @author Prince Raj
 */
public class GeneralConcepts {

    /**
     * <pre>
     *   Technologies:
     *   1. Zookeeper handles much of the system co-ordination (perhaps with a bit of help from higher-level abstractions
     *      like Helix or Curator).
     *   2. Apache Mesos and YARN do process virtualization and resource management
     *   3. Embedded libraries like Lucene and LevelDB do indexing
     *   4. Netty, Jetty and higher-level wrappers like Finagle and rest.li handle remote communication
     *   5. Avro, Protocol Buffers, Thrift, and umpteen zillion other libraries handle serialization
     *   6. Kafka and Bookeeper provide a backing log.
     *   7. Kafka is the "log as a service" project
     *   8. Apache Cassandra is distributed NoSQL database
     *   9. AWS EC2 (provides the instances that make up the Seagull and Jenkins cluster)
     *   10. AWS DynamoDB (stores scheduler metadata)
     *   11. Docker (provides isolation for services required by the tests)
     *   12. Elasticsearch (tracks test run times and cluster usage data)
     *   13. Jenkins (builds code artifacts and runs the Seagull schedulers)
     *   14. Kibana and SignalFx (provide monitoring and alerting)
     *   15. AWS S3 (serves as the source-of-truth for test logs)
     *
     *   Distributed key-value store:
     *   1. Aerospike
     *   2. Bigtable, the data store of Google
     *   3. Couchbase
     *   4. MongoDB
     *   5. Riak
     *   6. Redis
     *
     *   Pub/sub system or Messaging system
     *   1. ActiveMQ
     *   2. RabbitMQ
     *   3. Redis
     *
     *   Miscellaneous Latency Reduction Ideas:
     *   1. Cache
     *   2. CDN
     *   3. Caching proxy server
     *   4. Enhance Your Web Operations Capability
     *   5. Use an Edge DNS Accelerator
     *   6. Optimize Virtual Machines
     *   7. Use Ajax to minimize perceived latency to the user
     *   8. Use a faster network
     *   9. Scale up
     *   10. Optimize firewalls
     *   11. Use Small Memory Chunks When Using Java - GC in Java kills latency. One way to minimize the impact of garbage
     *        collection on latency is to use more VMs and less memory in each VM instead of VM with a lot of memory. This
     *        prevents a large GC run and makes latency more predictable.
     *   12. Use a TCP Offload Engine (TOE)
     *   13. Design low latency network topologies
     *   14. Make TCP Faster
     *   15. Copy Data Zero Times
     *   16. Increase the speed of light
     *
     *   Application Server Architecture Matters Again:
     *   1. Stop Serializing/Deserializing Messages
     *   2. Load Balance Across Read Replicas
     *   3. Minimize Paging
     *   4. Minimize/Remove locking
     *
     *   Minimize The Number Of Hops
     *   1. Colocation
     *   2. Simplify Software Architecture
     *   3. Open a New Datacenter
     *
     *   Design for self healing:
     *   1. Retry failed operations
     *   2. Protect failing remote services (Circuit Breaker)
     *   3. Isolate critical resources (Bulkhead)
     *   4. Perform load leveling
     *   5. Fail over
     *   6. Compensate failed transactions
     *   7. Checkpoint long-running transactions
     *   8. Degrade gracefully
     *   9. Throttle clients
     *   10. Block bad actors
     *   11. Use leader election
     *   12. Test with fault injection
     *   13. Embrace chaos engineering
     *
     *   Autoscaling key metrics:
     *   1. Response time
     *   2. CPU
     *   3. Memory
     *   4. Queue length
     *   5. Increase in tps
     *
     *   Horizontal Scaling Best Practices
     *   1. Split Your Monolithic Application
     *   2. Use Distributed Caching
     *   3. Use CDN
     *   4. Go Async
     *   5. Parallelize The Task
     *   6. Don’t Store State in the Application Tier instead store in DB
     *   7. Use Non-Blocking IO - The java.nio package allows developers to achieve greater performance in data
     *      processing and offers better scalability.
     *
     *   scalability != performance
     *   Performance refers to the capability of a system to provide a certain response time. Scalability can be defined
     *   as ease with which a system or component can be modified to fit the problem area in such a way that the system
     *   can accommodate increased usage, increased datasets, and remains maintainable.
     *
     *   Workflow engine is just a finite state machine with a historical record of every state change saved into a
     *   database.
     *
     *   Memcached supports only string types.
     *   Redis supports five different kinds of data types i.e. string, list, set, ordered set, hash map.
     *   Memcached no persistence, only memory. Redis supports two persistence strategies: snapshots and AOF(Append Only File) logs
     *   Memcached does not support distributed. Redis supports distributed storage using consistent hashing.
     *
     *   Redis application scenarios:
     *   1. Caching
     *   2. The List type is a doubly linked list and is well suited for message queues.
     *   3. Redis memory database can support frequent read and write operations.
     *   4. Redis distributed lock
     *
     *   With all the other message queues out there, Kafka seemed like a natural fit for these requirements. Its
     *   architecture is very unusual for a message queue – it is actually a replicated commit log, but this makes it
     *   a good fit for our use-case. Its replicated, partitioned design means it can tolerate node failure and scale
     *   up and down without service interruption. Its consumer design is also interesting: where most systems maintain
     *   message queues for each consumer, Kafka consumers are simply “cursors” into the message log. This makes pub/sub
     *   systems much less expensive, and because messages are kept around for a while no matter what, we can play
     *   earlier events back to certain services easily.
     *
     *   The serving system may also use optimized hardware. For example, most our live data systems either serve out
     *   of memory or else use SSDs.
     *
     *   YARN as execution layer, HDFS for storage, and MapReduce as processing API
     *
     *   Blocking I/O:
     *   It means that the calling system does not return control to the caller until the operation is finished. As a
     *   result, the caller is blocked and cannot perform other activities during that time.
     *
     *   Non-blocking Synchronous I/O:
     *   It means that call returns control to the caller immediately and the caller is not made to wait. The invoked
     *   system immediately returns one of two responses: If the call was executed and the results are ready, then the
     *   caller is told of that. Alternatively, the invoked system can tell the caller that the system has no resources
     *   (no data in the socket) to perform the requested action.
     *
     *   Non-blocking Asynchronous I/O:
     *   It means that the calling function returns control to the caller immediately, reporting that the requested
     *   action was started. The invoked system will notify the caller (by callback for example), when the result is
     *   ready for processing.
     *
     *   Column-oriented databases can model a problem such as time series efficiently.
     *
     *   DNS lookups in Java are another example of potential time mismanagement. In a typical long-running system, the
     *   initial DNS lookups are executed, and unless configured explicitly not to, are cached for the lifetime of the
     *   JVM.
     *
     *   By making performance work part of your daily routine and automating as much as possible, you’ll be able to
     *   minimize the operational costs of this work over time. By creating a performance budget early on, you can make
     *   performance sacrifices in one area of a page and make up for them in another.
     *
     *   The faster your website is, the more people will use it.
     *
     *   It does not matter how fast you can build the first version of your product. It only matters how fast you can
     *   change it later.
     *
     *   Compress the data you send over the network.
     *
     *   Compress the data you store on disk. Try it. Really. Yes, storage is cheap, but I/O is not. By compressing your
     *   stored data, you can easily and effectively increase your I/O throughput.
     *   Keep per connection overhead low.
     *
     *   Set explicit SLA's on all requests and specify upfront how the violations will be handled.
     *
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
