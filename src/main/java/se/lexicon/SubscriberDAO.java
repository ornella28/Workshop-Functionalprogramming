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

    //Creating a method to get all subscribers from the list
    public List<Subscriber> findAll(){
        return new ArrayList<>(storeSubscribers);
    }

    //Creating a method to find a subscriber by id
    public Subscriber findById(int id){
        for(Subscriber s : storeSubscribers){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }



}
