
public class Whatx
{
    public Whatx(){
    }

    /**
     * delete the message 
     * @param chatName the name of the chat where the message has to be deleted
     * @param msgld the id of the message that has to be deleted
     * @return boolean true if the message was deleted, false otherwise.
     */
    public boolean deleteMessage(String chatName, int msgId){
        Chat chat = findChat(chatName);
        if (chat != null){
            boolean deleted = chat.deleteMesagge(msgId);
            Message msg = findMessage(msgText);
            if (msg != null && !read){
                deleted = deleteMessage();
            }
            if (deleted){
                ArrayList<Usuario> members = chat.getMembers();
                for (int i = 0; members.size(); i++){
                    String toPhone = user.getPhone();
                    socket.deleteMessage(toPhone, chatName, mesgid);
                }
            }
        }
    }
}