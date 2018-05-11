package com.prince.javaconcepts;

/**
 * <pre>
 *
 * Spring is popular because of clear separation on concerns:
 * 1. Dispatcher Servlet
 * 2. View Resolver
 * 3. View
 * 4. Model
 *
 * The singleton scope is the default scope in Spring.
 * The Gang of Four defines Singleton as having one and only one instance per ClassLoader.
 * However, Spring singleton is defined as one instance of bean definition per container.
 *
 * Spring bean scopes:
 * 1. singleton - One instance per Spring Context
 * 2. prototype - New bean whenever requested
 * 3. request - One bean per HTTP request. Web-aware Spring ApplicationContext.
 * 4. session - One bean per HTTP session. Web-aware Spring ApplicationContext.
 *
 * Types of dependency injections:
 * 1. constructor injection (for mandatory dependencies)
 * 2. setter injection (for optional dependencies)
 *
 * The Spring team generally advocates constructor injection as it enables one to implement application components as
 * immutable objects and to ensure that required dependencies are not null.
 *
 * Options to create application context:
 * 1. ApplicationContext context =
 *        new ClassPathXmlApplicationContext( new String[] {"BusinessApplicationContext.xml", "Other-Configuration.xml"});
 * 2. ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContext.class);
 *
 * Autowiring is done in following ways:
 * 1. byType (class or interface)
 * 2. byName
 * 3. constructor (similar to byType)
 *
 * @Primary annotation is used to resolve default bean in case conflicting with multiple beans of same type.
 * @Qualifier annotation is used to specify specific bean.
 *
 * CDI (Contexts and Dependency Injection): Java EE Dependency Injection Standard (JSR-330). Spring Supports most annotations
 *  @Inject (@Autowired)
 *  @Named (@Component & @Qualifier)
 *  @Singleton (Defines a scope of Singleton)
 *
 * Spring 2.5 (2006)  made annotation-driven configuration possible.
 * Spring 3.0 (2009) made great use of the Java 5 improvements in language.
 * Spring 4.0 (2013) made full support to Java 8. Introduced @RestController annotation. Spring 4.1 supports JCache (JSR-107) annotations
 * Spring 5.0 (2017) made support for functional web framework. Support for reactive programming and Kotlin programming language.
 *
 * Spring AOP is proxy-based. It is commonly used as an implementation of "cross-cutting concerns" which means it defines, in one place,
 * functionality that is needed in multiple places throughout a code.
 *
 * A cross-cutting concern that can affect the whole application should be centralized in one place in code as much as
 * possible, such as authentication, logging, transaction management, security, etc.
 *
 * AOP Terminologies:
 * 1. Join Points:
 *    A Join Point is a point in the execution of the application where an aspect can be plugged in. This point could be
 *    a method being called, an exception being thrown, or even a field being modified.
 * 2. Pointcuts:
 *    Pointcuts are expressions that are matched with Join points to determine whether advice needs to be executed or
 *    not. This is a set of more than one Join point where an advice should be executed.
 * 3. Advice:
 *    Advice defines both the when and where of an aspect. The actual action to be taken before or after method execution.
 *    Actual piece of code that is invoked during program execution by Spring's Aspect Oriented Programming framework.
 * 4. Aspects:
 *    An aspect is the merger of Advice and Pointcuts. Advice and pointcuts define everything known about an aspect,
 *    what it does and where and when it does it.
 * 5. Introductions:
 *    An Introduction allows adding new methods or attributes to existing classes. The new method and instance variable
 *    can be introduced to existing classes without having to change them, giving them new state and behavior.
 * 6. Weaving:
 *    Weaving is the process of applying aspects to a target object to create a new proxied object. The target object at
 *    the specified Join points.
 *
 *    The weaving can occur at several points in the target object’s lifetime:
 *    1. Compile Time:
 *       Aspects are woven in as the target class is compiled. It requires a special compiler. AspectJ’s weaving compiler
 *       weaves aspects this way.
 *    2. Classload Time:
 *       Aspects are woven in as the target class is loaded into the JVM. It requires a special ClassLoader that enhances
 *       that target class's bytecode before the class is introduced into the application. AspectJ 5’s load-time weaving
 *       (LTW) support weaves aspects this way.
 *    3. Runtime:
 *       Aspects are woven in sometime during the execution of the application. An AOP container will dynamically generate
 *       a proxy object that will delegate to the target object while weaving in the aspects.
 *
 * Spring projects:
 * 1. Spring Boot
 * 2. Spring Cloud
 * 3. Spring Data
 * 4. Spring Integration
 * 5. Spring Batch
 * 6. Spring Security
 * 7. Spring HATEOAS
 * 8. Spring Web Services
 * 9. Spring Session
 *
 * Design Patterns in Spring:
 * 1. Front Controller - Dispatcher Servlet
 * 2. Prototype - Beans
 * 3. Dependency Injection
 * 4. Factory Pattern - Bean Factory & Application Context
 *
 * -------------
 *
 * Path variable:
 * @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
 * @ResponseBody
 * public User retrieveUser(@PathVariable int id) {
 *    return service.retrieveUser(id);
 * }
 *
 * Model attribute (invoked before @RequestMapping methods):
 * @ModelAttribute
 * public void addAttributes(Model model) {
 *    model.addAttribute("options", Arrays.asList("Option 1","Option 2","Option 3" ));
 * }
 *
 * Session attribute (stored in session or conventional storage):
 * @SessionAttributes("name")
 * public class TodoController {
 * }
 *
 * -------------
 *
 * @InitBinder
 * protected void initBinder(WebDataBinder binder) {
 *    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"
 *    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
 * }
 *
 * @ControllerAdvice
 * public class ExceptionController {
 *    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
 *
 *    @ExceptionHandler(value = Exception.class)
 *    public String handleException (HttpServletRequest request, Exception ex) {
 *           logger.error("Request " + request.getRequestURL() + " Threw an Exception", ex);
 *           return "error";
 *    }
 * }
 *
 * @ResponseStatus(HttpStatus.NOT_FOUND)
 * public class UserNotFoundException extends RuntimeException {
 * }
 *
 * -------------
 *
 * Spring Boot - quick to setup microservice.
 * Features:
 * 1. Auto Configuration
 * 2. Embedded Server
 * 3. Spring Boot Starter Projects
 * 4. Spring Boot Actuator
 *
 * Actuator - Actuator is mainly used to expose operational information about the running application like /health,
 * /metrics, /info, /dump, /env, /beans, etc. It uses HTTP endpoints or JMX beans to enable us to interact with it.
 *
 * Custom endpoints:
 * endpoints.beans.id=springbeans
 * endpoints.beans.sensitive=false
 * endpoints.beans.enabled=true
 *
 * logging.level.org.springframework: DEBUG
 *
 * Spring boot's default embedded server is Tomcat. It also support Jetty and UnderTow.
 *
 * spring-boot-starter-parent artifact id links to spring-boot-dependencies, which tells all spring related dependencies.
 *
 * CommandLineRunner - interface used to indicate that a bean should run when it is contained within a SpringApplication.
 * public interface CommandLineRunner {
 *    void run(String... args) throws Exception;
 * }
 *
 * JDBC - Here we create connection and prepared statement, once execution done then connection is closed.
 * Spring JDBC - Here jdbcTemplate is used, and is not required to open/close connection. Additionally we have RowMapper
 * and RowCallbackHandler.
 *
 * @RunWith(MockitoJUnitRunner.class)
 * public class SomeBusinessMockAnnotationsTest {
 *    @Mock
 *    private DataService dataServiceMock;
 *
 *    @InjectMocks -> creates an instance of the class and injects the mocks that are created with the @Mock (or @Spy)
 *    annotations into this instance.
 *    private SomeBusinessImpl businessImpl;
 *
 *    @Test
 *    public void testFindTheGreatestFromAllData() {
 *      when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
 *      assertEquals(24, businessImpl.findTheGreatestFromAllData());
 *    }
 * }
 *
 * SOAP
 * 1. Format
 *    SOAP XML Request
 *    SOAP XML Response
 * 2. Transport
 *    SOAP over MQ
 *    SOAP over HTTP
 * 3. Service Definition
 *    WSDL (Web Service Definition Language)
 *
 * WSDL contain
 * - endpoint
 * - request structure
 * - response structure
 * - all operations
 *
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class Spring {

}
