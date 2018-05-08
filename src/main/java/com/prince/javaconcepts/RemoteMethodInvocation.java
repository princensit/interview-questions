package com.prince.javaconcepts;

/**
 * <pre>
 *
 * The Java Remote Method Invocation (Java RMI) is a Java API that performs the object-oriented equivalent of remote
 * procedure calls (RPC), with support for direct transfer of serialized Java classes and distributed garbage collection.
 * Remote Method Invocation (RMI) can also be seen as the process of activating a method on a remotely running object.
 * RMI offers location transparency because a user feels that a method is executed on a locally running object.
 *
 * The RMI architecture is based on a very important principle which states that the definition of the behavior and the
 * implementation of that behavior, are separate concepts. RMI allows the code that defines the behavior and the code
 * that implements the behavior to remain separate and to run on separate JVMs.
 *
 * The RMI architecture consists of the following layers:
 * 1. Stub and Skeleton layer:
 *    This layer lies just beneath the view of the developer. This layer is responsible for intercepting method calls
 *    made by the client to the interface and redirect these calls to a remote RMI Service.
 * 2. Remote Reference Layer:
 *    The second layer of the RMI architecture deals with the interpretation of references made from the client to the
 *    server’s remote objects. This layer interprets and manages references made from clients to the remote service
 *    objects. The connection is a one-to-one (unicast) link.
 * 3. Transport layer:
 *    This layer is responsible for connecting the two JVM participating in the service. This layer is based on TCP/IP
 *    connections between machines in a network. It provides basic connectivity, as well as some firewall penetration
 *    strategies.
 *
 * The Remote interface in RMI serves to identify interfaces whose methods may be invoked from a non-local virtual
 * machine. Any object that is a remote object must directly or indirectly implement this interface. A class that
 * implements a remote interface should declare the remote interfaces being implemented, define the constructor for each
 * remote object and provide an implementation for each remote method in all remote interfaces.
 *
 * The java.rmi.Naming class provides methods for storing and obtaining references to remote objects in the remote object
 * registry. Each method of the Naming class takes as one of its arguments a name that is a String in URL format.
 *
 * Binding in RMI is the process of associating or registering a name for a remote object, which can be used at a later
 * time, in order to look up that remote object. A remote object can be associated with a name using the bind or rebind
 * methods of the Naming class.
 *
 * The bind() method is responsible for binding the specified name to a remote object, while the rebind() method is
 * responsible for rebinding the specified name to a new remote object. In case a binding exists for that name, the
 * binding is replaced.
 *
 * Steps involved in order for a RMI program to work properly:
 * 1. Compilation of all source files.
 * 2. Generation of the stubs using rmic.
 * 3. Start the rmiregistry.
 * 4. Start the RMIServer.
 * 5. Run the client program.
 *
 * A stub in RMI for a remote object acts as a client’s local representative or proxy for the remote object. The caller
 * invokes a method on the local stub, which is responsible for executing the method on the remote object. When a stub’s
 * method is invoked, it undergoes the following steps:
 * 1. It initiates a connection to the remote JVM containing the remote object.
 * 2. It marshals the parameters to the remote JVM.
 * 3. It waits for the result of the method invocation and execution.
 * 4. It unmarshals the return value or an exception if the method has not been successfully executed.
 * 5. It returns the value to the caller.
 *
 * DGC stands for Distributed Garbage Collection. Remote Method Invocation (RMI) uses DGC for automatic garbage collection.
 * Since RMI involves remote object references across JVM’s, garbage collection can be quite difficult. DGC uses a
 * reference counting algorithm to provide automatic memory management for remote objects.
 *
 * RMISecurityManager provides a security manager that can be used by RMI applications, which use downloaded code. The
 * class loader of RMI will not download any classes from remote locations, if the security manager has not been set.
 *
 * When an application wants to pass its memory objects across a network to another host or persist it to storage, the
 * in-memory representation must be converted to a suitable format. This process is called marshalling and the revert
 * operation is called demarshalling.
 *
 * Java provides a mechanism, called object serialization where an object can be represented as a sequence of bytes and
 * includes the object’s data, as well as information about the object’s type, and the types of data stored in the object.
 * Thus, serialization can be seen as a way of flattening objects, in order to be stored on disk, and later, read back
 * and reconstituted. Deserialisation is the reverse process of converting an object from its flattened state to a live
 * object.
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class RemoteMethodInvocation {

}
