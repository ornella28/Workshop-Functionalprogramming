package se.lexicon;

import java.util.List;

public class Main {

    void main() {

        List<Subscriber> subscribers = List.of(
                new Subscriber(1, "anna@example.com", Plan.Free, true, 10),
                new Subscriber(2, "erik@example.com", Plan.Basic, true, 3),
                new Subscriber(3, "sara@example.com", Plan.Pro, false, 0),
                new Subscriber(4, "noah@example.com", Plan.Free, true, 1),
                new Subscriber(5, "lina@example.com", Plan.Basic, true, 6),
                new Subscriber(6, "omar@example.com", Plan.Pro, true, 12),
                new Subscriber(7, "maria@example.com", Plan.Free, false, 0),
                new Subscriber(8, "john@example.com", Plan.Basic, true, 2)
        );

        // uSING the processor here
        SubscriberProcessor processor = new SubscriberProcessor();

        //Part1
        //1. Find active subscribers
        SubscriberFilter activeSubscriber = Subscriber::isActive;
        List<Subscriber> activeSubscribers = processor.findsubscribers(subscribers, activeSubscriber);
        System.out.println("------Active subscribers------");
        for (Subscriber s: activeSubscribers){
            System.out.println(s);
        }

        //2. Expiring subscribers (o or 1 month remaining)
        SubscriberFilter expiringSubscribers = s -> s.getMonthsRemaining() <= 1;
        List<Subscriber> expiringSubscriber = processor.findsubscribers(subscribers, expiringSubscribers);
        System.out.println("------Expiring subscribers------");
        for (Subscriber s: expiringSubscriber){
            System.out.println(s);
        }

        //3. Active and expiring subscribers
        SubscriberFilter activeAndExpiringSubscribers = s -> s.isActive() && s.getMonthsRemaining() <= 1;
        List<Subscriber> activeAndExpiringSubscriber = processor.findsubscribers(subscribers, activeAndExpiringSubscribers);
        System.out.println("------Active and expiring subscribers------");
        for (Subscriber s: activeAndExpiringSubscriber){
            System.out.println(s);
        }

        //4. Subscriber by plan
        SubscriberFilter basicPlanSubscribers = s -> s.getPlan() == Plan.Basic;
        List<Subscriber> basicPlanSubscriber = processor.findsubscribers(subscribers, basicPlanSubscribers);
        System.out.println("------Basic plan subscribers------");
        for (Subscriber s: basicPlanSubscriber){
            System.out.println(s);
        }

            SubscriberFilter proPlanSubscribers = s -> s.getPlan() == Plan.Pro;
            List<Subscriber> proPlanSubscriber = processor.findsubscribers(subscribers, proPlanSubscribers);
            System.out.println("------Pro plan subscribers------");
            for (Subscriber s: proPlanSubscriber){
                System.out.println(s);
            }

            SubscriberFilter freePlanSubscribers = s -> s.getPlan() == Plan.Free;
            List<Subscriber> freePlanSubscriber = processor.findsubscribers(subscribers, freePlanSubscribers);
            System.out.println("------Free plan subscribers------");
            for (Subscriber s: freePlanSubscriber){
                System.out.println(s);
            }

            //5. Paying subscribers
        SubscriberFilter payingSubscribers = s-> s.getPlan() == Plan.Basic || s.getPlan() == Plan.Pro;
            List<Subscriber> payingSubscriber = processor.findsubscribers(subscribers, payingSubscribers);
            System.out.println("------Paying subscribers------");
            for (Subscriber s: payingSubscriber){
                System.out.println(s);
            }

            //6. Extend subscription, an action which will use SubscriberAction

        SubscriberAction extendSubscription = s-> s.setMonthsRemaining(s.getMonthsRemaining() + 2);
            // To be continued....

        //7. Deactivate subscribers
        SubscriberAction deactivateSubscriber = s-> s.setActive(false);
        //apply













    }

}
