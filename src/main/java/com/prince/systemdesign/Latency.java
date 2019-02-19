package com.prince.systemdesign;

/**
 * https://igor.io/latency/
 *
 * latency is a measure of how "slow" something is. high latency means the system is taking a long
 * time to respond. low latency means the responses are fast.
 *
 * latency is the interval between two points in time.
 *
 * human reaction time is around ~215ms. So for websites you generally want to optimize for ~300ms
 * page load times or faster, and definitely under 1s
 *
 * sending packets over the internet takes some time, as transmission is limited by the speed of
 * light. this adds latency in the range of 100-200ms, depending on the location of the client and
 * the server.
 *
 * measuring latency:
 *
 * <pre>
 * started_at = time_monotonic()
 *
 * // code that does something, and takes some time
 *
 * finished_at = time_monotonic()
 * duration = finished_at - started_at
 * </pre>
 *
 * adding code that tracks latency is often called "instrumentation". These data points can be sent
 * to some dedicated metrics service, ex - statsd
 *
 * aggregation:
 *
 * aggregations are typically computed over a window of time, commonly 1 second or 10 seconds, that
 * interval is usually called resolution.
 *
 * 1) average
 *
 * It turns out that in the context of latency, the average is not a meaningful metric. the average
 * hides outliers (high latencies), which are the ones you are interested in.
 *
 * 2) percentile
 *
 * A commonly used aggregation for latency data is the percentile or quantile.
 *
 * the 99th percentile (p99) latency is the worst latency that was observed by 99% of all requests.
 * it is the maximum value if you ignore the top 1%.
 *
 * But p99 is still ignoring the worst 1% of requests. if you want to see the worst latencies, you
 * can take a look at the p100 latency, often called the maximum.
 *
 * 3) histograms
 *
 * Storing and computing percentiles is quite expensive. A histogram is a representation of a
 * distribution. It stores the range and count of values.
 *
 * instead of storing raw values, a histogram creates buckets and then sorts values into those
 * buckets. it only stores the count of each bucket.
 *
 * a nice property of histograms is that because they are based on counters, they can be merged by
 * adding up the counters. you can merge histograms from different machines, or combine them in the
 * time dimension (this is called a "roll-up").
 *
 * another nice property is that you don't have to decide ahead of time which percentiles you want.
 * since the histogram stores the full distribution, you can get any percentile out of it. though
 * the sizing of the buckets will impact how precise the percentiles will be.
 *
 * 4) heatmaps
 *
 * it visualises the full distribution by plotting time in the x-axis and latency buckets in the
 * y-axis. the color of the bucket indicates how many values fell into that bucket. this conveys
 * much more information than a single percentile would.
 *
 */
public class Latency {

    public static void main(String[] args) {

    }
}
