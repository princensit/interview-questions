package com.prince.linux;

/**
 * @author Prince Raj
 */
public class Main {

    /**
     * <pre>
     *
     * Monitor linux performance:
     *
     * CPU and Memory
     * - htop
     * - vmstat
     * - free -m
     * - sar -Ap
     * - monit
     * - monitorix
     * Disk
     * - iotop
     * - iostat
     * Network
     * - iftop
     * - netstat
     * - iptraf
     * - nethogs wlan0 (per process)
     * - tcpdump
     * Open files
     * - lsof
     * User activity
     * - pssact or acct
     * Ethernet activity monitor
     *
     * New commands:
     * 1. shift + pgup
     * 2. pv
     * 3. strace - lets you see what kind of system/kernel calls a process is making. Makes it easier to debug third party programs you don't have the source code for. can be useful in debugging shell scripts also.
     *    strace <command>
     *    strace -p <pid>
     * 4. units '2 feet' '1 inches'
     * 5. <(...) - Creates a temp file with the output of the commands within (...), which can be used as an arg to another command.
     *    diff <(sort samples1.txt) <(sort samples2.txt)
     * 6. http://code.google.com/p/logstalgia/
     * 7. disown
     *
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

    }

}
