package com.prince.design;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * https://www.youtube.com/watch?v=zKPNUMkwOJE
 * 
 * @author Prince Raj
 */
public class ChatServer {

    // u1 messaging to u2
    public void chat(ChatUser u1, ChatUser u2, Message message) {
        Map<String, PrivateChat> privateChats = u1.getPrivateChats();
        PrivateChat chat = privateChats.get(u2.getId());
        if (chat == null) {
            chat = new PrivateChat(u1, u2);
        }

        chat.addMessage(message);
    }

    // u1 messaging to group chat
    public void chat(ChatUser u1, String groupChatId, Message message) {
        Map<String, GroupChat> groupChats = u1.getGroupChats();
        GroupChat chat = groupChats.get(groupChatId);

        chat.addMessage(message);
    }

    // delete all messages of u2 from u1
    public void deleteMessages(ChatUser u1, ChatUser u2) {
        Map<String, PrivateChat> privateChats = u1.getPrivateChats();
        PrivateChat chat = privateChats.get(u2.getId());
        if (chat == null) {
            // log error that there are no private chats done between done
        } else {
            chat.getMessages().clear();
        }
    }

    // delete all messages of given group chat from u1
    public void deleteMessages(ChatUser u1, String groupChatId) {
        Map<String, GroupChat> groupChats = u1.getGroupChats();
        GroupChat chat = groupChats.get(groupChatId);
        if (chat == null) {
            // log error that there are no private chats done between done
        } else {
            chat.getMessages().clear();
        }
    }
}

class ChatDisplay {

    private static final int DEFAULT_MESSAGES_COUNT = 10;

    private static final MessageComparator MESSAGE_COMPARATOR = new MessageComparator();

    // paginated API
    public List<Message> getMessages(ChatUser u1, ChatUser u2, int rowsCount, int offsetFromLastMessage) {
        if (rowsCount > DEFAULT_MESSAGES_COUNT) {
            rowsCount = DEFAULT_MESSAGES_COUNT;
        }

        // TODO lot of optimizations needed here
        Map<String, PrivateChat> privateChats = u1.getPrivateChats();
        PrivateChat chat = privateChats.get(u2.getId());

        List<Message> messages = chat.getMessages();
        Collections.sort(messages, MESSAGE_COMPARATOR);

        int size = messages.size();
        return messages.subList(size - rowsCount - offsetFromLastMessage, size - offsetFromLastMessage);
    }
}

// singleton instancee

class ChatUserManager {

    private static ChatUserManager instance;

    private Map<String, ChatUser> userById;

    private Map<String, ChatUser> userByName;

    private Map<String, ChatUser> onlineUsers;

    public static ChatUserManager getInstance() {
        if (instance == null) {
            synchronized (ChatUserManager.class) {
                if (instance == null) {
                    instance = new ChatUserManager();
                }
            }
        }

        return instance;
    }

    public void addUser(ChatUser user) {
    }

    // User A approved User B to add him
    public void approveAddRequest(AddRequest request) {
    }

    // User A rejected User B to add him
    public void rejectAddRequest(AddRequest request) {
    }
}

abstract class Chat {

    private int id;

    private List<Message> messages;

    protected List<ChatUser> participants;

    public boolean addMessage(Message message) {
        messages.add(message);

        return true;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public int getId() {
        return id;
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
class PrivateChat extends Chat {

    public PrivateChat(ChatUser u1, ChatUser u2) {
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
class GroupChat extends Chat {

    public void addParticipant(ChatUser user) {

    }

    public void removeParticipant(ChatUser user) {

    }
}

@Data
class AddRequest {

    private ChatUser fromUser;

    private ChatUser toUser;

    private String message;

    private Date date;

    private RequestStatus status;
}

@Data
class ChatUser {

    private String id;

    private String name;

    private UserStatus status;

    private Role role;

    // other participant's user id -> private chat
    private Map<String, PrivateChat> privateChats;

    // group chat id -> group chat
    private Map<String, GroupChat> groupChats;

    private Map<String, AddRequest> sentAddRequests;

    private Map<String, AddRequest> receivedAddRequests;

    private Map<String, ChatUser> contacts;
}

@Data
class Message {

    private String id;

    private String content;

    private Date date;
}

@Data
class UserStatus {

    private String message;

    private UserStatusType type;
}

class MessageComparator implements Comparator<Message> {

    @Override
    public int compare(Message o1, Message o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}

enum UserStatusType {
    ONLINE, OFFLINE, INVISIBLE, BUSY, AWAY
}

enum Role {
    USER, ADMIN
}

enum RequestStatus {
    UNREAD, READ, ACCEPTED, REJECTED
}