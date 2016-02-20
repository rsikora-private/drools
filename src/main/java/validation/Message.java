package validation;

import java.util.List;

/**
 * Created by robertsikora on 20.02.2016.
 */
public interface Message {
    public enum Type {
        ERROR, WARNING
    }
    /**
     * @return type of this message
     */
    Type getType();
    /**
     * @return key of this message
     */
    String getMessageKey();
    /**
     * objects in the context must be ordered from the least
     * specific to most specific
     * @return list of objects in this message's context
     */
    List<Object> getContextOrdered();
}