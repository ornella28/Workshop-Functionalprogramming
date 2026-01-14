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
        System.out.println("Active subscribers");
        for (Subscriber s: activeSubscribers){
            System.out.println(s);
        }





    }

}
