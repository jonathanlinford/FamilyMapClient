package jonathanlinford.familymapclient.DataTransfer.Responses;

import jonathanlinford.familymapclient.Model.*;

/**
 * Created by jonathan.linford on 8/4/2017.
 */

public class FamilyDataResponse {
    private String message;
    private Event[] events;
    private Person[] persons;
    private String descendantFirstName;
    private String descendantLastName;

    public FamilyDataResponse(String message, Event[] events, Person[] persons) {
        this.message = message;
        this.events = events;
        this.persons = persons;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }
}
