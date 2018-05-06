package com.prince.design;

/**
 * http://highscalability.com/blog/2012/5/16/big-list-of-20-common-bottlenecks.html
 * 
 * <pre>
 *
 *  1. Database:
 *     a) Working size exceeds available RAM
 *     b) Long & short running queries
 *     c) Write-write conflicts
 *     d) Large joins taking up memory
 *  2. Virtualisation:
 *     a) Sharing a HDD, disk seek death
 *     b) Network I/O fluctuations in the cloud
 *  3. Programming:
 *     a) Threads: deadlocks, heavyweight as compared to events, debugging, non-linear scalability, etc...
 *     b) Event driven programming: callback complexity, how-to-store-state-in-function-calls, etc...
 *     c) Lack of profiling, lack of tracing, lack of logging
 *     d) One piece can't scale, SPOF, non horizontally scalable, etc...
 *     e) Stateful apps
 *     f) Bad design : The developers create an app which runs fine on their computer. The app goes into production, and
 *        runs fine, with a couple of users. Months/Years later, the application can't run with thousands of users and
 *        needs to be totally re-architectured and rewritten.
 *     g) Algorithm complexity
 *     h) Dependent services like DNS lookups and whatever else you may block on.
 *     i) Stack space
 *  4. Disk:
 *     a) Local disk access
 *     b) Random disk I/O -> disk seeks
 *     c) Disk fragmentation
 *     d) SSDs performance drop once  data written is greater than SSD size
 *  5. OS:
 *     a) Fsync flushing, linux buffer cache filling up
 *     b) TCP buffers too small
 *     c) File descriptor limits
 *     d) Power budget
 *  6. Caching:
 *     a) Not using memcached (database pummeling)
 *     b) In HTTP: headers, etags, not gzipping, etc..
 *     c) Not utilising the browser's cache enough
 *     d) Byte code caches (e.g. PHP)
 *     e) L1/L2 caches. This is a huge bottleneck. Keep important hot/data in L1/L2. This spans so much: snappy for
 *        network I/O, column DBs run algorithms directly on compressed data, etc. Then there are techniques to not
 *        destroy your TLB. The most important idea is to have a firm grasp on computer architecture in terms of CPUs
 *        multi-core, L1/L2, shared L3, NUMA RAM, data transfer bandwidth/latency from DRAM to chip, DRAM caches
 *        DiskPages, DirtyPages, TCP packets travel thru CPU<->DRAM<->NIC.
 *  7. CPU:
 *     a) CPU overload
 *     b) Context switches -> too many threads on a core, bad luck w/ the linux scheduler, too many system calls, etc...
 *     c) IO waits -> all CPUs wait at the same speed
 *     d) CPU Caches: Caching data is a fine grained process (In Java think volatile for instance), in order to find the
 *        right balance between having multiple instances with different values for data and heavy synchronization to
 *        keep the cached data consistent.
 *     e) Backplane throughput
 *  8. Network:
 *     a) NIC maxed out, IRQ saturation, soft interrupts taking up 100% CPU
 *     b) DNS lookups
 *     c) Dropped packets
 *     d) Unexpected routes with in the network
 *     e) Network disk access
 *     f) Shared SANs
 *     g) Server failure -> no answer anymore from the server
 *  9. Process:
 *     a) Testing time
 *     b) Development time
 *     c) Team size
 *     d) Budget
 *     e) Code debt
 *  10. Memory:
 *     a) Out of memory -> kills process, go into swap & grind to a halt
 *     b) Out of memory causing Disk Thrashing (related to swap)
 *     c) Memory library overhead
 *     d) Memory fragmentation
 *     e) In Java requires GC pauses
 *     f) In C, malloc's start taking forever
 * </pre>
 *
 * @author Prince Raj
 */
public class CommonBottlenecks {

}
