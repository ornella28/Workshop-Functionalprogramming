package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class SubscriberProcessor {

    //Creating a findSubsrciber method
    public List<Subscriber> findsubscribers(List<Subscriber> list, SubscriberFilter filter) {
        List<Subscriber> result = new ArrayList<>();

        for(Subscriber s : list){
            if(filter.matches(s)){
                result.add(s);
            }
        }
        return result;
    }

    //Creating a method applyToMatching for acctions with SubscribeAction
    public List<Subscriber> applyToMatching(List<Subscriber> list, SubscriberFilter filter, SubscriberAction action){
        List<Subscriber> result = new ArrayList<>();

        for(Subscriber s : list){
            if(filter.matches(s)){
                action.run(s);
                result.add(s);
            }
        }
        return result;
    }
}
