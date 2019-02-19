package com.prince.systemdesign;

/**
 * https://vladmihalcea.com/caching-best-practices/
 * <p>
 * 1. A key/value collection is not a Cache
 * A Map is not an out-of-the-box Caching solution, since a Cache is more than a key/value store. A Cache also requires:
 * <p>
 * - eviction policies
 * - max size limit
 * - persistent store
 * - weak references keys
 * - statistics
 * Ex- Guava cache, Ehcache
 * <p>
 * 2. Use a cache abstraction layer
 * A very flexible solution is the Spring Cache abstraction. The @Cacheable annotation allows you to separate the
 * business logic code from the caching cross-cutting concern. The caching solution is therefore configurable and it’s
 * not going to pollute your business methods.
 * <p>
 * 3. Beware of the caching overhead
 * Every API has a cost and caching is no different. Even the Spring cache abstraction has an overhead, so make sure the
 * benefits outweigh the costs.
 * <p>
 * 4. If your database queries are slow, the cache should be your last resort
 * If you use an ORM tool like Hibernate, that’s the first place where your optimization process should start from.
 * Make sure the fetching strategy is properly designed, and you don’t suffer from N+1 query problems. You could also
 * assert the SQL statement count to validate the ORM generated queries.
 * <p>
 * When you’re done optimizing your ORM SQL query generation, you should check your database for slow queries. Make sure
 * all indexes are in place and that your SQL queries are effective.
 * The indexes must always fit into RAM, otherwise, you will hit the more expensive SSD or HDD. Your database has the
 * ability to cache query results, so take advantage of it.
 * <p>
 * If the data set is large and the growth rate is high you could horizontally scale it on multiple shards.
 * <p>
 * If all of those actions are not enough, you may consider a professional caching solution such as Memcached.
 * <p>
 * 5. What about data consistency?
 * When you start using a cache in front of your business layer, the data consistency constraint is being challenged.
 * The benefits of ACID may be compromised if the cache is not properly synchronized with the database. This is like
 * keeping a denormalized form of your actual data. If a root entity changes it may affect a large portion of your cache.
 * If you discard the cache entries, all the caching benefits are lost. If you asynchronously update the cache entries
 * you lose the strong data consistency, leaving you with an eventual consistent data model.
 */
public class CachingBestPractices {
}
