package com.prince.systemdesign;

/**
 * Long-Polling, WebSockets, and Server-Sent Events are popular communication protocols between a
 * client like a web browser and a web server.
 *
 * 1. Ajax Polling: Polling is a standard technique used by the vast majority of AJAX applications.
 * The basic idea is that the client repeatedly polls (or requests) a server for data. The client
 * makes a request and waits for the server to respond with data. If no data is available, an empty
 * response is returned.
 *
 * The problem with Polling is that the client has to keep asking the server for any new data. As a
 * result, a lot of responses are empty, creating HTTP overhead.
 *
 * 2. HTTP Long-Polling: This is a variation of the traditional polling technique that allows the
 * server to push information to a client whenever the data is available. With Long-Polling, the
 * client requests information from the server exactly as in normal polling, but with the
 * expectation that the server may not respond immediately. That’s why this technique is sometimes
 * referred to as a "Hanging GET".
 *
 * Each Long-Poll request has a timeout. The client has to reconnect periodically after the
 * connection is closed due to timeouts.
 *
 * 3. WebSockets: WebSocket provides full duplex communication channels over a single TCP
 * connection. It provides a persistent connection between a client and a server that both parties
 * can use to start sending data at any time. The client establishes a WebSocket connection through
 * a process known as the WebSocket handshake.
 *
 * If the process succeeds, then the server and client can exchange data in both directions at any
 * time. The WebSocket protocol enables communication between a client and a server with lower
 * overheads, facilitating real-time data transfer from and to the server. In this way, a two-way
 * (bi-directional) ongoing conversation can take place between a client and a server.
 *
 * 4. Server-Sent Events (SSEs): Under SSEs the client establishes a persistent and long-term
 * connection with the server. The server uses this connection to send data to a client. If the
 * client wants to send data to the server, it would require the use of another technology/protocol
 * to do so.
 *
 * SSEs are best when we need real-time traffic from the server to the client or if the server is
 * generating data in a loop and will be sending multiple events to the client.
 *
 * @author Prince Raj
 */
public class LongPolling_WebSockets_ServerSentEvents {
}
