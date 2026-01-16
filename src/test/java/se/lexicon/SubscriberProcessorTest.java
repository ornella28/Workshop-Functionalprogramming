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


}
