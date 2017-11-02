package jonathanlinford.familymapclient.ServerConnection;

import android.util.Log;

import java.net.*;

import jonathanlinford.familymapclient.DataTransfer.Requests.*;
import jonathanlinford.familymapclient.DataTransfer.Responses.*;

public class IServer {
    private static String serverHost;
    private static int serverPort;
    public static String userFirstName;
    public static String userLastName;


    public static LoginResponse login(LoginRequestDTO request){
        LoginResponse response;
        HttpClient exchange = new HttpClient();

        serverHost = request.getServerHost();
        serverPort = Integer.parseInt(request.getServerPort());

        URL url = null;

        try {
            url = new URL("http", serverHost, serverPort, "/user/login/");
        } catch(MalformedURLException e){
            Log.e("HttpURL", e.getMessage(), e);
        }

        //  Take serverHost and port from the request to get ready to encode
        request.setServerHost(null);
        request.setServerPort(null);

        //Send request, receive response
        String json = Serializer.encoder(request);
        String responseJson = exchange.postUrl(url, json);
        response = (LoginResponse) Serializer.decoder(responseJson, LoginResponse.class);

        return response;
    }

    public static LoginResponse register(RegisterRequestDTO request){
        LoginResponse response;
        HttpClient exchange = new HttpClient();

        serverHost = request.getServerHost();
        serverPort = Integer.parseInt(request.getServerPort());

        URL url = null;

        try {
            url = new URL("http", serverHost, serverPort, "/user/register/");
        } catch(MalformedURLException e){
            Log.e("HttpURL", e.getMessage(), e);
        }

        //  Take serverHost and port from the request to get ready to encode
        request.setServerHost(null);
        request.setServerPort(null);

        //Send request, receive response
        String json = Serializer.encoder(request);
        String responseJson = exchange.postUrl(url, json);
        response = (LoginResponse) Serializer.decoder(responseJson, LoginResponse.class);

        return response;
    }

    public static EventResponse event(String authToken, String eventID){
        EventResponse response;
        HttpClient exchange = new HttpClient();

        URL url = null;

        try {
            if(eventID != null)
                url = new URL("http", serverHost, serverPort, "event/" + eventID);
            else
                url = new URL("http", serverHost, serverPort, "event/");

        } catch(MalformedURLException e){
            Log.e("HttpURL", e.getMessage(), e);
        }

        String responseJson = exchange.getUrl(url, authToken);
        response = (EventResponse) Serializer.decoder(responseJson, EventResponse.class);

        return response;
    }

    public static PersonResponse person(String authToken, String personID){
        PersonResponse response;
        HttpClient exchange = new HttpClient();

        URL url = null;

        try {
            if(personID != null)
                url = new URL("http", serverHost, serverPort, "person/" + personID);
            else
                url = new URL("http", serverHost, serverPort, "person/");

        } catch(MalformedURLException e){
            Log.e("HttpURL", e.getMessage(), e);
        }

        String responseJson = exchange.getUrl(url, authToken);
        response = (PersonResponse) Serializer.decoder(responseJson, PersonResponse.class);

        return response;
    }
}
