package com.prince.design;

/**
 * https://www.corejavaguru.com/blog/bigdata/why-zookeeper-on-odd-number-nodes.php
 *
 * http://www.dengshenyu.com/java/%E5%88%86%E5%B8%83%E5%BC%8F%E7%B3%BB%E7%BB%9F/2017/10/23/zookeeper-distributed-lock.
 * html
 *
 * @author Prince Raj
 */
public class Zookeeper {

    /**
     * <pre>
     * Three popular schemes for implementing distributed locks:
     * 1. Database
     * 2. Redis
     * 3. Zookeeper
     *
     * ZooKeeper - a centralized service that provides configuration management, distributed collaboration, and naming.
     *
     * Why zookeeper on odd number nodes?
     * - ZooKeeper is a coordination service for distributed systems
     * - ZooKeeper follows a simple client-server model where clients are nodes (i.e., machines) that make use of the service, and servers are nodes that provide the service. A collection of ZooKeeper servers forms a ZooKeeper ensemble.
     * - In zookeeper ensemble, all zookeeper server must all know about each other zookeeper server. They maintain an in-memory image of state, along with a transaction logs and snapshots in a persistent store.
     * - A quorum is the minimum number of members of a deliberative assembly necessary to conduct the business of that group.
     * - Reads are always read from the ZooKeeper server connected to the client, so their performance doesn't change as the number of servers in the ensemble changes. However, writes are successful only when written to a quorum of nodes.
     * - If a quorum of nodes are not available in an ensemble, the ZooKeeper service is nonfunctional.
     * - Because Zookeeper requires a majority, it is best to use an odd number of machines.
     *
     * In ZooKeeper, an even number of peers is supported, but it is normally not used because an
     * even sized ensemble requires, proportionally, more peers to form a quorum than an odd sized
     * ensemble requires. consider a case when you have 4 nodes in your cluster. Zookeeper will
     * remain up if at least 3 nodes are up (>4/2). So effectively you can handle failure of 1
     * nodes. If you had 3 nodes in your cluster, you would need at least 2 nodes up for the
     * zookeeper to function (>3/2). Hence even in 3 node cluster, you can handle failure of 1
     * nodes. So having 4th node doesn't give any additional advantage at all.
     *
     * Lets have one more example, as you know an ensemble with 4 nodes requires 3 to form a quorum,
     * while an ensemble with 5 also requires 3 nodes to form a quorum. Thus, an ensemble of 5
     * allows 2 nodes to fail, and thus is more fault tolerant than the ensemble of 4, which allows
     * only 1 down peer.
     *
     * Similarly, Zookeeper elects a master based on the opinion of more than half of the nodes from
     * the cluster. And finally, it keeps functioning if and only if more than half of the nodes are
     * up. This is basically to server the consistency part of CAP theorem.
     *
     * To summarise, If you would like to run one server, that's fine from ZooKeeper's perspective;
     * you just won't have a highly reliable or available system. A three-node ZooKeeper ensemble
     * will support one failure without loss of service, which is probably fine for most users and
     * arguably the most common deployment topology. However, to be safe, use five nodes in your
     * ensemble. A five-node ensemble allows you to take one server out for maintenance or rolling
     * upgrade and still be able to withstand a second unexpected failure without interruption of
     * service.
     *
     * In short three, five, or seven is the most typical number of nodes in a ZooKeeper ensemble,
     * the more members an ensemble has, the more tolerant the ensemble is of host failures.
     *
     * Keep in mind that the size of your ZooKeeper ensemble has little to do with the size of the
     * nodes in your distributed system. The nodes in your distributed system would be clients to
     * the ZooKeeper ensemble, and each ZooKeeper server can handle a large number of clients
     * scalably. For example, HBase (a distributed database on Hadoop) relies upon ZooKeeper for
     * leader election and lease management of region servers. You can have a large 75 node HBase
     * cluster running with a relatively small say, five or seven node ZooKeeper ensemble.
     *
     * Leader election: http://www.corejavaguru.com/bigdata/zookeeper/leader-election
     *
     * Establishing a new leader:
     * - 3 possible states: ELECTION, LEADING, FOLLOWING
     *
     * The protocol is extremely simple. When a server enters the LOOKING state, it sends a batch of notification
     * messages, one to each of the other servers in the ensemble. The message contains its current vote, which
     * consists of the server’s identifier (sid) and the zxid (zxid) of the most recent transaction it executed.
     *
     * Upon receiving a vote, a server changes its vote according to the following rules:
     * 1. Let voteId and voteZxid be the identifier and the zxid in the current vote of the receiver, whereas myZxid
     *    and mySid are the values of the receiver itself.
     * 2. If (voteZxid > myZxid) or (voteZxid = myZxid and voteId > mySid), keep the current vote.
     * 3. Otherwise, change my vote by assigning myZxid to voteZxid and mySid to voteId.
     *
     * In short, the server that is most up to date wins, because it has the most recent zxid.
     *
     * Leader Protocol in a nutshell:
     * 1. At startup wait for a quorum of followers to connect
     * 2. Sync with a quorum of followers
     *   - Tell the follower to delete any transaction that the leader doesn't have
     *   - Send any transactions that the follower doesn't have
     * 3. Continually
     *   - Assign an zxid to any message to be proposed and broadcast proposals to followers
     *   - When a quorum has acked a proposal, broadcast a commit
     *
     * Follower protocol in a nutshell:
     * 1. Connect to a leader
     * 2. Delete any transactions in the txn log that the leader says to delete
     * 3. Continually
     *   - Log to the transactions log and send an ack to leader
     *   - Deliver any committed transactions
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
