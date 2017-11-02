package jonathanlinford.familymapclient.ServerConnection;


import com.google.gson.*;


public class Serializer {

    public static String encoder(Object o){
        if(o == null)
            return null;

        Gson encodee = new Gson();

        return encodee.toJson(o);
    }

    public static Object decoder(String s, Class c){
        Gson decodee = new Gson();

        return decodee.fromJson(s, c);
    }
}
