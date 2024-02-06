package enoch.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpUrlConnectionUtil {
    public static String getResponseBody(HttpURLConnection httpURLConnection, int timeout) throws IOException {
        httpURLConnection.setConnectTimeout(timeout);
        httpURLConnection.connect();

        System.out.println(httpURLConnection.getResponseCode());
        System.out.println(httpURLConnection.getResponseMessage());

        // There's probably a new cooler way to do this
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }

    public static void setAuthorizationHeader(HttpURLConnection conn, String username, String password) {
        String authString = username + ":" + password;
        String authHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString(authString.getBytes());
        conn.setRequestProperty("Authorization", authHeaderValue);
        System.out.println("Done");
    }
}
