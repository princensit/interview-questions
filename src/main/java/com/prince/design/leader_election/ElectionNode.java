package com.prince.design.leader_election;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Prince Raj
 */
public interface ElectionNode extends Remote {

    // Node methods
    String startElection(String senderName) throws RemoteException, DeadNodeException;

    void newLeader(String newLeaderName) throws RemoteException;

    String recvMsg(String senderName, String msg) throws RemoteException;

    // Election Driver methods
    void makeChaos(String newName, int ignore) throws RemoteException;
}
