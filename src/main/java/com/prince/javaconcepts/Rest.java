package com.prince.javaconcepts;

/**
 * <pre>
 *  Representational State Transfer (REST) is an architectural style that defines a set of constraints and properties
 *  based on HTTP.
 *
 *  Web Services that conform to the REST architectural style, or RESTful web services, provide interoperability between
 *  computer systems on the Internet.
 *
 *  Architectural constraints:
 *  1. Uniform interface
 *     A resource in system should have only one logical URI
 *  2. Client–server
 *     The principle behind the client–server constraints is the separation of concerns.. This essentially means that
 *     client and server application MUST be able to evolve separately without any dependency on each other.
 *  3. Stateless
 *     The client–server communication is constrained by no client context being stored on the server between requests.
 *     Each request from any client contains all the information necessary to service the request, and session state is
 *     held in the client. The session state can be transferred by the server to another service such as a database to
 *     maintain a persistent state for a period and allow authentication.
 *  4. Cacheable
 *     Caching of data and responses is of utmost important wherever they are applicable/possible. Caching brings
 *     performance improvement for client side, and better scope for scalability for server because load has reduced.
 *  5. Layered system
 *     A client cannot ordinarily tell whether it is connected directly to the end server, or to an intermediary along
 *     the way. Intermediary servers may improve system scalability by enabling load balancing and by providing shared
 *     caches. They may also enforce security policies.
 *  6. Code on demand (optional)
 *     Most of the time you will be sending the static representations of resources in form of XML or JSON. But when you
 *     need to, you are free to return executable code to support a part of your application e.g. clients may call your
 *     API to get a UI widget rendering code.
 *
 *
 *  HTTP status code:
 *  200 - SUCCESS
 *  201 - CREATED
 *  204 - NO CONTENT
 *  206 - PARTIAL CONTENT
 *  301 - PERMANENT REDIRECTION
 *  302 - TEMPORARY REDIRECTION
 *  304 - NOT MODIFIED (if request packet header like If-Match, If-ModifiedSince, If-None-Match not satisfied)
 *  400 - BAD REQUEST
 *  401 - UNAUTHORIZED
 *  403 - FORBIDDEN
 *  404 - RESOURCE NOT FOUND
 *  500 - INTERNAL SERVER ERROR
 *  503 - SERVICE UNAVAILABLE
 *
 *  Versioning Options:
 *  URI Versioning
 *    http://localhost:8080/v1/person
 *    http://localhost:8080/v2/person
 *  Request Param Versioning
 *    http://localhost:8080/person/param?version=1
 *    http://localhost:8080/person/param?version=2
 *  Header Versioning
 *    http://localhost:8080/person/
 *      headers=[X-API-VERSION=1]
 *    http://localhost:8080/person/
 *      headers=[X-API-VERSION=2]
 *
 *  URI(Uniform Resource Identifier) contain URL(Uniform Resource Locator) and URN (Uniform Resource Name, Ex- urn:isbn:0-123-45678-9)
 *
 *  HEAD - Same as the GET method but does not return the body of the message entity. Mainly used to confirm the validity
 *         of the URL and the date and time of the resource update.
 *
 *  Persistent connections need to be managed using the Connection header field.
 *  HTTP/1.1 is a persistent connection by default..
 *  To disconnect, use "Connection: close".
 *  To maintain a continuous connection, use "Connection : Keep-Alive"
 *
 *  Multi-part object collection:
 *  A message body can contain multiple types of entities that are sent at the same time. Each section is separated by
 *  a delimiter defined by the boundary field. Each section can have a header field.
 *  Ex-
 *    Content-Type: multipart/form-data; boundary=AaB03x
 *
 *     --AaB03x
 *     Content-Disposition: form-data; name="submit-name"
 *
 *     Larry
 *     --AaB03x
 *     Content-Disposition: form-data; name="files"; filename="file1.txt"
 *     Content-Type: text/plain
 *
 *     ... contents of file1.txt ...
 *     --AaB03x--
 *
 *  Gateway:
 *  Unlike proxy servers, gateway servers translate HTTP to other protocols for communication, requesting services from
 *  other non-HTTP servers.
 *
 *  Tunnel:
 *  Use an encryption method such as SSL to establish a secure communication line between the client and the server.
 *
 *  Cookies set with HttpOnly can prevent JavaScript scripts from being invoked to a certain extent to prevent XSS
 *  attacks from stealing user's cookie information.
 *
 *  GET, HEAD, PUT, and DELETE are idempotent but the POST method is not.
 *
 *  XMLHttpRequest is an API that provides clients with the ability to transfer data between the client and the server.
 *  It provides a simple way to get data through a URL and does not refresh the entire page. This allows the web page
 *  to update only a portion of The page without disturbing the user. XMLHttpRequest is heavily used in AJAX.
 *
 *  HTTP/1.x defects
 *  1. Clients need to use multiple connections to achieve concurrency and reduce latency;
 *  2. Does not compress request and response headers, resulting in unnecessary network traffic;
 *  3. Does not support effective resource priority, resulting in low utilization of the underlying TCP connection.
 *
 *  HTTP/2.0 divides the message into HEADERS frames and DATA frames, both of which are in binary format.
 *
 *  HTTP/2.0 When a client requests a resource, it sends related resources to the client, and the client does not need
 *  to initiate the request again. For example, the client requests a page.html page, and the server sends the associated
 *  resources As script.js and style.css to the client.
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class Rest {

}
