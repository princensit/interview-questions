package com.prince.concept;

/**
 * https://www.digitalocean.com/community/tutorials/http-1-1-vs-http-2-what-s-the-difference
 *
 * HTTP/1.0, the client had to break and remake the TCP connection with every new request, a costly
 * affair in terms of both time and resources.
 *
 * HTTP/1.1 takes care of this problem by introducing persistent connections and pipelining. With
 * persistent connections, HTTP/1.1 assumes that a TCP connection should be kept open unless
 * directly told to close. This allows the client to send multiple requests along the same
 * connection without waiting for a response to each, greatly improving the performance of HTTP/1.1
 * over HTTP/1.0.
 *
 * Since multiple data packets cannot pass each other when traveling to the same destination, there
 * are situations in which a request at the head of the queue that cannot retrieve its required
 * resource will block all the requests behind it. This is known as head-of-line (HOL) blocking,
 *
 * HTTP/2, the binary framing layer encodes requests/responses and cuts them up into smaller packets
 * of information, greatly increasing the flexibility of data transfer.
 *
 * HTTP/2 establishes a single connection object between the two machines. Within this connection
 * there are multiple streams of data. Each stream consists of multiple messages in the familiar
 * request/response format. Finally, each of these messages split into smaller units called frames:
 *
 * At the most granular level, the communication channel consists of a bunch of binary-encoded
 * frames, each tagged to a particular stream. The identifying tags allow the connection to
 * interleave these frames during transfer and reassemble them at the other end. The interleaved
 * requests and responses can run in parallel without blocking the messages behind them, a process
 * called multiplexing. Multiplexing resolves the head-of-line blocking issue in HTTP/1.1 by
 * ensuring that no message has to wait for another to finish. This also means that servers and
 * clients can send concurrent requests and responses, allowing for greater control and more
 * efficient connection management.
 *
 * Buffer Overflow:
 *
 * HTTP/1.1 relies on the transport layer to avoid buffer overflow, each new TCP connection requires
 * a separate flow control mechanism. When this connection initiates, both client and server
 * establish their buffer sizes using their system default settings. If the receiver's buffer is
 * partially filled with data, it will tell the sender its receive window, i.e., the amount of
 * available space that remains in its buffer.
 *
 * HTTP/2 solves this problem by allowing the client and server to implement their own flow
 * controls, rather than relying on the transport layer.
 *
 * HTTP/2 — Stream Prioritization, multiplexing, flow control, server push, and compression
 */
public class Http {
}
