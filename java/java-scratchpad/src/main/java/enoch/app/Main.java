package enoch.app;

import enoch.app.util.HttpUrlConnectionUtil;

import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://swapi.dev/api/people/1";
        URL _url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)_url.openConnection();
        String responseBody = HttpUrlConnectionUtil.getResponseBody(conn, 20000);
        System.out.println(responseBody);
    }
}