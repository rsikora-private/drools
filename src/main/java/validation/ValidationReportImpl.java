package validation;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by robertsikora on 20.02.2016.
 */
public class ValidationReportImpl implements ValidationReport {

    private Set<Message> messages = new HashSet<Message>();

    public Set<Message> getMessages() {
        return messages;
    }

    public Set<Message> getMessagesByType(Message.Type type) {
        return null;
    }

    public boolean contains(String messageKey) {
        return false;
    }

    public boolean addMessage(Message message) {
        return messages.add(message);
    }
}
