package se.lexicon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriberProcessorTest {

    private SubscriberProcessor subscriberProcessor;
    private SubscriberDAO subscriberDAO;

    @BeforeEach
    void setUp() {
        subscriberProcessor = new SubscriberProcessor();
        subscriberDAO = new SubscriberDAO();

        subscriberDAO.save(new Subscriber(1, "Alice@gmail.com", Plan.Free, true, 0));
        subscriberDAO.save(new Subscriber(2, "anitha@gmail.com", Plan.Basic, true, 5));
        subscriberDAO.save(new Subscriber(3, "ornella@gmail.com", Plan.Pro, false, 2));
        subscriberDAO.save(new Subscriber(4, "didier@gmail.com", Plan.Basic, true, 1));


    }

    //active subscribers
    @Test
    void testFindActiveSubscribers() {
        List<Subscriber> allSubscribers = subscriberDAO.findAll();
        SubscriberFilter activeFilter = Subscriber::isActive;

        List<Subscriber> activeSubscribers = subscriberProcessor.findsubscribers(allSubscribers, activeFilter);

        assertEquals(3, activeSubscribers.size());
        for (Subscriber s : activeSubscribers) {
            assertTrue(s.isActive());
        }

    }

    //expiring subscribers
    @Test
    void testFindExpiringSubscribers() {
        List<Subscriber> allSubscribers = subscriberDAO.findAll();
        SubscriberFilter expiringFilter = s -> s.getMonthsRemaining() <= 1;

        List<Subscriber> expiringSubscribers = subscriberProcessor.findsubscribers(allSubscribers, expiringFilter);

        assertEquals(2, expiringSubscribers.size());
        for (Subscriber s : expiringSubscribers) {
            assertTrue(s.getMonthsRemaining() <= 1);
        }
    }

    // active and expiring subscribers
    @Test
    void testFindActiveAndExpiringSubscribers() {
        List<Subscriber> allSubscribers = subscriberDAO.findAll();
        SubscriberFilter activeAndExpiringFilter = s -> s.isActive() && s.getMonthsRemaining() <= 1;

        List<Subscriber> activeAndExpiringSubscribers = subscriberProcessor.findsubscribers(allSubscribers, activeAndExpiringFilter);

        assertEquals(2, activeAndExpiringSubscribers.size());
        for (Subscriber s : activeAndExpiringSubscribers) {
            assertTrue(s.isActive() && s.getMonthsRemaining() <= 1);
        }
    }

    //Extend subscriptions for paying subscribers
    @Test
    void testExtendSubscriptionsForPayingSubscribers() {
        List<Subscriber> allSubscribers = subscriberDAO.findAll();
        SubscriberFilter payingFilter = s -> s.getPlan() != Plan.Free;
        int extensionMonths = 3;
        SubscriberAction extendAction = s -> s.setMonthsRemaining(s.getMonthsRemaining() + extensionMonths);
        subscriberProcessor.applyToMatching(allSubscribers, payingFilter, extendAction);
        for (Subscriber s : allSubscribers) {
            if (payingFilter.matches(s)) {
                assertTrue(s.getMonthsRemaining() >= extensionMonths);
            }
        }
    }

    //Deactivate expired free subscribers
    @Test
    void testDeactivateExpiredFreeSubscribers() {
        List<Subscriber> allSubscribers = subscriberDAO.findAll();
        SubscriberFilter expiredFreeFilter = s -> s.getPlan() == Plan.Free && s.getMonthsRemaining() == 0;
        SubscriberAction deactivateAction = s -> s.setActive(false);
        subscriberProcessor.applyToMatching(allSubscribers, expiredFreeFilter, deactivateAction);
        for (Subscriber s : allSubscribers) {
            if (expiredFreeFilter.matches(s)) {
                assertFalse(s.isActive());
            }
        }
    }

    //Filter subscribers by plan
    @Test
    void testFilterSubscribersByPlan() {
        List<Subscriber> allSubscribers = subscriberDAO.findAll();
        SubscriberFilter basicPlanFilter = s -> s.getPlan() == Plan.Basic;

        List<Subscriber> basicPlanSubscribers = subscriberProcessor.findsubscribers(allSubscribers, basicPlanFilter);

        assertEquals(2, basicPlanSubscribers.size());
        for (Subscriber s : basicPlanSubscribers) {
            assertEquals(Plan.Basic, s.getPlan());
        }
    }

}
