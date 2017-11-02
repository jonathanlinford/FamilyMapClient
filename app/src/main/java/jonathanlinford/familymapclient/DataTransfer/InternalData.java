package jonathanlinford.familymapclient.DataTransfer;


import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jonathanlinford.familymapclient.Model.*;

public class InternalData {
    public static Person[] persons;
    public static Event[] events;

    public static Map<Event, Marker> eventMarkerMap = new HashMap<>();
    public static Map<String, Float> colors = new HashMap<>();

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public Map<Event, Marker> getEventMarkerMap() {
        return eventMarkerMap;
    }

    public void setEventMarkerMap(Map<Event, Marker> eventMarkerMap) {
        this.eventMarkerMap = eventMarkerMap;
    }

    public static Person[] searchPersons(String searchType, String searchParam){
        ArrayList<Person> returnPersons = new ArrayList<>();

        switch(searchType){
            case "personID" :
                for(Person person: persons){
                    if(person.getPersonID().equals(searchParam)){
                        returnPersons.add(person);
                        break;
                    }
                }
                break;

            case "gender" :
                for(Person person: persons){
                    if(person.getGender() == searchParam.charAt(0)){
                        returnPersons.add(person);
                    }
                }
                break;
            case "eventID" :
                Event event = (Event) searchEvents("eventID", searchParam).keySet().toArray()[0];
                for(Person person : persons){
                    if(person.getPersonID().equals(event.getPersonID()))
                        returnPersons.add(person);
                    break;
                }
                break;

            default :
                System.out.println("Invalid search parameter");
                break;
        }
        if(returnPersons.size() > 0)
            return returnPersons.toArray(new Person[returnPersons.size()]);
        else
            return null;
    }

    public static Map<Event, Marker> searchEvents(String searchType, String searchParam){
        Map<Event, Marker> returnEvents = new HashMap<>();

        switch(searchType){
            case "eventID" :
                for(Event event: eventMarkerMap.keySet()){
                    if(event.getEventID().equals(searchParam)){
                        returnEvents.put(event, eventMarkerMap.get(event));
                    }
                }
                break;

            case "eventType" :
                for(Event event: eventMarkerMap.keySet()){
                    if(event.getEventType().equals(searchParam)){
                        returnEvents.put(event, eventMarkerMap.get(event));
                    }
                }
                break;

            case "personID" :
                for(Event event: eventMarkerMap.keySet()){
                    if(event.getPersonID().equals(searchParam)){
                        returnEvents.put(event, eventMarkerMap.get(event));
                    }
                }
                break;
            case "gender" :
                for(Event event: eventMarkerMap.keySet()){
                    if(searchPersons("personID", event.getPersonID())[0].getGender() == searchParam.charAt(0)){
                        returnEvents.put(event, eventMarkerMap.get(event));
                    }
                }
                break;
            case "parent" :
                Person person = persons[0];
                for(Event event: eventMarkerMap.keySet()){
                    if(event.getPersonID().equals(person.getPersonID())){
                        returnEvents.put(event, eventMarkerMap.get(event));
                    }
                }

                while(searchParam.equals("father")){
                    person = searchPersons("personID", person.getFather())[0];
                    if(person == null)
                        break;

                    for(Event event: eventMarkerMap.keySet()){
                        if(event.getPersonID().equals(person.getPersonID())){
                            returnEvents.put(event, eventMarkerMap.get(event));
                        }
                    }
                }

                while(searchParam.equals("mother")){
                    person = searchPersons("personID", person.getMother())[0];
                    if(person == null)
                        break;

                    for(Event event: eventMarkerMap.keySet()){
                        if(event.getPersonID().equals(person.getPersonID())){
                            returnEvents.put(event, eventMarkerMap.get(event));
                        }
                    }
                }
                break;

            default :
                System.out.println("Invalid search parameter");
        }
        if(returnEvents.size() == 0) {
            return null;
        }

        return returnEvents;
    }

    public static Event keyFromValue(Marker m){
        for (Event e : eventMarkerMap.keySet()) {
            if (eventMarkerMap.get(e).equals(m)) {
                return e;
            }
        }
        return null;
    }
}
