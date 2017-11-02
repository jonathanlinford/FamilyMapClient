package jonathanlinford.familymapclient.Model;




/**
 * An object to store the personal information of everyone in the family history tree.
 *
 * Containts the following variables:
 * String personID;
 * String descendant;
 * String firstName;
 * String lastName;
 * char gender;
 * String fatherID;
 * String motherID;
 * String spouseID;
 */
public class Person {
    private String personID;
    private String descendant;
    private String firstName;
    private String lastName;
    private char gender;
    private String father;
    private String mother;
    private String spouse;

    /**
     * Constructor for the Person class
     */
    public Person(String personID, String descendant, String firstName, String lastName, char gender, String father, String mother, String spouse) {
        this.personID = personID;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }


    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }
}
