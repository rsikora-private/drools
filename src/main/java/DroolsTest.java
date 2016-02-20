/**
 * Created by robertsikora on 19.02.2016.
 */

import model.Account;
import model.Customer;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.event.process.*;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import service.AccountService;
import validation.ValidationReportImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by robertsikora on 19.02.2016.
 */
public class DroolsTest {

    public final static void main(String ... args) throws Exception {

        KnowledgeBase kbase = readKnowledgeBase();
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        ksession.setGlobal("accountService", new AccountService());

        Account account1 = new Account(33l, "GOLD");

        for(final Object fact : Arrays.asList(account1, new Account(101l, "SILVER"),
                new Customer("John", account1),
                new Customer(null, null),
                new Customer(null, null))) {

            ksession.insert( fact );
        }

        final ValidationReportImpl validationReport = new ValidationReportImpl();
        List<Command> commands = new ArrayList<Command>();
        commands.add(CommandFactory.newSetGlobal("validationReport", validationReport));
        ksession.execute(CommandFactory
                .newBatchExecution(commands));
        ksession.execute(CommandFactory.newFireAllRules());

        try {
            ksession.execute(CommandFactory
                    .newBatchExecution(commands));
            //ksession.fireAllRules();
        }finally {
            ksession.dispose();
        }

        System.out.print("");

    }

    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("account.drl"), ResourceType.DRL);
        kbuilder.add(ResourceFactory.newClassPathResource("test.drl"), ResourceType.DRL);
        kbuilder.add(ResourceFactory.newClassPathResource("validation.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }
}

