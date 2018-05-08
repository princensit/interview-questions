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
 *  301 - te
 *  404 - RESOURCE NOT FOUND
 *  400 - BAD REQUEST
 *  401 - UNAUTHORIZED
 *  500 - SERVER ERROR
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
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class Rest {

}
