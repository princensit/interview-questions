package com.prince.javaconcepts;

/**
 * <pre>
 *
 * The servlet is a class used to process client requests and generate dynamic web content. Servlets are mostly used to
 * process or store data submitted by an HTML form, provide dynamic content and manage state information that does not
 * exist in the stateless HTTP protocol.
 *
 * Architecture: The core abstraction that must be implemented by all servlets is the javax.servlet.Servlet interface.
 * Each servlet must implement it either directly or indirectly, either by extending javax.servlet.GenericServlet or
 * javax.servlet.http.HTTPServlet. Finally, each servlet is able to serve multiple requests in parallel using multithreading.
 *
 * An Applet is a client side java program that runs within a Web browser on the client machine. On the other hand, a
 * servlet is a server side component that runs on the web server.An applet can use the user interface classes, while a
 * servlet does not have a user interface. Instead, a servlet waits for client’s HTTP requests and generates a response
 * in every request.
 *
 * GenericServlet and HttpServlet:
 * GenericServlet is a generalized and protocol-independent servlet that implements the Servlet and ServletConfig interfaces.
 * Those servlets extending the GenericServlet class shall override the service method. Finally, in order to develop an
 * HTTP servlet for use on the Web that serves requests using the HTTP protocol, your servlet must extend the HttpServlet
 * instead.
 *
 * Life cycle of a Servlet:
 * On every client’s request, the Servlet Engine loads the servlets and invokes its init methods, in order for the servlet
 * to be initialized. Then, the Servlet object handles all subsequent requests coming from that client, by invoking the
 * service method for each request separately. Finally, the servlet is removed by calling the server’s destroy method.
 *
 * The GET method appends the name-value pairs on the request’s URL. Thus, there is a limit on the number of characters
 * and subsequently on the number of values that can be used in a client’s request. Furthermore, the values of the
 * request are made visible and thus, sensitive information must not be passed in that way.
 *
 * The POST method overcomes the limit imposed by the GET request, by sending the values of the request inside its body.
 * Also, there is no limitations on the number of values to be sent across. Finally, the sensitive information passed
 * through a POST request is not visible to an external client.
 *
 * A Web application is a dynamic extension of a Web or application server.
 * There are two types of web applications: presentation-oriented and service-oriented.
 * A presentation-oriented Web application generates interactive web pages, which contain various types of markup
 * language and dynamic content in response to requests. On the other hand, a service-oriented web application implements
 * the endpoint of a web service. In general, a Web application can be seen as a collection of servlets installed under
 * a specific subset of the server’s URL namespace.
 *
 * Server Side Includes (SSI) is a simple interpreted server-side scripting language, used almost exclusively for the Web,
 * and is embedded with a servlet tag. The most frequent use of SSI is to include the contents of one or more files into
 * a Web page on a Web server. When a Web page is accessed by a browser, the Web server replaces the servlet tag in that
 * Web page with the hyper text generated by the corresponding servlet.
 *
 * Servlet Chaining is the method where the output of one servlet is sent to a second servlet. The output of the second
 * servlet can be sent to a third servlet, and so on. The last servlet in the chain is responsible for sending the
 * response to the client.
 *
 * The ServletRequest class has functions for finding out the IP address or host name of the client machine.
 * getRemoteAddr() gets the IP address of the client machine and getRemoteHost() gets the host name of the client machine.
 *
 * The HTTP response consists of three parts:
 * 1. Status Code:
 *    If servlet does not return a status code, the success status code, HttpServletResponse.SC_OK, is returned by default.
 * 2. HTTP Headers:
 * 3. Body:
 *    The body may contain HTML code, an image, etc. The body consists of the data bytes transmitted in an HTTP transaction
 *    message immediately following the headers
 *
 * A cookie is a bit of information that the Web server sends to the browser. The browser stores the cookies for each
 * Web server in a local file. In a future request, the browser, along with the request, sends all stored cookies for
 * that specific Web server.The differences between session and a cookie are the following:
 * 1. The session should work, regardless of the settings on the client browser. The client may have chosen to disable
 *    cookies. However, the sessions still work, as the client has no ability to disable them in the server side.
 * 2. The session and cookies also differ in the amount of information that can store. The HTTP session is capable of
 *    storing any Java object, while a cookie can only store String objects.
 *
 * HTTP Tunneling is a technique by which, communications performed using various network protocols are encapsulated
 * using the HTTP or HTTPS protocols. The HTTP protocol therefore acts as a wrapper for a channel that the network
 * protocol being tunneled uses to communicate. The masking of other protocol requests as HTTP requests is HTTP Tunneling.
 *
 * The sendRedirect method creates a new request, while the forward method just forwards a request to a new target.
 * The previous request scope objects are not available after a redirect, because it results in a new request. On the
 * other hand, the previous request scope objects are available after forwarding. FInally, in general, the sendRedirect
 * method is considered to be slower compare to the forward method.
 *
 * The URL encoding procedure is responsible for replacing all the spaces and every other extra special character of a
 * URL, into their corresponding Hex representation. In correspondence, URL decoding is the exact opposite procedure.
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class Servlets {

}