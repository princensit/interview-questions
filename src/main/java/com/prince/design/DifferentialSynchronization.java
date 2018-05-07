package com.prince.design;

/**
 * https://neil.fraser.name/writing/sync/
 *
 * <pre>
 * 1. Conventional Strategies:
 *   a. Locking - Not preferable for varying reasons
 *   b. Event passing -
 *      It relies on capturing all user actions and mirroring them across the network to other users.
 *      Algorithms based on Operation Transformation[?] are currently popular for implementing edit-based collaborative
 *      systems. Obtaining a snapshot of the state is usually trivial, but capturing edits is a different matter altogether.
 *      A practical challenge with event passing synchronization is that all user actions must be captured. Obvious ones
 *      include typing, but edits such as cut, paste, drag, drop, replacements and autocorrect must also be caught.
 *      The richness of modern user interfaces can make this problematic, especially within a browser-based environment.
 *
 *      Any failure during edit passing results in a fork. Since each edit changes the location of subsequent edits, one
 *      lost edit may cause subsequent edits to be applied incorrectly, thus increasing the gap between the two versions.
 *
 *      Three-way merge process:
 *        a. The client sends the contents of the document to the server.
 *        b. The server performs a three-way merge to extract the user's changes and merge	them with changes from other users.
 *        c. The server sends a new copy of the document to the client.
 *
 *      Three-way merges are not a good solution for real-time collaboration across a network with latency.
 *
 * 2. Differential Synchronization:
 *    Differential synchronization is a symmetrical algorithm employing an unending cycle of background difference (diff)
 *    and patch operations.
 *
 *    See image: differential_synchronization_1_basic.gif
 *
 *    The following walk-through starts with Client Text, Common Shadow and Server Text all being equal. Client Text and
 *    Server Text may be edited at any time. The goal is to keep these two texts as close as possible with each other
 *    at all times.
 *    a. Client Text is diffed against the Common Shadow.
 *    b. This returns a list of edits which have been performed on Client Text.
 *    c. Client Text is copied over to Common Shadow. This copy must be identical to the value of Client Text in step 1, so
 *       in a multi-threaded environment a snapshot of the text should have been taken.
 *    d. The edits are applied to Server Text on a best-effort basis.
 *    e. Server Text is updated with the result of the patch. Steps 4 and 5 must be atomic, but they do not have to be blocking; they may be repeated until Server Text stays still long enough.
 *
 *    The process now repeats symmetrically in the other direction.
 *
 *    The enabling feature is that the patch algorithm is fuzzy, meaning patches may be applied even if the document has
 *    changed. Thus if the client has typed a few keystrokes in the time that the synchronization took to complete, the
 *    patches from the server are likely to have enough recognizable context that they may still be applied successfully.
 *    However, if some or all of the patches fail in step 4, they will automatically show up negatively in the following
 *    diff and will be patched out of the Client Text.
 *
 * 3. Dual Shadow Method
 *    Instead of one shadow, we would have both client and server shadow.
 *
 *    See image: differential_synchronization_2_dual_shadow.gif
 *
 *    However, on a network with best-effort delivery, nothing is guaranteed. Therefore a simple checksum of Client
 *    Shadow ought to be sent along with the Edits and compared to Server Shadow after the patches have been applied.
 *    If the checksum fails to match, then something went wrong and one side or the other must transmit the whole body
 *    of the text to get the two parties back in sync. This will result in data loss equal to one synchronization cycle.
 *
 * 4. Guaranteed Delivery Method
 *    In the event of a transitory network failure, an outbound or a return packet may get lost. In this case the client
 *    might stop synchronizing for a while until the connection times out. When the connection is restored on the
 *    following synchronization, the shadows will be out of sync which requires a transmission of the full text to get
 *    back in sync. This will destroy all changes since the previous successful synchronization. If this form of
 *    data-loss is unacceptable, a further refinement adds guaranteed delivery.
 *
 *    See image: differential_synchronization_3_guaranteed_delivery.gif
 *
 * 5. Topolgy
 *    Scalability may become an issue as the number of clients increase. Diff and patch can be expensive operations,
 *    thus a server may become overloaded. There are two simple methods of distributing the system onto multiple servers.
 *
 *    One method is to separate the database from the algorithm. Thus one database would service any number of
 *    load-balanced servers. A client could hit any server, and as long as the view of the shared database is identical
 *    across all servers, the system remains consistent.
 *
 *    See image: differential_synchronization_4_topolgy.gif
 *
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class DifferentialSynchronization {

}
