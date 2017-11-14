package com.prince.design;

/**
 * http://javahungry.blogspot.com/2013/09/round-robin-scheduling-algorithm-with-example-java-program-code.html
 *
 * Turnaround time: The total time taken by the process between starting and the completion
 *
 * Waiting time: The time for which process is ready to run but not executed by CPU scheduler
 *
 * CPU scheduler picks the process from the circular/ready queue, set a timer to interrupt it after 1 time slice /
 * quantum and dispatches it .
 *
 * If process has burst time less than 1 time slice/quantum
 * <ul>
 * <li>Process will leave the CPU after the completion</li>
 * <li>CPU will proceed with the next process in the ready queue / circular queue</li>
 * </ul>
 * else If process has burst time longer than 1 time slice/quantum
 * <ul>
 * <li>Timer will be stopped. It cause interruption to the OS</li>
 * <li>Executed process is then placed at the tail of the circular / ready querue by applying the context switch</li>
 * <li>CPU scheduler then proceeds by selecting the next process in the ready queue</li>
 * </ul>
 *
 * @author Prince Raj
 */
public class RoundRobin {

}
