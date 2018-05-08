package com.prince.javaconcepts;

/**
 * <pre>
 * A Java Server Page (JSP) is a text document that contains two types of text: static data and JSP elements. Static data
 * can be expressed in any text-based format, such as HTML or XML. JSP is a technology that mixes static content with
 * dynamically-generated content.
 *
 * On the arrival of a JSP request, the browser first requests a page with a .jsp extension. Then, the Web server reads
 * the request and using the JSP compiler, the Web server converts the JSP page into a servlet class. Notice that the
 * JSP file is compiled only on the first request of the page, or if the JSP file has changed.The generated servlet class
 * is invoked, in order to handle the browser’s request. Once the execution of the request is over, the servlet sends a
 * response back to the client.
 *
 * The advantages of using the JSP technology are shown below:
 * 1. JSP pages are dynamically compiled into servlets and thus, the developers can easily make updates to presentation
 * code.
 * 2. JSP pages can be pre-compiled.
 * 3. JSP pages can be easily combined to static templates, including HTML or XML fragments, with code that generates
 * dynamic content.
 * 4. Developers can offer customized JSP tag libraries that page authors access using an XML-like syntax.
 * 5. Developers can make logic changes at the component level, without editing the individual pages that use the
 * application’s logic.
 *
 * Directives are instructions that are processed by the JSP engine, when the page is compiled to a servlet. Directives
 * are used to set page-level instructions, insert data from external files, and specify custom tag libraries. Directives
 * are defined between < %@ and % >.The different types of directives are shown below:
 *
 * Include directive: it is used to include a file and merges the content of the file with the current page.
 * Page directive: it is used to define specific attributes in the JSP page, like error page and buffer.
 * Taglib: it is used to declare a custom tag library which is used in the page.
 *
 * JSP actions use constructs in XML syntax to control the behavior of the servlet engine. JSP actions are executed when
 * a JSP page is requested. They can be dynamically inserted into a file, re-use JavaBeans components, forward the user
 * to another page, or generate HTML for the Java plugin.Some of the available actions are listed below:
 *   jsp:include – includes a file, when the JSP page is requested.
 *   jsp:useBean – finds or instantiates a JavaBean.
 *   jsp:setProperty – sets the property of a JavaBean.
 *   jsp:getProperty – gets the property of a JavaBean.
 *   jsp:forward – forwards the requester to a new page.
 *   jsp:plugin – generates browser-specific code.
 *
 * A scriptlet is a piece of Java-code embedded in a JSP page. The scriptlet is everything inside the tags. Between these
 * tags, a user can add any valid scriplet.
 *
 * Declarations are similar to variable declarations in Java. Declarations are used to declare variables for subsequent
 * use in expressions or scriptlets. To add a declaration, you must use the sequences to enclose your declarations.
 *
 * A JSP expression is used to insert the value of a scripting language expression, converted into a string, into the
 * data stream returned to the client, by the web server. Expressions are defined between <% = and %> tags.
 *
 * JSP implicit objects are those Java objects that the JSP Container makes available to developers in each page. A
 * developer can call them directly, without being explicitly declared. JSP Implicit Objects are also called pre-defined
 * variables.The following objects are considered implicit in a JSP page:
 * 1. application
 * 2. page
 * 3. request
 * 4. response
 * 5. session
 * 6. exception
 * 7. out
 * 8. config
 * 9. pageContext
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class JavaServerPages {

}
