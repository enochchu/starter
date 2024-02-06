package enoch.test.util;

import enoch.app.util.HttpUrlConnectionUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class HttpUrlConnectionUtilTest {
    private final String TEST_URL = "https://swapi.dev/api/people/1";
    private final Logger logger = Logger.getLogger(HttpUrlConnectionUtil.class.getName());

    @Test
    public void shouldBeAbleToSetABase64AuthorizationHeader() throws IOException, NoSuchFieldException, IllegalAccessException {
        URL _url = new URL(TEST_URL);
        HttpURLConnection conn = (HttpURLConnection)_url.openConnection();

        String username = "dummy";
        String password = "yummd";

        try {
            HttpUrlConnectionUtil.setAuthorizationHeader(conn, username, password);
        }
        catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void shouldBeAbleToReturnResponseBody() throws IOException {
        URL _url = new URL(TEST_URL);
        HttpURLConnection conn = (HttpURLConnection)_url.openConnection();
        String responseBody = null;
        responseBody = HttpUrlConnectionUtil.getResponseBody(conn, 20000);

        assertNotNull(responseBody);
        logger.log(Level.INFO, "This is the return response - check to see if its legit: " + responseBody);
    }
}
