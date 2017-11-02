package jonathanlinford.familymapclient.ServerConnection;


import android.util.Log;

import java.io.*;
import java.net.*;

public class HttpClient {

    public String getUrl(URL url, String authToken){
        try{
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", authToken);
            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                //  Get response body input stream
                InputStream respBody = connection.getInputStream();

                return readString(respBody);
            }
        } catch(Exception e){
            Log.e("HttpClient", e.getMessage(), e);
        }

        return null;
    }

    public String postUrl(URL url, String json){
        try{
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            //connection.setRequestProperty("Content-Length", "" + json.getBytes("UTF-8").length);
            connection.addRequestProperty("ACCEPT", "application/json");
            connection.connect();

            OutputStream reqBody = connection.getOutputStream();
            writeString(json, reqBody);
            reqBody.close();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                //  Get response body input stream
                InputStream respBody = connection.getInputStream();

                return readString(respBody);
            }
        } catch(Exception e){
            Log.e("HttpClient", e.getMessage(), e);
        }

        return null;
    }

    private void writeString(String str, OutputStream os) throws IOException{
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    private String readString(InputStream is) throws IOException{
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);

        char[] buf = new char[1024];
        int len;

        while ((len = sr.read(buf)) > 0){
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
