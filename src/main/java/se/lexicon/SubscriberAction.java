package se.lexicon;

//Important class to create functional interface for Subscriber actions
@FunctionalInterface
public interface SubscriberAction {

    void run(Subscriber subscriber);
}
