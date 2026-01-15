package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class SubscriberDAO {

    //Creating a private list to store subscribers
    private final List<Subscriber> storeSubscribers = new ArrayList<>();

    //Creating a method to save subscribers to the list
    public void save(Subscriber subscriber){
        storeSubscribers.add(subscriber);
    }



}
