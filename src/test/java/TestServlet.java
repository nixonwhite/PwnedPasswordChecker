/**
 * PwnedPassword checker
 * Uses third-party service https://haveibeenpwned.com/Passwords
 *
 * This test does not collect any kind of personal information!
 *
 * @author nick
 * @version 0.1
 */

import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.Assert.assertTrue;

public class TestServlet {

    @Test
    public void checkConnection() throws IOException {
        URL url = new URL("http://193.151.91.25:8088/PwnedPasswordChecker/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);

        BufferedReader responseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String inLine;
        StringBuilder response = new StringBuilder();
        while ((inLine = responseReader.readLine()) != null) {
            response.append(inLine);
        }
        responseReader.close();

        System.out.println(response.toString());
        assertTrue(response.toString().toLowerCase().contains("javacourses"));
    }

    @Test(expected = IOException.class)
    public void checkGETMethod() throws IOException {
        URL url = new URL("http://193.151.91.25:8088/PwnedPasswordChecker/checkpass?password=zzz");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);

        BufferedReader responseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    }

    @Test
    public void checkPOSTMethod() throws IOException {
        URL url = new URL("http://193.151.91.25:8088/PwnedPasswordChecker/checkpass?password=zzz");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);

        BufferedReader responseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String inLine;
        StringBuilder response = new StringBuilder();
        while ((inLine = responseReader.readLine()) != null) {
            response.append(inLine);
        }
        responseReader.close();

        System.out.println(response.toString());
        assertTrue(response.toString().toLowerCase().contains("oops"));
    }
}
