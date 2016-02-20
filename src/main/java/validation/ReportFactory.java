package validation;

/**
 * Created by robertsikora on 20.02.2016.
 */
public interface ReportFactory {

    ValidationReport createValidationReport();
    Message createMessage(Message.Type type, String messageKey,
                          Object... context);

}
