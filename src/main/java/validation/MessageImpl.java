package validation;

import java.util.List;

/**
 * Created by robertsikora on 20.02.2016.
 */
public class MessageImpl implements Message {

    private final Type type;
    private final String messageKey;
    private final List<Object> contextOrdered;

    public MessageImpl(Type type, String messageKey, List<Object> contextOrdered) {
        this.type = type;
        this.messageKey = messageKey;
        this.contextOrdered = contextOrdered;
    }

    public Type getType() {
        return type;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public List<Object> getContextOrdered() {
        return contextOrdered;
    }
}
