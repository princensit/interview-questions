package com.prince.design;

/**
 * @author Prince Raj
 */
public class GroupChatService {

    public static void main(String[] args) {
        /*
        Design a group chat system
        With a bunch of groups that anyone can request to join
        Groups can be public or moderated
        If public, user is added immediately
        If moderated, a join request is sent for approval to moderators
        After joining, user is able to post messages in the Group and read messages.
                Constraints:
        Consider this as a college project - design for single server, multiple clients, in-memory storage
        Signup is out of scope

        class Message {
            int id;
            String content;
            Date date;

            int count;
        }

        enum ChatType {
            PUBLIC, MODERATED
        }

        class User {
            String id;
            String name;

            // group id -> GroupChat
            Map <String, GroupChat> groupChats;
        }


        class Chat extends BasicChat{

            // owner (to approve the request for moderated group type)

            List<Message> messages;

            List<User> participants;

            Chat(boolean public) {
                this.public = public;
            }
        }

        class BasicChat {

            String id;

            ChatType chatType;
        }


// singleton class
        class ChatUserManager {

            List<BasicChat> groupChats;

            // chat id -> group chat
            Map<String, BasicChat> chatsById;

            Map<String, User> chatByUserId;


            UserService userService;

            // returns list of chats
            void List<BasicChat> getGroupChats() {
                return groupChats;
            }

            void requestForApproval(User user, String chatId) {
                // get Chat object for given chat id

                // get admin user

                // check if user is already added to that group

                // if all basic conditions of users are met, then approve the request from admin and inform to user

                // persist this info in cache or DB

            }

            void joinGroupChat(User user, String chatId) {
                BasicChat chat = chatsById.get(chatId);

                if(chat.getChatType == MODERATED) {
                    boolean valid = validerUserGroupConstrinats();

                    if(!valid) {
                        throw exceptionl
                    }
                }

                // add that user to group

            }

            private void validerUserGroupConstrinats(User user, String chatId) {
                // check whether admin has approved the request for Moderated type from some persistence say DB
                // If already approved, then add him to chat group
                // else throw exception
            }



            boolean removeFromChat() {

            }

            List<Message> getUserMessages() {
                return userService.getMessages();
            }

            // UserService

        }

        class UserService  {

            // cache can be extended to NoSQL where data is schemaless, and highly available
            Map<String, GroupChat> detailsChatsByChatId;

            List<Message> getMessagesForUser(User id, String chatId) {
                // first check in cache, if not found then call user dao to get all messages from DB and convert into Message POJO

                // and also put into detailsChatsByChatId in-memory cache

                GroupChat chat;

                chat.getMessages();

                // return messages;

            }

            // paginated response - limit and offset

        }
        */
    }
}
