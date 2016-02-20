package validation;

import java.util.Set;

/**
 * Created by robertsikora on 20.02.2016.
 */
public interface ValidationReport {
    /**
     * @return all messages in this report
     */
    Set<Message> getMessages();
    /**
     * @return all messages of specified type in this report
     */
    Set<Message> getMessagesByType(Message.Type type);
    /**
     * @return true if this report contains message with
     *  specified key, false otherwise
     */
    boolean contains(String messageKey);
    /**
     * adds specified message to this report
     */
    boolean addMessage(Message message);
}
