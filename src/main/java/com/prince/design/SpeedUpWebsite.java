package com.prince.design;

/**
 * https://developer.yahoo.com/performance/rules.html?guccounter=1
 *
 * <pre>
 *  Best Practices for Speeding Up Your Web Site:
 *  1. Make Fewer HTTP Requests
 *     a. Combine JS, CSS files
 *     b. Use CSS Sprites
 *     c. Inline images use the data: URL scheme
 *  2. Use a Content Delivery Network
 *  3. Add an Expires or a Cache-Control Header
 *     a. For static components: implement "Never expire" policy by setting far future Expires header
 *     b. For dynamic components: use an appropriate Cache-Control header to help the browser with conditional requests
 *  4. Gzip Components
 *     a. Starting with HTTP/1.1, web clients indicate support for compression with the Accept-Encoding header in the HTTP request.
 *        Accept-Encoding: gzip, deflate
 *     b. If the web server sees this header in the request, it may compress the response using one of the methods listed
 *        by the client. The web server notifies the web client of this via the Content-Encoding header in the response.
 *        Content-Encoding: gzip
 *  5. Put CSS at the Top
 *     a. Moving stylesheets to the document HEAD makes pages appear to be loading faster.
 *  6. Move Scripts to the Bottom
 *     a. The problem caused by scripts is that they block parallel downloads.
 *     b. The HTTP/1.1 specification suggests that browsers download no more than two components in parallel per hostname.
 *        If you serve your images from multiple hostnames, you can get more than two downloads to occur in parallel.
 *        While a script is downloading, however, the browser won't start any other downloads, even on different hostnames.
 *     c. In some situations it's not easy to move scripts to the bottom. If, for example, the script uses document.write
 *        to insert part of the page's content, it can't be moved lower in the page. There might also be scoping issues.
 *        In many cases, there are ways to workaround these situations.
 *        An alternative suggestion that often comes up is to use deferred scripts. The DEFER attribute indicates that
 *        the script does not contain document.write, and is a clue to browsers that they can continue rendering.
 *        Unfortunately, Firefox doesn't support the DEFER attribute. In Internet Explorer, the script may be deferred,
 *        but not as much as desired. If a script can be deferred, it can also be moved to the bottom of the page. That
 *        will make your web pages load faster.
 *  7. Avoid CSS Expressions
 *     a. CSS expressions are a powerful (and dangerous) way to set CSS properties dynamically.
 *        Ex - background-color: expression( (new Date()).getHours()%2 ? "#B8D4FF" : "#F08A00" );
 *  8. Make JavaScript and CSS External
 *     a. Using external files in the real world generally produces faster pages because the JavaScript and CSS files
 *        are cached by the browser. JavaScript and CSS that are inlined in HTML documents get downloaded every time the
 *        HTML document is requested. This reduces the number of HTTP requests that are needed, but increases the size
 *        of the HTML document. On the other hand, if the JavaScript and CSS are in external files cached by the browser,
 *        the size of the HTML document is reduced without increasing the number of HTTP requests.
 *  9. Reduce DNS Lookups
 *     a. DNS typically takes 20-120 milliseconds for DNS to lookup the IP address for a given hostname.
 *     b. DNS lookups are cached for better performance. This caching can occur on a special caching server, maintained
 *        by the user's ISP or local area network, but there is also caching that occurs on the individual user's computer.
 *     c. Internet Explorer caches DNS lookups for 30 minutes by default, as specified by the DnsCacheTimeout registry
 *        setting. Firefox caches DNS lookups for 1 minute, controlled by the network.dnsCacheExpiration configuration setting.
 *  10. Minify JavaScript
 *     a. When code is minified all comments are removed, as well as unneeded white space characters (space, newline, and tab).
 *  11. Avoid Redirects
 *     a. Redirects are accomplished using the 301 and 302 status codes. Here's an example of the HTTP headers in a 301 response:
 *        Ex - HTTP/1.1 301 Moved Permanently
 *             Location: http://example.com/newuri
 *             Content-Type: text/html
 *  12. Remove Duplicate Scripts
 *  13. Configure ETags
 *     a. Entity tags (ETags) are a mechanism that web servers and browsers use to determine whether the component in the browser's cache matches the one on the origin server.
 *     b. (An "entity" is another word a "component": images, scripts, stylesheets, etc.)
 *     c. ETags were added to provide a mechanism for validating entities that is more flexible than the last-modified date. An ETag is a string that uniquely identifies a specific version of a component. The only format constraints are that the string be quoted. The origin server specifies the component's ETag using the ETag response header.
 *        Ex - HTTP/1.1 200 OK
 *             Last-Modified: Tue, 12 Dec 2006 03:03:59 GMT
 *             ETag: "10c24bc-4ab-457e1c1f"
 *             Content-Length: 12195
 * </pre>
 *
 * @author Prince Raj
 */
public class SpeedUpWebsite {

}
