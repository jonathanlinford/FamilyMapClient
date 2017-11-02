package jonathanlinford.familymapclient.DataTransfer.Responses;

import java.io.Serializable;

public class LoginResponse {
    private String message;
    private String authToken;
    private String userName;
    private String personID;

    public LoginResponse(String message,
                         String authToken,
                         String userName,
                         String personID) {
        this.message = message;
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
