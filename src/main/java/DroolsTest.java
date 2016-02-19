/**
 * Created by robertsikora on 19.02.2016.
 */

import model.Account;
import model.Customer;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.event.process.*;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import service.AccountService;

import java.util.Arrays;

/**
 * Created by robertsikora on 19.02.2016.
 */
public class DroolsTest {

    public final static void main(String ... args) throws Exception {

        KnowledgeBase kbase = readKnowledgeBase();
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        ksession.setGlobal("accountService", new AccountService());

        Account account1 = new Account(66l);

        for(final Object fact : Arrays.asList(account1, new Account(99l), new Customer("John"), new Customer("John")) ) {
            ksession.insert( fact );
        }

        try {
       /*     ksession.addEventListener(new ProcessEventListener() {
                public void beforeProcessStarted(ProcessStartedEvent event) {

                }

                public void afterProcessStarted(ProcessStartedEvent event) {

                }

                public void beforeProcessCompleted(ProcessCompletedEvent event) {

                }

                public void afterProcessCompleted(ProcessCompletedEvent event) {

                }

                public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {

                }

                public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {

                }

                public void beforeNodeLeft(ProcessNodeLeftEvent event) {

                }

                public void afterNodeLeft(ProcessNodeLeftEvent event) {

                }

                public void beforeVariableChanged(ProcessVariableChangedEvent event) {
                    System.out.print("");
                }

                public void afterVariableChanged(ProcessVariableChangedEvent event) {
                    System.out.print("");
                }
            });
*/
            ksession.fireAllRules();

        }finally {
            ksession.dispose();
        }

    }

    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("account.drl"), ResourceType.DRL);
        kbuilder.add(ResourceFactory.newClassPathResource("test.drl"), ResourceType.DRL);
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

