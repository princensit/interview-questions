package com.prince.design;

/**
 * https://neil.fraser.name/writing/sync/
 *
 * <pre>
 * Divide the whole system into following major components
 * 1. File storage
 *    Since Google Docs is part of Google Drive, I include the storage feature as well. The system allows users to group
 *    files (docs) into folders and support features like edit/create/remove etc.. It works like an OS.
 * 2. Online editing and formatting
 *    There’s no doubt that one of the core features of Google Docs is online editing. It supports almost everything of
 *    Microsoft Office and maybe more.
 * 3. Collaboration
 *    It’s truly amazing that Google Docs allows multiple people to edit a single doc simultaneously. This is a technical
 *    challenge for sure.
 * 4. Access Control
 *    You can share docs with your friends and give different permissions (owner, read-only, allow comment etc.).
 *
 * <--- Concurrency --->
 * If you have used Git for version control, some of the ideas here can be similar. First, let’s consider the simplest
 * case – only 2 people are editing the same doc. Assuming the doc is “abc”.
 *
 * Basically, the server can keep 2 copies of the same doc to each person and tracks the full revision history as well.
 * When A edits the doc by adding “x” in the beginning, this change will be sent to the server together with the last
 * revision seen by A. Suppose at this time, B deletes the last character “c” and this change is sent to the server as
 * well.
 *
 * Since the server knows the change is made on which revision, it will adjust the change accordingly. More specifically,
 * B’s change is deleting the 3rd character “c”, which will be transformed to deleting the 4th character as A adds “x”
 * in the beginning.
 *
 * This is called <strong>operational transformation</strong>. It’s okay if you never heard of this. The basic idea is to transform each person’s mutation based on its revision and revisions from other collaborators.
 *
 * <--- Access Control --->
 * Google Docs allows you to invite collaborators to each doc with different level of permissions. For each file, you
 * can keep a list of collaborators with corresponding permissions like read-only, owner etc.. When one wants to do
 * specific actions, the system checks his permission.
 *
 * </pre>
 *
 * Refer {@link DifferentialSynchronization} for stratey
 *
 * @author Prince Raj
 */
public class GoogleDoc {

}
